import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Rezervacija } from "../admin/rezervacija";
import { OcenaRequest } from "./ocena";

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

  oceni(ocena : OcenaRequest, rezervacijaId : string): Observable<void>{
    return this.http.post<any>(`${this.url}/api/ocena/${rezervacijaId}`, ocena, httpOptions);
  }

}
