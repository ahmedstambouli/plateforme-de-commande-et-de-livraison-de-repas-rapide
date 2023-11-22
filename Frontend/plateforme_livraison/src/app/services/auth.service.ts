import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../entity/authRequest';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url="http://localhost:8085/public/"
  constructor(private http:HttpClient) { }
  login(body:AuthRequest):any{
    return this.http.post(this.url+"login",body)
  }
}
