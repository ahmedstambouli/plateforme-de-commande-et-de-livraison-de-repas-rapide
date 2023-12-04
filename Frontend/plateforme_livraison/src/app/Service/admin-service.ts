import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from '../Model/Admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) {
  }

  private URL ="http://localhost:8085/public/"

  getAllInfo():any{
    return this.http.get(this.URL+"Admin/allInfo");
  }

  updateAdmin(admin:Admin){
    return this.http.put(this.URL+"Admin/update/",admin);
  }


}
