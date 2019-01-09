import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PolicyService } from '../service/policy.service';
import { Policy } from '../model/Policy';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyPayment } from '../model/PolicyPayment';
import { Customer } from '../model/Customer';
import { SharedDataService } from '../service/sharedData.service';

@Component({
  selector: 'app-renewpolicy',
  templateUrl: './renewpolicy.component.html',
  styleUrls: ['./renewpolicy.component.css']
})
export class RenewpolicyComponent implements OnInit {

  constructor(private router: Router, private _policyService: PolicyService,private sharedDataService: SharedDataService) { }
  renewPolicyList:Policy[];
  private today: any;
  customerVehicle: CustomerVehicle;
  policyPayment: PolicyPayment;
  price:number;

  ngOnInit() {
    this.getPolicyListForRenew();
    this.today = new Date().getTime();
    document.getElementById("policydetails").style.display = "none";
  }

  getPolicyListForRenew(){
    
    return this._policyService.getRenewPolicyList(localStorage.getItem("username"), 
    localStorage.getItem("role")).subscribe(policyList => {
      console.log(policyList);
      this.renewPolicyList = <Policy[]>policyList;
    });
  }

  showRenewQuote(policyId:number){
    document.getElementById("policylist").style.display = "none";
    document.getElementById("policydetails").style.display = "block";
    this.getcustomerVehicleDetails(policyId);
    this._policyService.getQuoteByPolicyId(policyId).subscribe(quote => {
      console.log(quote);
      this.price = Number(quote);
    });

  }

  getcustomerVehicleDetails(policyId:number) {
    this._policyService.getCustomerVehicleDetails(policyId)
    .subscribe(customerVehicle => this.customerVehicle = <CustomerVehicle>customerVehicle);

  }

  showPolicyList(){
    document.getElementById("policylist").style.display = "block";
    document.getElementById("policydetails").style.display = "none";
  }

}
