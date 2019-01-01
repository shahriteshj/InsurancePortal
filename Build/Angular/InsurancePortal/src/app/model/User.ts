export class User {
    id?:number;
    name:string;
    username:string;
    password:string;

    constructor(name:string,username:string,password:string,id?:number){
        id=id;
        this.name=name;
        this.username=username;
        this.password=password;
    }


}