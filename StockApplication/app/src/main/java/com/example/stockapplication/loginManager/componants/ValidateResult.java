
package com.example.stockapplication.loginManager.componants;

public class ValidateResult {

    private String fieldName;
    private boolean isValid;
    private String errorMessage;

    public ValidateResult(String fieldName, boolean isValid, String errorMessage) {
        this.fieldName = fieldName;
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


    public boolean equals(ValidateResult result) {
        return this.fieldName.equals(result.fieldName) &&
                this.isValid == result.isValid;
                /*&&
                this.errorMessage.equals(result.errorMessage);*/
    }
}
