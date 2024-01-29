import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ['http://localhost:8081/'];

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  viewProfileByEmail(data: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/auth/viewDetail', data);
  }

  findAccountByEmail(data: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/auth/findEmail', data);
  }
}
