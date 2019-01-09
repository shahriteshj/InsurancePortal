import { Component, OnInit } from '@angular/core';
import { Query } from '../model/Query';
import { QueryService } from '../service/query.service';
import { NgForm, FormGroup } from '@angular/forms';
import { User } from '../model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.css']
})
export class QueryComponent implements OnInit {

  query:Query;


  constructor(private _queryService: QueryService ,private _router: Router ) { }

  ngOnInit() {
   
  }

 

  submitQuery(frm: NgForm) {

    
    let group: FormGroup = frm.control
    //this.logKeyValuePairs(group);
    let user:User;
    user = JSON.parse(localStorage.getItem("currentUser"));
    
    let name1 = user.name;
    let username1 = user.username;
    console.log(name1);
    console.log(username1);
    
    let query: Query = {
      name: name1,
      emailId: username1,
      queryType: frm.value.queryType,
      queryDescription:frm.value.queryDescription,
      assignedTo:null,
      queryResponse:null,
      status:"IN PROGRESS"
      
    }
    console.log(query);
    
    this._queryService.addQuery(query)
      .subscribe(
        (data:Query) => {
          console.log("Success");
          alert("Thanks for submitting the query. \n Please note Query Ref: " + data.queryId + " for future reference");
          this._router.navigate(['/viewquery']);
        },
        error => {
              console.log("Failure");     
        }
      );

  }

  logKeyValuePairs(group: FormGroup): void {

    Object.keys(group.controls).forEach((key: string) => {
      const abstractControl = group.get(key);
      if (abstractControl instanceof FormGroup) {
        this.logKeyValuePairs(abstractControl);
      } else {
        console.log('Key: ' + key + ' Value: ' + abstractControl.value);
      }
    }
    );
  }


}
