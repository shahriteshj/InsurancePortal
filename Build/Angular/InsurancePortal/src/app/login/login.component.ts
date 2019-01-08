import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { LocalStorageService, LocalStorage } from 'ngx-webstorage';
import { SharedDataService } from '../service/sharedData.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @LocalStorage()
  public valid: boolean;
  loading = false;
  users: User[];
  user: User;
  username: string;
  password: string;
  errorMessage: string = "";


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
    console.log(this.username);
    console.log(this.password);
    this._userService.authenticateUser(this.username, this.password).subscribe(
      (data: any) => {
        console.log(data);
        this.user = <User>data;
        this.sharedDataService.isUserLoggedIn = true;
        localStorage.setItem('currentUser', JSON.stringify(data));
        localStorage.setItem('username', data.username);
        localStorage.setItem('role', data.roleName);
        console.log(data.roleName);
         if (data.responseText == "SUCCESS") {
        //if (data.responseTextText == "SUCCESS") {
          console.log("I insert in admin")
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
          } else if (data.roleName.toLowerCase() == 'customer') {
            this.sharedDataService.isCustomerUser = true;
            isValid = true;
            this._router.navigate(['/customer']);
          }
          else {
            isValid = true;
            this._router.navigate(['/user']);
          }

        }
        else {
          console.log(data.responseText);
          if (data.responseText != null) {
            this.errorMessage = data.responseText;
          } else {
            this.errorMessage = "Username not found";
          }

          this._router.navigate(['/login']);
          this.loading = false;
        }
        this.localStorage.store("valid", isValid);
      },
      error => {
        console.log("error");
        console.log(error);
        this.errorMessage = "Username or password invalid";
        this.loading = false;
      }
    );
  }

  public isValid(): boolean {

    return this.localStorage.retrieve("valid");
  }

}


