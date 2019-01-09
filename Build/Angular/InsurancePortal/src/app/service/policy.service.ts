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



  getQuote(customerVehicle: CustomerVehicle) {
    console.log("In Policy Service");
    console.log(customerVehicle);
    return this.http.post('/InsurancePortal/policy/getQuote', customerVehicle);
  }

  getQuoteByPolicyId(policyId: number) {
    console.log("In Policy Service");
    console.log(policyId);
    return this.http.get('/InsurancePortal/policy/getRenewPolicyQuote?policyId='+policyId);
  }

  addPolicy(username: string, customerVehicle: CustomerVehicle, policyPayment: PolicyPayment) {
    console.log("in policy service");

    let z = Object.assign({ username: username }, customerVehicle, policyPayment);
    console.log(z);
    return this.http.post("/InsurancePortal/policy/savePolicy", z);

  }

  getPolicyList(username: String, role: String) {

    return this.http.post("/InsurancePortal/policy/getPolicyList", { username: username, role: role });
  }

  getRenewPolicyList(username: String, role: String) {

    return this.http.post("/InsurancePortal/policy/getRenewPolicyList", { username: username, role: role });
  }

  getCustomerDetails(policyId:number){
    return this.http.get("/InsurancePortal/policy/getCustomerDetails?policyId="+policyId); 
  }

  getCustomerVehicleDetails(policyId:number){
    return this.http.get("/InsurancePortal/policy/getCustomerVehicleDetails?policyId="+policyId); 
  }

  getPolicyPaymentDetails(policyId:number){
    return this.http.get("/InsurancePortal/policy/getPolicyPaymentDetails?policyId="+policyId); 
  }

}
