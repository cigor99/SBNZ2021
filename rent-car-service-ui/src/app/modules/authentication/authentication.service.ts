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
import { JwtHelperService } from "@auth0/angular-jwt";
import { ConstantPool } from "@angular/compiler";

@Injectable({ providedIn: "root" })
export class AuthenticationService {
	authenticationUrl = `${environment.apiUrl}/api`;

	private handleError: HandleError;
	private headers = new HttpHeaders({ "Content-Type": "application/json" });
	public currentUserSubject = new BehaviorSubject<string>('');
	currentUserSubject$ = this.currentUserSubject.asObservable();

  private jwtService: JwtHelperService;


	constructor(private http: HttpClient, private router: Router) {
    this.jwtService = new JwtHelperService();
	}

	isAuthenticated(): boolean {
		return this.currentUserSubject.value != null;
	}

  autoLogin(): boolean {
    const user = this.getLoggedInUser();
    // provera postojanja tokena
    if (!user) {
      return true;
    }
    // provera validnosti tokena
    if (user.exp * 1000 < Date.now()) {
      // token vise nije validan
      localStorage.removeItem('jwtToken');
      localStorage.removeItem('expiresIn');
      this.router.navigate(['login-register/login']);
      return false;
    }
    // token je validan, prosledimo rolu kao sledecu vrednost observable
    const role = this.getUserRole();
    this.currentUserSubject.next(role);
    // pokrenemo refresh
    return true;
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
		localStorage.removeItem('jwtToken');
    localStorage.removeItem('expiresIn');
    this.router.navigate(['auth/login']);
    this.currentUserSubject.next('');
    // this.stopRefreshTokenTimer();
	}

	register(registerRequest: RegisterRequest): any {
		const url = `${this.authenticationUrl}/register`;
		return this.http.post(url, registerRequest, { headers: this.headers});
	}

  // geteri i seteri
  // poziva se i za updatePasswordDto i sa loginDto -> response je any
  setLoggedInUser(response: any): void {
    // ekstrakcija tokena
    const jwtTokenBearer = response.headers.get('Authorization');
    const jwtToken = jwtTokenBearer.split(' ')[1];
    const expiresIn = response.headers.get('Expires-In');
    // postavljanje tokena
    localStorage.setItem('jwtToken', jwtToken);
    localStorage.setItem('expiresIn', expiresIn);
    // pokretanje tajmera za refresh tokena
    // this.startRefreshTokenTimer(jwtToken);
 }

  // sadrzaj jwt tokena moze biti bilo sta
  getLoggedInUser(): any {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
      return null;
    }
    const info = this.jwtService.decodeToken(token);
    return info;
  }

  getUserRole(): string {
    const info = this.getLoggedInUser();
    if (info) {
      return info.authorities[0].authority;
    }
    else {
      return '';
    }
  }
}
