import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Partenaire } from '../Partenaire/Partenaire';

@Injectable({
  providedIn: 'root'
})
export class PartenaireService {

  constructor(private http:HttpClient) {
  }

  private URL ="http://localhost:8084/public/"


  getAllPartenaire():any{
    return this.http.get(this.URL+"Partenaire/ListPartenaire");
  }


  deletePartner(id: number) {
    return this.http.delete(this.URL + "Partenaire/deletePartenaire/" + id, { responseType: 'text' });
  }

  updatePartenaire(id:number,Partenaire:Partenaire){
    return this.http.put(this.URL+"Partenaire/updatepartenaire/"+id,Partenaire);
  }

  GetById(id :number){
    return this.http.get(this.URL+"Partenaire/ListPartenaire/"+id);
  }


  blockPartenaire(id: number) {
    return this.http.put(this.URL + "Partenaire/block/" + id, null);
  }


  deblockPartenaire(id: number) {
    return this.http.put(this.URL + "Partenaire/deblock/" + id, null);
  }


}
