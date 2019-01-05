export class PolicyPayment {
  cardNo: string;
  nameOnCard: string;
  cvv: number;
  cardExpiryMonth: number;
  cardExpiryYear: number;
   amount:number;

    constructor(cardNo:string,nameOnCard:string,cvv:number,cardExpiryMonth:number,cardExpiryYear:number,amount:number){
        this.cardNo=cardNo;
        this.nameOnCard=nameOnCard;
        this.cvv=cvv;
        this.cardExpiryMonth=cardExpiryMonth;
        this.cardExpiryYear=cardExpiryYear;
        this.amount=amount;
    }

}