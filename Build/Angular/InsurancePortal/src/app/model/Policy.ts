export class Policy {
    policyId?:number;
    customerId?:number;
    customerVehicleId?:number;
    policyStartDate:Date;
    policyEndDate:Date;
    
    constructor(policyStartDate:Date,policyEndDate:Date,customerId?:number,customerVehicleId?:number,policyId?:number){
        this.policyStartDate=policyStartDate;
        this.policyEndDate=policyEndDate;
        this.customerId=customerId;
        this.customerVehicleId=customerVehicleId;
        this.policyId=policyId;
    }
    
}