import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { SharedDataService } from '../service/sharedData.service';

@Component({
  selector: 'app-log-out',
  templateUrl: './log-out.component.html',
  styleUrls: ['./log-out.component.css']
})
export class LogOutComponent implements OnInit {

  constructor(private sharedDataService : SharedDataService,private router: Router) { }

  ngOnInit( ) {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('username');
    localStorage.removeItem('role');
    this.sharedDataService.clear();
    this.router.navigate(['/home']);
  }

 

}
