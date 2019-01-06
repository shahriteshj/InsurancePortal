import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleMasterService } from '../service/vehiclemaster.service';
import { VehicleMaster } from '../model/VehicleMaster';
import { CustomerVehicle } from '../model/CustomerVehicle';
import { PolicyService } from '../service/policy.service';
import { DatePipe } from '@angular/common';
import { NgForm } from '@angular/forms';
import { User } from '../model/user';
import { PolicyPayment } from '../model/PolicyPayment';

@Component({
  selector: 'app-buypolicy',
  templateUrl: './buypolicy.component.html',
  styleUrls: ['./buypolicy.component.css']
})
export class BuypolicyComponent implements OnInit {

  vehicleMasterList: VehicleMaster[];
  customerVehicle: CustomerVehicle;
  currentTab = 0;

  makeList: string[] = [];
  modelList: string[] = [];
  submodelList: string[] = [];

  make: string = "HYUNDAI";
  model: string = "i10";
  submodel: string = "";
  cc: string = "123";
  fuelType: string = "PETROL";
  vehicleRegNo: string = "123";
  engineNo: string = "123";
  chasisNo: string = "123";
  manufacturingYear: number;
  registrationDate: string = "01/01/2010";
  vehicleRegCity: string = "MUMBAI";

  price: number;
  datepipe: DatePipe;

  //Payment Data
  cardNo: string = "1234-5678-9101";
  nameOnCard: string = "Name";
  cvv: number;
  cardExpiryMonth: number = 10;
  cardExpiryYear: number = 2020;

  policyId:number;

  constructor(private router: Router, private _VehicleMasterService: VehicleMasterService,
    private _policyService: PolicyService) { }

  ngOnInit() {
    console.log("in on init");
    this.getVehicleMasterList();
    this.getVehicleMake();
    this.showTab(this.currentTab);
  }


  getVehicleMasterList() {
    this._VehicleMasterService.getVehicleList().subscribe(data => {
      this.vehicleMasterList = <VehicleMaster[]>data

    });
  }

  getVehicleMake() {
    this._VehicleMasterService.getVehicleMake().subscribe(data => {
      this.makeList = <string[]>data

    });
  }

  showTab(n) {
    // This function will display the specified tab of the form...

    if(n==0){
      document.getElementById("tab1").style.display="block";
      document.getElementById("tab2").style.display="none";
      document.getElementById("tab3").style.display="none";
      document.getElementById("tab4").style.display="block";
      document.getElementById("tab5").style.display="none";
    }else if(n==1){
      document.getElementById("tab1").style.display="none";
      document.getElementById("tab2").style.display="block";
      document.getElementById("tab3").style.display="none";
      document.getElementById("tab4").style.display="block";
      document.getElementById("tab5").style.display="none";
    }else if(n==2){
      document.getElementById("tab1").style.display="none";
      document.getElementById("tab2").style.display="none";
      document.getElementById("tab3").style.display="block";
      document.getElementById("tab4").style.display="none";
      document.getElementById("tab5").style.display="block";
    }
    //... and fix the Previous/Next buttons:
    if (n == 0) {
      document.getElementById("prevBtn").style.display = "none";
    } else {
      document.getElementById("prevBtn").style.display = "inline";
    }
 
    this.fixStepIndicator(n);
  }

  submit(frm:NgForm):void{
    
    let username= localStorage.getItem("username");
    console.log(username);
     this.nameOnCard=frm.value.nameOnCard;
    let policyPayment:PolicyPayment= {cardNo:frm.value.cardNo,nameOnCard:frm.value.nameOnCard,
      cvv:frm.value.cvv,cardExpiryMonth:frm.value.cardExpiryMonth,cardExpiryYear: frm.value.cardExpiryYear,
      amount: this.price};
        
    this._policyService.addPolicy(username,this.customerVehicle,policyPayment).subscribe(policyId=>{console.log(policyId);
      this.policyId=<number>policyId
      this.router.navigate(['/policysuccess']);
    });

  }


  handleClick(event: Event, n: any) {
    this.nextPrev(n);
  }

  nextPrev(n) {
    console.log("in nextPrev ", n);
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !this.validateForm()) return false;
    
    // Increase or decrease the current tab by 1:
    this.currentTab = this.currentTab + n;
    
