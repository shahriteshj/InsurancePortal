import { Injectable} from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';

@Injectable()
export class SharedDataService{
    isAdminUser : boolean;
    isManagerUser : boolean;
    isOperationsUser: boolean;
    isCustomerUser:boolean;
    isUserLoggedIn : boolean;
    noOfCartItems : number = 0;

    constructor(private localStorage: LocalStorageService){
        this.getStoredValues();
        console.log(this.isManagerUser);
    }

    getStoredValues(){
        this.isUserLoggedIn =  (this.localStorage.retrieve("valid")==null) ? false : this.localStorage.retrieve("valid");
        this.isAdminUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'admin' ? true: false);
        this.isManagerUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'manager' ? true: false);
        this.isOperationsUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'operations' ? true: false);
        this.isCustomerUser =   (this.localStorage.retrieve("user")== null) ? false : (<String>this.localStorage.retrieve("user").toLowerCase()== 'customer' ? true: false);
    }

    clear(){
        this.isAdminUser = false;
        this.isManagerUser=false;
        this.isOperationsUser=false;
        this.isCustomerUser=false;
        this.isUserLoggedIn = false;

        this.localStorage.clear();
    }
}