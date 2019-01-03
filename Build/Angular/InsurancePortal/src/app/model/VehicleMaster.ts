export class VehicleMaster {

     vehicleId?: number;
	 make:string  ;
	  model:string ;
	  submodel: string;
	  cc: string;
	 price: number;

    constructor(make:string,model:string,submodel:string,cc:string,price:number,vehicleId?:number){
        this.make=make;
        this.model=model;
        this.submodel=submodel;
        this.cc=cc;
        this.price=price;
        this.vehicleId=vehicleId;

    }

}