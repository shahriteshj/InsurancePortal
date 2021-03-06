import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { map } from "rxjs/operators";
import { Query } from '../model/Query';


@Injectable({
  providedIn: 'root'
})
export class QueryService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get('http://localhost:3000/query');
  }

 

  addQuery(query:Query){
    return this.http.post("/InsurancePortal/policy/submitQuery",query);

  }

  create(user: User) {
    return this.http.post("http://localhost:3000/User", user);
  }

  update(user: User) {
    return this.http.put('http://localhost:3000/User/' + user.id, user);
  }

  delete(id: number) {
    return this.http.delete('http://localhost:3000/User/' + id)
  }

  getQueryList(username:String,role:String) {
    return this.http.post("/InsurancePortal/policy/getQueryList",{username:username,roles:role});
  }

  getQueryDetails(queryId:number) {    
    return this.http.get("/InsurancePortal/policy/getQueryDetails?queryId="+queryId);
  }

  updateQueryDetails(query:Query) {
    console.log(query);
    return this.http.post("/InsurancePortal/policy/updateQuery",query);
  }
}
