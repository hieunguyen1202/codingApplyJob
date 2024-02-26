import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ['http://localhost:8081/'];

@Injectable({
  providedIn: 'root',
})
export class JobService {
  constructor(private http: HttpClient) {}

  getAllJob(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/job/viewJob');
  }

  getJobById(id: number): Observable<any> {
    return this.http.get(BASIC_URL + 'api/job/view/' + id);
  }

  addJob(data: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/job/createJob', data);
  }

  updateJob(data: any): Observable<any> {
    // Assuming data structure matches JobDTO
    console.log('====================================');
    console.log('update data::', data);
    console.log('====================================');
    return this.http.put<any>(`${BASIC_URL}api/job/edit`, data);
  }

  deleteJobById(id: number): Observable<any> {
    return this.http.delete<any>(`${BASIC_URL}api/job/deleteJob/${id}`);
  }
}
