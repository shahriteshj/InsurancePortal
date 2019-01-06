export class PolicyPayment {
    paymentId?: number;
    policyId?: number;
    cardNo: string;
    nameOnCard: string;
    cvv: number;
    cardExpiryMonth: number;
    cardExpiryYear: number;
    amount: number;
    paymentDate?: Date;

    constructor(cardNo: string, nameOnCard: string, cvv: number, cardExpiryMonth: number, cardExpiryYear: number, amount: number, paymentId?: number, policyId?: number, paymentDate?: Date) {
        this.cardNo = cardNo;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardExpiryYear = cardExpiryYear;
        this.amount = amount;
        this.paymentId = paymentId;
        this.policyId = policyId;
        this.paymentDate = paymentDate;
    }

}