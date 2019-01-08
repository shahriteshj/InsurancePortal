import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable()
export class LoginRouteGuard implements CanActivate {

  constructor(private userService: UserService,private router:Router) {}

  canActivate() {
    let isUserLogin = this.userService.isValid();
    if(!(isUserLogin)){
      this.router.navigate(['/login']);
    }
    return isUserLogin;
  }
}