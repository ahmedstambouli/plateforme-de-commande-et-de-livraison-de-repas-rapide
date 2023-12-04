import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private URL ="http://localhost:8085/public/Partenaire/"
  private URLP ="http://localhost:8085/public/Prouit/"


  constructor(private http:HttpClient) { }


    savePartenaire(partenaire: Object)
    {
      return this .http.post(this.URL+"RegisterPartenaire",partenaire);
    }


    getAllPartenaire()
    {
      return this.http.get(this.URL+"ListPartenaire");
    }

    getPartenaireparId(id:any){
      return this.http.get(this.URL+"ListPartenaire/"+id)
    }


    updatePartenaire(partenaire:object,id:any)
    {
      return this.http.put(this.URL+"updatepartenaire/"+id,partenaire)
    }
    updatePartenairesonpassword(partenaire:object,id:any)
    {
      return this.http.put(this.URL+"updatepartenairesonpassword/"+id,partenaire)
    }

    loginPartenaire(data: FormData) {
      return this.http.post(this.URL + "login", data);
    }



    //service produit


    Ajouterproduit(data:FormData)
    {
      return this.http.post(this.URLP+"AddProduit",data)
    }

    getproduitparidp(id:any)
    {
      return this.http.get(this.URLP+"byPartenaire/"+id)
    }

    getimageproduit(id:any):Observable<Blob>
    {
      const urlim=this.URLP+"image/"+id;
      return this.http.get(urlim,{responseType:'blob'})
    }

    getproduitparid(id:any)
    {
      return this.http.get(this.URLP+"Produitid/"+id)
    }

    updateproduit(id:any,produit:FormData)
    {
      return this.http.put(this.URLP+id+"/UpdateProduit",produit)
    }





}
