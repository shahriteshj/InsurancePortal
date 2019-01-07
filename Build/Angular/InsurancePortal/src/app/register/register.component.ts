import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { AlertService } from '../service/alert.service';
import { Customer } from '../model/Customer';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  loading = false;
  user:User;
  customer:Customer;

  constructor(private router: Router, private _userService: UserService,
    private alertService: AlertService) { }

  ngOnInit() {
  }


  Register(registerFrm) {

    this.loading = true;

    let customer:Customer = {
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
    DOB: registerFrm.value.DOB
    }

     let user: User = {
       name: registerFrm.value.name,
       username: registerFrm.value.username,
       password: registerFrm.value.password,
       roleName:"CUSTOMER",
       questionId:registerFrm.value.questionId,
       securityAnswer:registerFrm.value.securityAnswer,
       lastSuccessfulLoginDate:"",
       responseText:""
     }
     console.log(user);
    
     this._userService.create(user,customer)
       .subscribe(
         data => {
           this.alertService.success('Registration successful', true);
           this.router.navigate(['/login']);
         },
         error => {
           this.alertService.error(error);
           this.loading = false;
         }
       );


  }

}
