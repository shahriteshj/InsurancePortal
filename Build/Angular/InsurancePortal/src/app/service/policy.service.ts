import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Query } from '../model/Query';
import { CustomerVehicle } from '../model/CustomerVehicle';


@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get('http://localhost:3000/policy');
  }

  getQuote(customerVehicle: CustomerVehicle) {
    console.log("In Policy Service");
    console.log(customerVehicle);
    return this.http.post('/InsurancePortal/login/getQuote', customerVehicle);
  }

  addPolicy(query: Query) {
    return this.http.post("/InsurancePortal/query/authenticateUser", query);

  }



}
