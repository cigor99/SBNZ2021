import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Router } from "@angular/router";
import { BehaviorSubject, throwError } from "rxjs";
import { Observable } from "rxjs";
import { catchError, map, tap } from "rxjs/operators";
import { User } from "./user";
import { HandleError } from "src/app/core/services/http-error-handler.service";
import { RegisterRequest } from "./register";

@Injectable({ providedIn: "root" })
export class AuthenticationService {
	authenticationUrl = `${environment.apiUrl}/api/auth`;

	private handleError: HandleError;
	private currentUserSubject: BehaviorSubject<User>;
	public currentUser: Observable<User>;
	private headers = new HttpHeaders({ "Content-Type": "application/json" });

	private getUserFromLocalStorage(): User {
		const user = JSON.parse(localStorage.getItem("currentUser"));
		if (!user) {
			return null;
		}

		user.token = user.token._text;
		user.role = user.role._text;
		user.expiresIn = +user.expiresIn._text;
		return user;
	}

	constructor(private http: HttpClient, private router: Router) {
		this.currentUserSubject = new BehaviorSubject<User>(
			this.getUserFromLocalStorage()
		);
		this.currentUser = this.currentUserSubject.asObservable();
	}

	public get currentUserValue(): User {
		return this.currentUserSubject.value;
	}

	isAuthenticated(): boolean {
		return this.currentUserSubject.value != null;
	}

	login(email: string, lozinka: string): Observable<HttpResponse<void>> {
		const reqBody = { email, lozinka };
		console.log(reqBody);
		return this.http.post<void>("http://localhost:8080/login", reqBody, {
			headers: this.headers,
			observe: "response",
		});
	}

	logout(): void {
		localStorage.removeItem("currentUser");
		this.currentUserSubject.next(null);
		this.router.navigate([""]);
	}

	register(registerRequest: RegisterRequest): any {
		const url = `${this.authenticationUrl}/register`;
		return this.http.post(url, registerRequest);
		// .pipe(
		//   catchError(this.handleError<string>('postRegisterRequest')
		//   )
		// );
	}
}
