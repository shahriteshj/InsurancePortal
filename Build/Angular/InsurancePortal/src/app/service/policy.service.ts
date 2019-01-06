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
    console.log("in policy service");

    let z=Object.assign({username:username},customerVehicle,policyPayment);
    console.log(z);
    return this.http.post("/InsurancePortal/policy/savePolicy", z);

  }

  getPolicyList(username:String,role:String){

    return this.http.post("/InsurancePortal/policy/getPolicyList",{username:username,role:role});
  }

}
