import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../service/sharedData.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userVerified:Boolean=true;
  constructor( private sharedDataService: SharedDataService) { }

  ngOnInit() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    this.sharedDataService.clear();
  }

}
