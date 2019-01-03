export class Query {
    queryId?: number;
    name: string;
    emailId: string;
    queryType: string;
    queryDescription: string;
    queryResponse: string;
    assignedTo: string;
    status: string;


    constructor(name: string, emailId: string, queryType: string, queryDescription: string, queryResponse: string, assignedTo: string, status: string, queryId?: number) {
        this.queryId = queryId;
        this.name = name;
        this.emailId = emailId;
        this.queryType = queryType;
        this.queryDescription = queryDescription;
        this.queryResponse = queryResponse;
        this.assignedTo = assignedTo;
        this.status = status;
    }
}