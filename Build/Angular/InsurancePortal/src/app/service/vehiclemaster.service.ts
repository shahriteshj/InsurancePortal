import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class VehicleMasterService {


  constructor(private http: HttpClient) { }

  getVehicleList() {
    return this.http.get('/InsurancePortal/policy/getAllVehicles');
  }

  getVehicleMake() {
    return this.http.get('/InsurancePortal/policy/getVehicleMake');
  }
}