    console.log("currentTab: " + this.currentTab);
    if (this.currentTab == 1) {
      // Get policy Quote
      console.log("inside Loop");
      this.loadCustomerVehcle();
      console.log(this.customerVehicle);
      this._policyService.getQuote(this.customerVehicle).subscribe(quote => {
        console.log(quote);
        this.price = Number(quote)
      });
    }
    // Otherwise, display the correct tab:
    this.showTab(this.currentTab);
  }


  validateForm() {
    // This function deals with validation of the form fields
    let valid=true;
    if(this.currentTab==0){
      valid = this.validateform1(this.currentTab+1);
    }else if (this.currentTab==1){

    }
    else if(this.currentTab==2){
      valid = this.validateform1(this.currentTab+1);
    }
      // If the valid status is true, mark the step as finished and valid:
    if (valid) {
      document.getElementsByClassName("step")[this.currentTab].className += " finish";
    }
    return valid; // return the valid status
  }

  validateform1(n):boolean{
    var x, y, i, z, valid = true;

    x = document.getElementById("tab"+n);
    
      y = x.getElementsByTagName("input");
      // A loop that checks every input field in the current tab:
      for (i = 0; i < y.length; i++) {
        // If a field is empty...
        if (y[i].value == "") {
          // add an "invalid" class to the field:
          y[i].className += " invalid";
          // and set the current valid status to false
          valid = false;
        } else {
          y[i].className = "";
        }
      }
    
    z = x.getElementsByTagName("select");

    for (i = 0; i < z.length; i++) {
      // If a field is empty...

      var sel = z[i].selectedIndex;
      var opt = z[i].options[sel].value;

      console.log(opt);
      if (opt == "") {
        // add an "invalid" class to the field:
        z[i].className = " invalid";
        // and set the current valid status to false
        valid = false;
      }
    }
    return valid;
  }

  
  fixStepIndicator(n) {

    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
      x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class on the current step:
    x[n].className += " active";
  }

  loadCustomerVehcle() {
    console.log(this.chasisNo);
    let customerVehicle1: CustomerVehicle = {
      make: this.make,
      model: this.model,
      submodel: this.submodel,
      cc: this.cc,
      fuelType: this.fuelType,
      engineNo: this.engineNo,
      chasisNo: this.chasisNo,
      vehicleRegNo: this.vehicleRegNo,
      manufacturingYear: this.manufacturingYear,
      registrationDate: this.registrationDate,
      vehicleRegCity: this.vehicleRegCity
    }


    let customerVehicle2: CustomerVehicle = {
      make: "HYUNDAI",
      model: "i10",
      submodel: "Dlite",
      cc: "1866",
      fuelType: "PETROL",
      engineNo: "123456789",
      chasisNo: "123456789",
      vehicleRegNo: "123456789",
      manufacturingYear: Number("2016"),
      registrationDate: "01/01/2016",
      vehicleRegCity: "Mumbai"
    }

    this.customerVehicle = customerVehicle2;
    console.log(this.customerVehicle);
  }


  makeChange(e) {
    this.make = e.target.value;
    let i = 0;
    this.modelList = [];
    this.vehicleMasterList.forEach(element => {

      if (element.make == e.target.value) {
        this.modelList[i] = element.model;
        i++;
      }

    });
    //console.log(this.modelList);
    let unique = new Set(this.modelList);
    this.modelList = Array.from(unique);
    this.submodelList = [];

  }
  modelChange(evt) {
    // console.log(evt.target.value)
    this.submodel = evt.target.value;
    let i = 0;
    this.submodelList = [];
    this.vehicleMasterList.forEach(element => {
      //console.log(element.make);

      if (element.make == this.make) {
        if (element.model == evt.target.value) {
          this.submodelList[i] = element.submodel;
          i++;
        }
      }

    });
    //console.log(this.submodelList);
    let unique = new Set(this.submodelList);
    this.submodelList = Array.from(unique);


  }

  submodelChange(event) {
    //console.log(event.target.value);
    this.submodel = event.target.value;
    this.vehicleMasterList.forEach(element => {
      //console.log(element.make);

      if (element.make == this.make && element.model == this.model) {
        if (element.submodel == event.target.value) {
          this.cc = element.cc;
        }
      }

    });
  }

}
