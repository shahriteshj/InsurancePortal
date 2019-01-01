import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { AlertService } from '../service/alert.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  loading = false;
  users: User[];

  constructor(private router: Router, private _userService: UserService,
    private alertService: AlertService) { }

  ngOnInit() {
  }


  Register(registerFrm) {

    this.loading = true;
    let name = registerFrm.value.name;
    let username = registerFrm.value.username;
    let password = registerFrm.value.password;
    console.log(name);
    console.log(username);
    console.log(password);
    
    let user: User = {
      name: registerFrm.value.name,
      username: registerFrm.value.username,
      password: registerFrm.value.password
    }
    console.log(user);
    
    this._userService.create(user)
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
