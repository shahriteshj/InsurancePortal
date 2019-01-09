export class Policy {
    policyId?:number;
    customerId?:number;
    customerVehicleId?:number;
    policyStartDate:Date;
    policyEndDate:Date;
    status:string;
    
    constructor(policyStartDate:Date,policyEndDate:Date,customerId?:number,customerVehicleId?:number,policyId?:number){
        this.policyStartDate=policyStartDate;
        this.policyEndDate=policyEndDate;
        this.customerId=customerId;
        this.customerVehicleId=customerVehicleId;
        this.policyId=policyId;
    }
    
}