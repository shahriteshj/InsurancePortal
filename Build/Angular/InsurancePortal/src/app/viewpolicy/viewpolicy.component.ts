import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PolicyService } from '../service/policy.service';
import { Policy } from '../model/Policy';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyPayment } from '../model/PolicyPayment';
import { Customer } from '../model/Customer';
import { SharedDataService } from '../service/sharedData.service';

@Component({
  selector: 'app-viewpolicy',
  templateUrl: './viewpolicy.component.html',
  styleUrls: ['./viewpolicy.component.css']
})
export class ViewpolicyComponent implements OnInit {

  policyList: Policy[];
  
   customerVehicle: CustomerVehicle;
  policyPayment: PolicyPayment;
  customer:Customer;

  policy: Policy;
  policyId: number;
  private today: any;
  

  constructor(private router: Router, private _policyService: PolicyService,private sharedDataService: SharedDataService) { }

  ngOnInit() {
    console.log(this.sharedDataService.isCustomerUser);
    document.getElementById("policydetails").style.display = "none";
    this.getPolicyList();
    this.today = new Date().getTime();
  }

  getPolicyList() {

    return this._policyService.getPolicyList(localStorage.getItem("username"), localStorage.getItem("role"))
      .subscribe(policyList => {
        console.log(policyList);
        this.policyList = <Policy[]>policyList;
      });
  }



  viewDetails(id: number) {
    document.getElementById("policylist").style.display = "none";
    document.getElementById("policydetails").style.display = "block";
    this.policyId = id;
    this.getcustomerVehicleDetails();
    this.getPaymentDetails();
    this.getCustomerDetails();
  }

  showPolicyList() {
    document.getElementById("policylist").style.display = "block";
    document.getElementById("policydetails").style.display = "none";
  }

  getcustomerVehicleDetails() {
    this._policyService.getCustomerVehicleDetails(this.policyId)
    .subscribe(customerVehicle => this.customerVehicle = <CustomerVehicle>customerVehicle);

  }
  getPaymentDetails() {
    this._policyService.getPolicyPaymentDetails(this.policyId).subscribe(
      policyPayment=> {console.log(policyPayment);this.policyPayment=<PolicyPayment>policyPayment;}
    );
  }

  getCustomerDetails() {
    this._policyService.getCustomerDetails(this.policyId).subscribe(
      customer=> {console.log(customer);this.customer=<Customer>customer;}
    );
  }
}
