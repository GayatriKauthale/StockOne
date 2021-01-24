
package com.example.stockapplication.loginManager.componants;

import android.content.Context;

import java.util.ArrayList;

public class ValidateResults extends ArrayList<ValidateResult> {


    public boolean hasErrors() {
        return this.size() > 0;
    }


    public ValidateResult get(String fieldName) {

        for (ValidateResult result : this) {
            if (fieldName.equalsIgnoreCase(result.getFieldName())) {
                return result;
            }
        }

        return null;
    }

    public String getErrorMessagesFromResults() {
        String errorMsgs = "";
        for (ValidateResult result : this) {

            errorMsgs = errorMsgs + " " + (result.getErrorMessage());

        }
        return errorMsgs;
    }
}
