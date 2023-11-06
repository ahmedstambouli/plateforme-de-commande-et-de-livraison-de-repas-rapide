import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User1 } from '../entity/user1';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class User1Service {
   url=environment.apiUrl+"user";
  constructor(private http:HttpClient) { }
  async create (user:User1):Promise<any>{
    return this.http.post(this.url,user);
  }
}
