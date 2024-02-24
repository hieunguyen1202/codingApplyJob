import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASIC_URL = ['http://localhost:8081/'];

@Injectable({
  providedIn: 'root'
})
export class JobCategoryService {

  constructor(private http: HttpClient) {}

  addJobCategory(data : any){
    return this.http.post(BASIC_URL + 'api/job/job_category', data);
  }

  updateJobCategory(data : any){
    return this.http.post(BASIC_URL + 'api/job/update/job_category', data);
  }

  viewJobCategory(){
    return this.http.get(BASIC_URL + 'api/job/viewJobCategory');
  }

  deleteJobCategoryById(id : number){
    return this.http.delete(BASIC_URL + 'api/job/delete/job_category/'+id);
  }

}
