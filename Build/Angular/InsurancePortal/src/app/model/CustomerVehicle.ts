export class CustomerVehicle {

    vehicleId?: number;
    customerId?:number;
    make:string  ;
    model:string ;
    submodel: string;
    cc: string;
    vehicleRegNo:string;
    engineNo:string;
    chasisNo:string;
    fuelType:string;
    manufacturingYear:number;
    registrationDate:string;
    vehicleRegCity:string;

   constructor(make:string,model:string,submodel:string,cc:string,vehicleRegNo:string,engineNo:string,chasisNo:string,
    fuelType:string,manufacturingYear:number,registrationDate:string,vehicleRegCity:string,customerId?:number,vehicleId?:number){
       this.make=make;
       this.model=model;
       this.submodel=submodel;
       this.cc=cc;
       this.vehicleRegNo=vehicleRegNo;
       this.engineNo=engineNo;
       this.chasisNo=chasisNo;
       this.fuelType=fuelType;
       this.manufacturingYear=manufacturingYear;
       this.registrationDate=registrationDate;
       this.vehicleRegCity=vehicleRegCity;
       this.customerId=customerId;
       this.vehicleId=vehicleId;

   }

}