package com.example.stockapplication.loginManager.observer;

import androidx.databinding.BaseObservable;

import com.example.stockapplication.loginManager.componants.ValidateResult;
import com.example.stockapplication.loginManager.componants.ValidateResults;


public class BaseDataObserver extends BaseObservable {

    public String getErrorMessagesFromResults(ValidateResults results) {

        if (!results.hasErrors()) {
            return "";
        }

        String errorMsgs = "";
        for (ValidateResult result : results) {

            errorMsgs = errorMsgs + " " + (result.getErrorMessage());

        }
        return errorMsgs;
    }
}
