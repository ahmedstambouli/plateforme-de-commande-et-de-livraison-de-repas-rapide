import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Partenaire } from './Partenaire/Partenaire';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private URL ="http://localhost:8084/public/Partenaire/"


  constructor(private http:HttpClient) { }


    savePartenaire(partenaire: Object)
    {
      return this .http.post(this.URL+"RegisterPartenaire",partenaire);
    }


    getAllPartenaire()
    {
      return this.http.get(this.URL+"ListPartenaire");
    }




    loginPartenaire(data: FormData) {
      return this.http.post(this.URL + "login", data);
    }





}
