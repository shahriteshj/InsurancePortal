import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Query } from '../model/Query';


@Injectable({
  providedIn: 'root'
})
export class PolciyService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get('http://localhost:3000/policy');
  }

 

  addPolicy(query:Query){
    return this.http.post("/InsurancePortal/query/authenticateUser",query);

  }

  
  
}
