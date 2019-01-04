import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VehicleMasterService } from '../service/vehiclemaster.service';
import { VehicleMaster } from '../model/VehicleMaster';

@Component({
  selector: 'app-buypolicy',
  templateUrl: './buypolicy.component.html',
  styleUrls: ['./buypolicy.component.css']
})
export class BuypolicyComponent implements OnInit {

  vehicleMasterList: VehicleMaster[];
  currentTab = 0;
  makeList: string[] = [];
  modelList: string[] = [];
  submodelList: string[] = [];
  make: string = "";
  model: string = "";
  submodel: string = "";
  cc: string = "";


  constructor(private router: Router, private _VehicleMasterService: VehicleMasterService) { }

  ngOnInit() {
    console.log("in on init");
    this.getVehicleMasterList();
    this.getVehicleMake();
    this.showTab(this.currentTab);
  }


  getVehicleMasterList() {
    this._VehicleMasterService.getVehicleList().subscribe(data => {
      console.log(data);
      this.vehicleMasterList = <VehicleMaster[]>data
      // this.getVehicleMake(<VehicleMaster[]>data);
    });
  }

  getVehicleMake() {
    this._VehicleMasterService.getVehicleMake().subscribe(data => {
      console.log(data);
      this.makeList = <string[]>data
      // this.getVehicleMake(<VehicleMaster[]>data);
    });
  }

  showTab(n) {
    console.log("in on showTab " + n);
    // This function will display the specified tab of the form...

    var x = document.getElementsByClassName("tab1");
    console.log("tab1 " + x.length);

    for (let i = 0; i < x.length; i++) {
      x[i].setAttribute("class", "tab");
    }

    var x = document.getElementsByClassName("tab");
    //console.log(x[n]);
    console.log("tab " + x.length);


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
    //... and run a function that will display the correct step indicator:
    this.fixStepIndicator(n);
  }

  handleClick(event: Event, n: any) {
    //console.log('Click!', event);
    //console.log(n);
    this.nextPrev(n);
  }

  nextPrev(n) {
    console.log("in nextPrev ", n);
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !this.validateForm()) return false;
    // Hide the current tab:
    //x[this.currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    this.currentTab = this.currentTab + n;
    // if you have reached the end of the form...
    if (this.currentTab >= x.length + 1) {
      // ... the form gets submitted:
      //document.getElementById("regForm").submit();
      return false;
    }
    // Otherwise, display the correct tab:
    this.showTab(this.currentTab);
  }

  validateForm() {
    // This function deals with validation of the form fields
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[this.currentTab].getElementsByTagName("input");
    // A loop that checks every input field in the current tab:
    for (i = 0; i < y.length; i++) {
      // If a field is empty...
      if (y[i].value == "") {
        // add an "invalid" class to the field:
        y[i].className += " invalid";
        // and set the current valid status to false
        valid = true;
      }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
      document.getElementsByClassName("step")[this.currentTab].className += " finish";
    }
    //return valid; // return the valid status
    return true;
  }

  fixStepIndicator(n) {
    console.log("in first step indicator")
    // This function removes the "active" class of all steps...
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
      x[i].className = x[i].className.replace(" active", "");
    }
    //... and adds the "active" class on the current step:
    x[n].className += " active";
  }

  // getVehicleMake(vehicleMasterList: VehicleMaster[]) {
  //   console.log(vehicleMasterList);
  //   let make:string[];
  //   vehicleMasterList.forEach(element => {
  //     console.log(element.make);
  //     make.push(element.make);
  //   });
  //   this.makeList=make;
  // }

  makeChange(e) {
    console.log(e.target.value);
    this.make = e.target.value;
    let i = 0;
    this.modelList = [];
    this.vehicleMasterList.forEach(element => {
      console.log(element.make);

      if (element.make == e.target.value) {
        this.modelList[i] = element.model;
        i++;
      }

    });
    console.log(this.modelList);
    let unique = new Set(this.modelList);
    this.modelList = Array.from(unique);
    this.submodelList = [];

  }
  modelChange(evt) {
    console.log(evt.target.value)
    this.submodel = evt.target.value;
    let i = 0;
    this.submodelList = [];
    this.vehicleMasterList.forEach(element => {
      console.log(element.make);

      if (element.make == this.make) {
        if (element.model == evt.target.value) {
          this.submodelList[i] = element.submodel;
          i++;
        }
      }

    });
    console.log(this.submodelList);
    let unique = new Set(this.submodelList);
    this.submodelList = Array.from(unique);


  }

  submodelChange(event) {
    console.log(event.target.value);

    this.vehicleMasterList.forEach(element => {
      console.log(element.make);

      if (element.make == this.make && element.model == this.model) {
        if (element.submodel == event.target.value) {
          this.cc = element.cc;
        }
      }

    });
  }

}
