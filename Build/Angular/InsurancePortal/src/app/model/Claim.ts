export class Claim {
    claimId?:number;
	policyId?:number;
	status:string;
	 createdOn?:Date;
	 updatedOn?:Date;
	assignedTo?:string;
    rejectedReason:string;
    accidentId?:number;	
}