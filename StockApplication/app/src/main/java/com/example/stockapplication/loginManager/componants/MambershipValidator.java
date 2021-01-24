package com.example.stockapplication.loginManager.componants;

import com.example.stockapplication.constant.Strings;
import com.example.stockapplication.loginManager.helper.StringHelper;
import com.example.stockapplication.loginManager.messages.LoginRequest;

public class MambershipValidator
{

        private String confirmPass;
        public ValidateResults validateLoginDetails(LoginRequest request) {

            ValidateResults results = new ValidateResults();

            //Validate email id
            validateEmailId(results, request.getEmailId());

            //Validate password
            validatePassword(results, request.getPassword());

            return results;
        }


    public ValidateResults validateEmailId(ValidateResults results, String emailId) {

        if (StringHelper.isNullOrEmpty(emailId)) {
            results.add(new ValidateResult("EmailId", false,
                    "Email Id is required"));
            return results;
        }

        if (!StringHelper.isValidEmailId(emailId)) {
            results.add(new ValidateResult("EmailIdPatternValidity", false,
                    "Email Id is not valid"));
        }

        //Do more validations....

        return results;
    }



    public ValidateResults validatePassword(ValidateResults results, String password) {

        confirmPass=password;
        if (StringHelper.isNullOrEmpty(password)) {
            results.add(new ValidateResult("Password", false,
                    "PASSWORD IS REQUIRED"));
            return results;
        } else if (!StringHelper.isValidPassword(password)) {
            results.add(new ValidateResult("PasswordPatternValidity", false,
                    "Password must be atleast 8 characters long and should contain atleast one uppercase,lowercase,number and special character"));
        }
        //Do more validations....

        return results;
    }

}
