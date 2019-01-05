import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { LocalStorageService } from 'ngx-webstorage';
import { SharedDataService } from '../service/sharedData.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loading = false;
  users: User[];
  user: User;
  username: string;
  password: string;


  constructor(
    private _router: Router,
    private localStorage: LocalStorageService,
    private sharedDataService: SharedDataService, private _userService: UserService) {

  }


  ngOnInit() {

  }


  Login(frm: NgForm): void {
    let isValid: Boolean = false;
    this.loading = true;
    this.username = frm.value.username;
    this.password = frm.value.password;
    this._userService.authenticateUser(this.username, this.password).subscribe(
      data => {
        console.log(data);
        this.user = <User>data;
        this.sharedDataService.isUserLoggedIn = true;
        localStorage.setItem('currentUser', JSON.stringify(data));
        localStorage.setItem('username', data.username);
        localStorage.setItem('role', data.roleName);
        console.log(data.roleName);
        if (data.responseText == "SUCCESS") {
          if (data.roleName.toLowerCase() == 'admin') {

            this.sharedDataService.isAdminUser = true;
            isValid = true;
            this._router.navigate(['/admin']);
          }
          else if (data.roleName.toLowerCase() == 'operations') {
            this.sharedDataService.isOperationsUser = true;
            isValid = true;
            this._router.navigate(['/operations']);
          }
          else if (data.roleName.toLowerCase() == 'manager') {
            this.sharedDataService.isManagerUser = true;
            isValid = true;
            this._router.navigate(['/manager']);
          }else if (data.roleName.toLowerCase() == 'customer') {
            this.sharedDataService.isCustomerUser = true;
            isValid = true;
            this._router.navigate(['/customer']);
          }
          else {
            isValid = true;
            this._router.navigate(['/user']);
          }

        } else {
          alert('LoginFailed....');
          this._router.navigate(['/login']);
          this.loading = false;
        }
        this.localStorage.store("valid", isValid);
      },
      error => {
        console.log("error");
        console.log(error);
        this.loading = false;
      }
    );
  }





}


