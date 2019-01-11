import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { Customer } from '../model/Customer';
import { MasterDataService } from '../service/masterDataservice';
import { State } from '../model/State';
import { NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  loading = false;
  user: User;
  customer: Customer;
  state: string;
  city: string;
  stateList: string[];
  cityList: string[];
  regForm: NgForm;
  formBuilder: any;
  username: string;


  @ViewChild('usernameControl') el: ElementRef;

  constructor(private router: Router, private _userService: UserService,
    private _masterDataService: MasterDataService) { }

  ngOnInit() {

    this.getStateList();
    let today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    
    var yyyy = today.getFullYear();
    let strToday;
    if(mm<10){
      strToday = yyyy + "-0"+ mm+"-"+dd;
    }else{
      strToday = yyyy + "-"+ mm+"-"+dd;
    }
    
    let element = document.getElementById('dob');

    element.setAttribute("max",strToday);

  }
  validateUsername(): any {
    throw new Error("Method not implemented.");
  }

  getStateList() {
    this._masterDataService.getStateList().subscribe(stateList => {  this.stateList = <string[]>stateList });
  }

  getCityList(stateName: string) {
    this._masterDataService.getCityList(stateName).subscribe(cityList => {  this.cityList = <string[]>cityList });
  }



  Register(registerFrm) {

    if (!this.validateForm()) return false;

    this.loading = true;

    let customer: Customer = {
      firstName: registerFrm.value.firstName,
      lastName: registerFrm.value.lastName,
      emailId: registerFrm.value.username,
      mobileNo: registerFrm.value.mobileNo,
      address1: registerFrm.value.address1,
      address2: registerFrm.value.address2,
      address3: registerFrm.value.address3,
      gender: registerFrm.value.gender,
      pincode: registerFrm.value.pincode,
      state: registerFrm.value.state,
      city: registerFrm.value.city,
      DOB: registerFrm.value.dob
    }

    let user: User = {
      name: registerFrm.value.name,
      username: registerFrm.value.username,
      password: registerFrm.value.password,
      roleName: "CUSTOMER",
      questionId: registerFrm.value.securityQuestion,
      securityAnswer: registerFrm.value.securityAnswer,
      lastSuccessfulLoginDate: "",
      //responseText: ""
    }
    console.log(user);
    console.log(customer);
    this._userService.create(user, customer)
      .subscribe(
        (data: any) => {
          console.log("post create");
          console.log(data);
          if (data.response == "SUCCESS") {
            this.router.navigate(['/login']);
          } else if (data.response == "FAILURE") {
            alert("User Id is already");
            this.router.navigate(['/register']);
            this.loading = false;
          }
        },
        error => {
          this.router.navigate(['/register']);
          this.loading = false;
        }
      );


  }

  validateForm(){
    let valid = true;
   

    let x, y, i, z;

    let password = document.getElementById("password");
    let confirmPassword = document.getElementById("confirmPassword");

    // console.log(password.value);
    // console.log(confirmPassword.value);

    if(password.value!=confirmPassword.value){
      return false;
    }

//     var date = new Date();

// let birthdate = document.getElementById("dob");
// if(date > birthdate.value) {
//   return false;
// }

    return valid;
  }

  // birthdateError:boolean=false;

  // validateDate(birthdate:Date){
  //   console.log("in bday validate");
  //   let today = new Date();
  //   if(birthdate> today){
  //     this.birthdateError=true;
  //   }else{
  //     this.birthdateError=false;
  //   }

  // }

  usernameControl: string;

  checkUserExists(username: string) {

    console.log(username);
    this._userService.checkUsernameExists(username)
      .subscribe((data: any) => {
        console.log(data);
        if (data.response == "userFound") {
          this.username = "";
        }
      });
  }



  stateChange(e) {
    this.state = e.target.value;
    let selectedOptions = e.target['options'];
    let selectedIndex = selectedOptions.selectedIndex;
    let selectElementText = selectedOptions[selectedIndex].text;
    console.log(selectElementText + " " + this.state);
    this.state = selectElementText;
    
    this.getCityList(this.state);
  }

  check() {
    if (document.getElementById('password').value ==
      document.getElementById('confirmPassword').value) {
      document.getElementById('message').style.color = 'green';
      document.getElementById('message').innerHTML = 'Password and Confirm Password matching';
    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'Password and Confirm Password not matching';
    }
  }

}
