import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../service/sharedData.service';
import { LocalStorageService } from 'ngx-webstorage';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  constructor(private _router: Router,
    private localStorage: LocalStorageService,
    private sharedDataService: SharedDataService) {
     
      this._router.routeReuseStrategy.shouldReuseRoute = function(){
        return false;
     }
    

    this._router.events.subscribe((evt) => {
       if (evt instanceof NavigationEnd) {
          // trick the Router into believing it's last link wasn't previously loaded
          this._router.navigated = false;
          // if you need to scroll back to top, here is the right place
          window.scrollTo(0, 0);
       }
   });
  }

  ngOnInit() {
    console.log(this.sharedDataService.isUserLoggedIn);
    
    console.log(this.sharedDataService.isOperationsUser);
    console.log(this.sharedDataService.isCustomerUser);
    console.log(this.sharedDataService.isManagerUser);
  }

  logout(){
    localStorage.removeItem('currentUser');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    this.sharedDataService.clear();
    this._router.navigate(['/home']);
  }

}
