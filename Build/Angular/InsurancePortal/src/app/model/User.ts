export class User {
    id?: number;
    name: string;
    username: string;
    password: string;
    roleName: string;
    questionId: number;
    securityAnswer: string;
    lastSuccessfulLoginDate: string;
    responseText: string;

    constructor(name: string, username: string, password: string, roleName: string, questionId: number, securityAnswer: string,lastSuccessfulLoginDate:string,responseText:string, id?: number) {
        id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roleName=roleName;
        this.questionId=questionId;
    }


}