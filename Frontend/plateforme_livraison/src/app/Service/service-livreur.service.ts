import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServiceLivreurService {
  private URL ="http://localhost:8085/public/"

  constructor(private http:HttpClient) {

   }
   getAllLivreurs():any
    {
      return this.http.get(this.URL+"livreurs/all");
    }

    createNewLivreur(User: Object)
    {
      return this.http.post(this.URL+"livreurs/create",User);
    }


    blockLivreur(livreurId: number) {
      return this.http.put(this.URL + "livreurs/block/" + livreurId, null);
    }


    deblockLivreur(livreurId: number) {
      return this.http.put(this.URL + "livreurs/deblock/" + livreurId, null);
    }

    deleteLivreur(livreurId: number) {
      return this.http.delete(this.URL + "livreurs/delete/" + livreurId, { responseType: 'text' });
    }

}
