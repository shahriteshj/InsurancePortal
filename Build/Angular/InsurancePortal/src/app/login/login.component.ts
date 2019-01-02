import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { AlertService } from '../service/alert.service';



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
  

  constructor(private router: Router, private _userService: UserService,
    private alertService: AlertService) { }

  ngOnInit() {
    localStorage.removeItem('currentUser');
  }


  Login(frm: NgForm) {
    this.loading = true;
    this.username = frm.value.username;
    this.password = frm.value.password;
    this._userService.authenticateUser(this.username, this.password).subscribe(
      data => {
        
        this.user = <User>data;
        //let strUser = JSON.parse(localStorage.getItem('currentUser'));
        //console.log(strUser);
        //console.log(data.get("username"));
        //this.username = strUser[0];
        //if (this.user != undefined) {
        if(this.user.responseText=="SUCCESS") {
          console.log("success");
          this.router.navigate(['/menu']);
        } else {
          console.log("failure");
          this.router.navigate(['/login']);
          this.loading = false;
        }
      },
      error => {
        console.log("error");
        console.log(error);
        this.alertService.error(error);
        this.loading = false;
      }
    );
  }





}


