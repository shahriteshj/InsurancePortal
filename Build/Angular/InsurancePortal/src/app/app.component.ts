import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AmazingCart';
  static userLogged:boolean=false;

  static setUserLogged(flag:boolean){
    AppComponent.userLogged=flag;
  }

}
