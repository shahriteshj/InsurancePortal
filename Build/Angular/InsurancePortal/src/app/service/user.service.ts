import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { map } from "rxjs/operators";
import { Customer } from '../model/Customer';
import { LocalStorage, LocalStorageService } from 'ngx-webstorage';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  @LocalStorage()
    public valid: boolean;

  constructor(private localStorage: LocalStorageService,private http: HttpClient) { }

  authenticateUser(username:string,password:string){
    return this.http.post<User>("/InsurancePortal/policy/authenticateUser",{ username: username, password: password });

  }

  create(user: User,customer:Customer) {
    let z = Object.assign(user,customer);
    console.log(z);
    return this.http.post("/InsurancePortal/policy/registerUser", z);
  }

  checkUsernameExists(username:String){
    return this.http.get("/InsurancePortal/policy/checkUsername");
  }

  update(user: User) {
    return this.http.put('http://localhost:3000/User/' + user.id, user);
  }

  delete(id: number) {
    return this.http.delete('http://localhost:3000/User/' + id)
  }

  public isValid () : boolean
  {
      return this.localStorage.retrieve("valid");
  }

}
