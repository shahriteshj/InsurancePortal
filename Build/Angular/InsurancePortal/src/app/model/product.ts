export class Product {
    id?:number;
    name:string;
    price:number;
    photoPath:string;

    constructor(name:string,price:number,photoPath:string,id?:number){
        this.name=name;
        this.price=price;
        this.photoPath=photoPath;
    }
}