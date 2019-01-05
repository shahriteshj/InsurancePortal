import { Component, OnInit } from '@angular/core';
import { SharedDataService } from '../service/sharedData.service';
import { LocalStorageService } from 'ngx-webstorage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  constructor(private _router: Router,
    private localStorage: LocalStorageService,
    private sharedDataService: SharedDataService) { }

  ngOnInit() {
    console.log(this.sharedDataService.isCustomerUser);
  }

}
