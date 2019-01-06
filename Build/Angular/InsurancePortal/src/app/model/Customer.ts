export class Customer {

    customerId?: number;
    firstName: string;
    lastName: string;
    emailId: string;
    mobileNo: string;
    address1: string;
    address2: string;
    address3: string;
    gender: string;
    pincode: number;
    state: string;
    city: string;
    DOB: Date;

    constructor(
        firstName: string, lastName: string, emailId: string, mobileNo: string,
        address1: string, address2: string, address3: string, gender: string, pincode: number,
        state: string, city: string, DOB: Date, customerId?: number) {
            this.firstName=firstName;
            this.lastName=lastName;
            this.emailId=emailId;
            this.mobileNo=mobileNo;
            this.address1=address1;
            this.address2 = address2;
            this.address3=address3;
            this.gender=gender;
            this.pincode=pincode;
            this.state=state;
            this.city=city;
            this.DOB=DOB;
            this.customerId=customerId;
    }
}