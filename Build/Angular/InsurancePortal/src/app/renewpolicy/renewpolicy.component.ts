import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PolicyService } from '../service/policy.service';
import { Policy } from '../model/Policy';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyPayment } from '../model/PolicyPayment';
import { Customer } from '../model/Customer';
import { SharedDataService } from '../service/sharedData.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-renewpolicy',
  templateUrl: './renewpolicy.component.html',
  styleUrls: ['./renewpolicy.component.css']
})
export class RenewpolicyComponent implements OnInit {

  constructor(private router: Router, private _policyService: PolicyService,
    private sharedDataService: SharedDataService) { }
  
  errorMessage:string;
  renewPolicyList:Policy[];
  private today: any;
  customerVehicle: CustomerVehicle;
  policyPayment: PolicyPayment;
  price:number;
  policyId:number;

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
    this.policyId=policyId;
    this.getcustomerVehicleDetails(policyId);
    this._policyService.getQuoteByPolicyId(policyId).subscribe(quote => {
      console.log(quote);
      this.price = Number(quote);
    });

  }

  getcustomerVehicleDetails(policyId:number) {
    this._policyService.getCustomerVehicleDetails(policyId)
    .subscribe(customerVehicle => {this.customerVehicle = <CustomerVehicle>customerVehicle
    },
    (error:any)=>{
      console.log(error);
      console.log((error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : 'Server error');
      this.errorMessage="System Error. Please try after sometime";
    });

  }

  showPolicyList(){
    document.getElementById("policylist").style.display = "block";
    document.getElementById("policydetails").style.display = "none";
  }

  submit(frm:NgForm){
    let username= localStorage.getItem("username");
    console.log(username);

    let policyPayment:PolicyPayment= {cardNo:frm.value.cardNo,nameOnCard:frm.value.nameOnCard,
      cvv:frm.value.cvv,cardExpiryMonth:frm.value.cardExpiryMonth,cardExpiryYear: frm.value.cardExpiryYear,
      policyAmount: this.price};
    
      console.log(policyPayment);
      this._policyService.renewPolicy(username,this.policyId,policyPayment).subscribe(policyId=>{console.log(policyId);
      this.policyId=<number>policyId
      this.router.navigate(['/viewpolicy']);
    },
    (error:any)=>{
      console.log(error);
      console.log((error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : 'Server error');
      this.errorMessage="System Error renewing Policy. Please try after sometime";
    });
  }
}


