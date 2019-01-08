import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
  })
  export class MasterDataService {
    constructor(private http: HttpClient) { }
  
    getStateList() {
      return this.http.get("/InsurancePortal/policy/stateList");
    }

    getCityList(stateName:string){
        return this.http.get("/InsurancePortal/policy/cityList?stateName="+stateName);

    }
}