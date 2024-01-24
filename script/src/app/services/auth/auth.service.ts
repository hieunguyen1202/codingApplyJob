import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ['http://localhost:8081/'];

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  sendOtpCode(data: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/auth/sendOtpCode', data);
  }

  verifyAccount(data: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/auth/verifyAccount', data);
  }
}
