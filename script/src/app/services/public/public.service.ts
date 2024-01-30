import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignUp } from '../../models/signup.model';
import {Login} from '../../models/login.model'
import { Observable } from 'rxjs';
const BASIC_URL = ['http://localhost:8081/'];

@Injectable({
  providedIn: 'root',
})
export class PublicService {
  constructor(private http:HttpClient) {}

  signup(signupRequest:SignUp): Observable<any>{
    return this.http.post<[]>(BASIC_URL+"api/auth/signup",signupRequest);
  }

  login(loginRequest:Login): Observable<any>{
    return this.http.post<[]>(BASIC_URL+"api/auth/login",loginRequest);
  }

}