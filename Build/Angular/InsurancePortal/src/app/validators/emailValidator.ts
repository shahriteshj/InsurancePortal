import {
    NG_VALIDATORS,
    FormControl,
    ValidatorFn,
    Validator
} from '@angular/forms';

import { Directive } from '@angular/core';
import { UserService } from '../service/user.service';
@Directive({
    selector: '[emailvalidator][ngModel]',
    providers: [
        {
            provide: NG_VALIDATORS,
            useExisting: EmailValidator,
            multi: true
        }
    ]
})
export class EmailValidator implements Validator {
    validator: ValidatorFn;
    constructor(private _userService: UserService) {
        this.validator = this.emailValidator();
    }
    validate(c: FormControl) {
        console.log("in validate");
        return this.validator(c);
    }

    emailValidator(): ValidatorFn {
        return (c: FormControl) => {

            console.log(c.value);
            return this._userService.checkUsernameExists(c.value)
                .subscribe((data: any) => {

                    if (data.response == "userFound") {
                        console.log(data.response);
                        return {
                            emailvalidator: {
                                invalid: true
                            }
                        };
                    }
                    else {
                        console.log("user not found");
                        return null;}
                });
        }
    }
}