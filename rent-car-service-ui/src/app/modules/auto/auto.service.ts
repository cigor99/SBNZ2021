import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";
import { Auto } from "./auto";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'Bearer ' + localStorage.getItem('jwtToken')
  })
};

@Injectable({ providedIn: "root" })
export class AutoService{
  url: string = environment.apiUrl;


  constructor(private http: HttpClient){}

  autoNaprednaPretraga(korisnickiUnos:any): Observable<Auto[]>{
    return this.http.post<any>(`${this.url}/api/auto/napredna-pretraga`,korisnickiUnos, httpOptions);
  }
}
