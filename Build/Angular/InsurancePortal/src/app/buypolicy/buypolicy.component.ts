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

  make: string = "";
  model: string = "";
  submodel: string = "";
  cc: string = "";
  fuelType: string = "";
  vehicleRegNo: string = "";
  engineNo: string = "";
  chasisNo: string = "";
  manufacturingYear: number;
  registrationDate: string = "";
  vehicleRegCity: string = "";

  price: number;
  datepipe: DatePipe;

  //Payment Data
  policyPayment: PolicyPayment;
  cardNo: string = "";
  nameOnCard: string = "";
  cvv: number;
  cardExpiryMonth: number;
  cardExpiryYear: number;

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
      //console.log(data);
      this.vehicleMasterList = <VehicleMaster[]>data

    });
  }

  getVehicleMake() {
    this._VehicleMasterService.getVehicleMake().subscribe(data => {
      this.makeList = <string[]>data

    });
  }

  showTab(n) {
    //console.log("in on showTab " + n);
    // This function will display the specified tab of the form...

    var x = document.getElementsByClassName("tab1");

    for (let i = 0; i < x.length; i++) {
      x[i].setAttribute("class", "tab");
    }

    var x = document.getElementsByClassName("tab");
    //console.log(x[n]);


    for (let i = 0; i < x.length; i++) {
      if (i == n) {
        //console.log(i+" "+ n);
        x[i].setAttribute("class", "tab1");
      } else {
        //console.log(i+" "+ n);
        x[i].setAttribute("class", "tab");
      }



    }
    console.log("prev and next " + n + " " + x.length);
    //... and fix the Previous/Next buttons:
    if (n == 0) {
      document.getElementById("prevBtn").style.display = "none";
    } else {
      document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length)) {
      document.getElementById("nextBtn").innerHTML = "Submit";
      
    } else {
      document.getElementById("nextBtn").innerHTML = "Next";
    }

    this.fixStepIndicator(n);
  }

  submit(){
    let user:User;
    user= JSON.parse(localStorage.getItem("currentUser"));
    let username= user.username;

    this.policyPayment.nameOnCard= this.nameOnCard;
    this.policyPayment.cardNo=this.cardNo;
    this.policyPayment.cvv=this.cvv;
    this.policyPayment.cardExpiryMonth=this.cardExpiryMonth;
    this.policyPayment.cardExpiryYear=this.cardExpiryYear;
    this.policyPayment.amount=this.price;

    
    this._policyService.addPolicy(username,this.customerVehicle,this.policyPayment)

  }


  handleClick(event: Event, n: any) {
    this.nextPrev(n);
  }

  nextPrev(n) {
    console.log("in nextPrev ", n);
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab1");
    console.log(x[0]);
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !this.validateForm()) return false;
    // Hide the current tab:
    
    x[0].setAttribute("class", "tab");
    //x[this.currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    this.currentTab = this.currentTab + n;
    
    // if you have reached the end of the form...
    var x = document.getElementsByClassName("tab");
    if (this.currentTab >= x.length) {
      console.log(this.nameOnCard);
      document.getElementById("nextBtn").setAttribute("type","submit");
      this.submit();
      return false;
    }

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

  validateForm() {
    // This function deals with validation of the form fields
    let valid=true;
    if(this.currentTab==0){
      valid = this.validateform1();
    }else if (this.currentTab==1){

    }
    else if(this.currentTab==2){
      valid = this.validateform1();
    }
      // If the valid status is true, mark the step as finished and valid:
    if (valid) {
      document.getElementsByClassName("step")[this.currentTab].className += " finish";
    }
    return valid; // return the valid status
    //return true;
  }

  validateform1():boolean{
    var x, y, i, z, valid = true;
    x = document.getElementsByClassName("tab1");
    
      y = x[0].getElementsByTagName("input");
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
    
    z = x[0].getElementsByTagName("select");

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

  makeChange(e) {
    //console.log(e.target.value);

    // var x = document.getElementById(id);
    // x.className="";
    this.make = e.target.value;
    let i = 0;
    this.modelList = [];
    this.vehicleMasterList.forEach(element => {
      // console.log(element.make);

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
