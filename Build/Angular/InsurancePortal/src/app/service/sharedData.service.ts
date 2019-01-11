import { Injectable} from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';

@Injectable()
export class SharedDataService{
    isAdminUser : boolean;
    isManagerUser : boolean;
    isOperationsUser: boolean;
    isCustomerUser:boolean;
    isUserLoggedIn : boolean;
    username:string;
    lastSuccessfulLoginDate:string;
    

    constructor(private localStorage: LocalStorageService){
        console.log("shareddata service cons called");
        this.getStoredValues();
    }

    getStoredValues(){
        console.log(localStorage.getItem("user"));
        console.log(localStorage.getItem("valid"));
        console.log(this.localStorage.retrieve("valid"));
        console.log(this.localStorage.retrieve("user"));
        
        this.isUserLoggedIn =  (this.localStorage.retrieve("valid")==null) ? false : this.localStorage.retrieve("valid");
        this.isAdminUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'admin' ? true: false);
        this.isManagerUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'manager' ? true: false);
        this.isOperationsUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'operations' ? true: false);
        this.isCustomerUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'customer' ? true: false);
        this.username = this.localStorage.retrieve("username");
    }

    // getStoredValues(){
    //     console.log(localStorage.getItem("user"));
    //     console.log(localStorage.getItem("valid"));
    //     this.isUserLoggedIn =  (localStorage.getItem("valid")==null) ? false :(<String> localStorage.getItem("valid").toLowerCase()== 'true' ? true: false);
    //     this.isAdminUser =   (localStorage.getItem("user")== null) ? false : (<String>localStorage.getItem("user").toLowerCase()== 'admin' ? true: false);
    //     this.isManagerUser =   (localStorage.getItem("user")== null) ? false : (<String>localStorage.getItem("user").toLowerCase()== 'manager' ? true: false);
    //     this.isOperationsUser =   (localStorage.getItem("user")== null) ? false : (<String>localStorage.getItem("user").toLowerCase()== 'operations' ? true: false);
    //     this.isCustomerUser =   (localStorage.getItem("user")== null) ? false : (<String>localStorage.getItem("user").toLowerCase()== 'customer' ? true: false);
    //     this.username = localStorage.getItem("username");
    // }

    clear(){
        this.isAdminUser = false;
        this.isManagerUser=false;
        this.isOperationsUser=false;
        this.isCustomerUser=false;
        this.isUserLoggedIn = false;

        this.localStorage.clear();
    }
}