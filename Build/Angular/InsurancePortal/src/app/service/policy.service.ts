import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Query } from '../model/Query';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyPayment } from '../model/PolicyPayment';


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
    return this.http.post('/InsurancePortal/policy/getQuote', customerVehicle);
  }

  addPolicy(username:string,customerVehicle:CustomerVehicle,policyPayment:PolicyPayment) {
    let input = username + JSON.stringify(customerVehicle)+ JSON.stringify(policyPayment);
    console.log(input);
    
    //return this.http.post("/InsurancePortal/policy/addPolicy", "");

  }



}
