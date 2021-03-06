import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";
import { Rezervacija } from "./rezervacija";

const httpOptions = {
	headers: new HttpHeaders({
		"Content-Type": "application/json",
		Authorization: "Bearer " + localStorage.getItem("jwtToken"),
	}),
};

@Injectable({ providedIn: "root" })
export class AdminService {
	url: string = environment.apiUrl;

	constructor(private http: HttpClient) {}

	getRezervacije(): Observable<Rezervacija[]> {
		return this.http.get<any>(`${this.url}/api/rezervacija`, httpOptions);
	}

	odbijRezervaciju(id: number): Observable<Object> {
		return this.http.get(
			`${this.url}/api/administrator/odbij-rezervaciju/${id}`,
			httpOptions
		);
	}

	odobriRezervaciju(id: number): Observable<Object> {
		return this.http.get(
			`${this.url}/api/administrator/odobri-rezervaciju/${id}`,
			httpOptions
		);
	}
}
