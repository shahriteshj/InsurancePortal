import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../service/sharedData.service';
import { Router } from '@angular/router';
import { PolicyService } from '../service/policy.service';
import { Policy } from '../model/Policy';

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {
  renewPolicyList: any[];
  constructor(private router: Router, private _policyService: PolicyService,private sharedDataService: SharedDataService) { }

  ngOnInit() {
    this.getPolicyListForClaim();
  }

  getPolicyListForClaim(){
    
    return this._policyService.getPolicyList(localStorage.getItem("username"), 
    localStorage.getItem("role")).subscribe(policyList => {
      console.log(policyList);
      this.renewPolicyList = <Policy[]>policyList;
    });
  }
}
