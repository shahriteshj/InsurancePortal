import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { UserLoginService } from './userLogin.service';

@Injectable()
export class LoginRouteGuard implements CanActivate {

  constructor(private userLoginService: UserLoginService,private router:Router) {}

  canActivate() {
    let isUserLogin = this.userLoginService.isValid();
    if(!(isUserLogin)){
      this.router.navigate(['/login']);
    }
    return isUserLogin;
  }
}