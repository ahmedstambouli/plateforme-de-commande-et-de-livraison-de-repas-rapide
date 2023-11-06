import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Partenaire } from './Partenaire/Partenaire';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private URL ="http://localhost:8085/public/Partenaire/"


  constructor(private http:HttpClient) { }


    savePartenaire(partenaire: Object)
    {
      return this .http.post("http://localhost:8085/public/Partenaire/RegisterPartenaire",partenaire);
    }


    getAllPartenaire()
    {
      return this.http.get("http://localhost:8085/public/Partenaire/ListPartenaire");
    }
}
