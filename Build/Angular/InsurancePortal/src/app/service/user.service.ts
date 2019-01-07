import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { map } from "rxjs/operators";
import { Customer } from '../model/Customer';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get('http://localhost:3000/User');
  }

  // authenticateUser(username:string,password:string){
  //   return this.http.get("http://localhost:3000/User?username="+username+"&password="+password).pipe(
  //   map(user => {
  //               // login successful if there's a jwt token in the response
  //               if (user) {
  //                   localStorage.setItem('currentUser', JSON.stringify(user));
  //               }
  //               return user;
  //           }));

  // }

  authenticateUser(username:string,password:string){
    return this.http.post<User>("/InsurancePortal/policy/authenticateUser",{ username: username, password: password });

  }

  create(user: User,customer:Customer) {
    let z = Object.assign(user,customer);
    console.log(z);
    return this.http.post("/InsurancePortal/policy/registerUser", z);
  }

  update(user: User) {
    return this.http.put('http://localhost:3000/User/' + user.id, user);
  }

  delete(id: number) {
    return this.http.delete('http://localhost:3000/User/' + id)
  }

}
