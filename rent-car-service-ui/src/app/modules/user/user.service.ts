import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Rezervacija } from "../admin/rezervacija";

const httpOptions = {
	headers: new HttpHeaders({
		"Content-Type": "application/json",
		Authorization: "Bearer " + localStorage.getItem("jwtToken"),
	}),
};

@Injectable({ providedIn: "root" })
export class UserService {
	url: string = environment.apiUrl;

	constructor(private http: HttpClient) {}

	getIznajmljivanja(): Observable<Rezervacija[]> {
		return this.http.get<any>(`${this.url}/api/korisnik`, httpOptions);
	}
}
