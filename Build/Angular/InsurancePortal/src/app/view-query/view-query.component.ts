import { Component, OnInit } from '@angular/core';
import { Query } from '../model/Query';
import { QueryService } from '../service/query.service';
import { User } from '../model/user';

@Component({
  selector: 'app-view-query',
  templateUrl: './view-query.component.html',
  styleUrls: ['./view-query.component.css']
})

export class ViewQueryComponent implements OnInit {

  queries:Query[]=[];
  user:User;
  
  

  constructor(private httpClientService:QueryService) { }
  queryId:number;

  ngOnInit() {
    this.getQueryList();
  }

  getQueryList() {
    let user:User;
    user = JSON.parse(localStorage.getItem("currentUser"));
    
    let role = user.roleName;
    let username = user.username;
    console.log(role);

    return this.httpClientService.getQueryList(username,role).subscribe(queries=>
      this.queries=<Query[]>queries);
  }
}
