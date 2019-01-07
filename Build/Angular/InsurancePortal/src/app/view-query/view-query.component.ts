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

  queries: Query[] = [];
  user: User;
  query:Query={emailId: "", queryDescription: "", status: "",name: "", queryType: "",queryResponse: "",assignedTo: ""};
  
  constructor(private queryService: QueryService) { }
  queryId: number;

  ngOnInit() {
    this.getQueryList();
    document.getElementById("queryDetails").style.display = "none";
  }

  getQueryList() {
    let user: User;
    user = JSON.parse(localStorage.getItem("currentUser"));
    let role = user.roleName;
    let username = user.username;
    console.log(role);
    return this.queryService.getQueryList(username, role).subscribe(queries =>
      this.queries = <Query[]>queries);
  }

  viewQueryDetails(id: number) {
    document.getElementById("queryList").style.display = "none";
    document.getElementById("queryDetails").style.display = "block";
    this.queryId = id;
     this.getQueryDetails();
  }

  showQueryList() {
    document.getElementById("queryList").style.display = "block";
    document.getElementById("queryDetails").style.display = "none";
  }

  getQueryDetails() {
    this.queryService.getQueryDetails(this.queryId)
    .subscribe(query => this.query = <Query>query);
  }
}
