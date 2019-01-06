import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PolicyService } from '../service/policy.service';
import { Policy } from '../model/Policy';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyPayment } from '../model/PolicyPayment';

@Component({
  selector: 'app-viewpolicy',
  templateUrl: './viewpolicy.component.html',
  styleUrls: ['./viewpolicy.component.css']
})
export class ViewpolicyComponent implements OnInit {

  policyList:Policy[];
  customerVehicle: CustomerVehicle;
  policyPayment:PolicyPayment;
  policy:Policy;
  policyId:number;
  constructor(private router: Router,private _policyService: PolicyService) { }

  ngOnInit() {
    document.getElementById("policydetails").style.display="none";
      this.getPolicyList();
  }

  getPolicyList(){

      return this._policyService.getPolicyList(localStorage.getItem("username"),localStorage.getItem("role"))
      .subscribe(policyList=>{
        console.log(policyList);
        this.policyList=<Policy[]>policyList;
      });
  }



  getPolicyDetails(){

  }

  viewDetails(id:number){
      document.getElementById("policylist").style.display="none";
      document.getElementById("policydetails").style.display="block";
      this.policyId=id;
  }

  showPolicyList(){
    document.getElementById("policylist").style.display="block";
    document.getElementById("policydetails").style.display="none";
  }
}
