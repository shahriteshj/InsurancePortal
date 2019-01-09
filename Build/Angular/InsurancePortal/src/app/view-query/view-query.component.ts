import { Component, OnInit } from '@angular/core';
import { Query } from '../model/Query';
import { QueryService } from '../service/query.service';
import { User } from '../model/user';
import { LocalStorageService } from 'ngx-webstorage';
import { SharedDataService } from '../service/sharedData.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-query',
  templateUrl: './view-query.component.html',
  styleUrls: ['./view-query.component.css']
})

export class ViewQueryComponent implements OnInit {

  queries: Query[] = [];
  user: User;  
  query:Query;
  msg: string = null;
  queryId: number;
    
  constructor(private queryService: QueryService,
    private localStorage: LocalStorageService,
    private sharedDataService: SharedDataService,
    private _router:Router) { }
    

  ngOnInit() {
    this.getQueryList();
    console.log(this.sharedDataService.isCustomerUser);
    document.getElementById("queryDetails").style.display = "none";
    console.log(this.queryId);

    
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
    .subscribe(query => {console.log(query); this.query = <Query>query;}
    );
  }

  updateQueryDetails() {
    console.log(this.query)
    this.queryService.updateQueryDetails(this.query).subscribe(
      (query:any) => {      
        console.log(query)        
        if (query.response == "Success") {
          alert("Query Ref # " + this.queryId +" updated sucessfully.");
          this._router.navigate(['/viewquery']);          
        } else {                         
          alert("Failed to update query.");
        }
    })
  }

  updateQuery() {    
   this.updateQueryDetails()
   this.showQueryList()
  }
}
