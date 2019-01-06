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
  customerVehicle: CustomerVehicle={make:"",model:"",submodel:"",cc:"",vehicleRegNo:"",engineNo:"",chasisNo:"",
  fuelType:"",manufacturingYear:0,registrationDate:"",vehicleRegCity:""};
  policyPayment: PolicyPayment={cardNo: "", nameOnCard: "", cvv: 0, cardExpiryMonth: 0, cardExpiryYear: 0, amount: 0};
  customer:Customer={firstName: "", lastName: "", emailId: "", mobileNo: "",
    address1: "", address2: "", address3: "", gender: "", pincode: 0,
    state: "", city: "", DOB: new Date()};
  policy: Policy;
  policyId: number;
  constructor(private router: Router, private _policyService: PolicyService,private sharedDataService: SharedDataService) { }

  ngOnInit() {
    console.log(this.sharedDataService.isCustomerUser);
    document.getElementById("policydetails").style.display = "none";
    this.getPolicyList();
  }

  getPolicyList() {

    return this._policyService.getPolicyList(localStorage.getItem("username"), localStorage.getItem("role"))
      .subscribe(policyList => {
        console.log(policyList);
        this.policyList = <Policy[]>policyList;
      });
  }



  getPolicyDetails() {

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
      policyPayment=> this.policyPayment=<PolicyPayment>policyPayment
    );
  }

  getCustomerDetails() {
    this._policyService.getCustomerDetails(this.policyId).subscribe(
      customer=> this.customer=<Customer>customer
    );
  }
}
