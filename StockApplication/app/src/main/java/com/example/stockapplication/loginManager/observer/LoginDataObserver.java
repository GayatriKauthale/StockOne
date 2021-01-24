package com.example.stockapplication.loginManager.observer;

import android.app.Application;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.stockapplication.BR;
import com.example.stockapplication.constant.Strings;
import com.example.stockapplication.loginManager.componants.MambershipValidator;
import com.example.stockapplication.loginManager.componants.ValidateResults;
import com.example.stockapplication.loginManager.helper.StringHelper;
import com.example.stockapplication.loginManager.messages.LoginRequest;


public class LoginDataObserver extends BaseDataObserver
{

    private String emailId;
    private String password;
    private  Application mApplication;
    private  MambershipValidator mMembershipValidator;
    private String emailIdError;
    private String passwordError;
    public LoginDataObserver(Application application, MambershipValidator mambershipValidator) {
       this.mApplication=application;
       this.mMembershipValidator=mambershipValidator;
    }

    @Bindable
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        notifyPropertyChanged(BR.emailId);
    }



    public TextWatcher getEmailIdWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                emailId = s.toString();
                if (!StringHelper.isNullOrEmpty(s.toString())) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showEmailIdErrorsIfAny();
                        }
                    },1000);


                } else {
                    setEmailIdError("");
                }

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }

        };
    }

    private void showEmailIdErrorsIfAny() {
        ValidateResults results = new ValidateResults();
        results = mMembershipValidator.validateEmailId(results, emailId);

        String errorMsgs = getErrorMessagesFromResults(results);
        setEmailIdError(errorMsgs);
    }

    @Bindable
    public String getEmailIdError()
    {
        return emailIdError;
    }

    public void setEmailIdError(String emailIdError) {
        this.emailIdError = emailIdError;
        notifyPropertyChanged(BR.emailIdError);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public TextWatcher getPasswordWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                password = s.toString();
                if (!StringHelper.isNullOrEmpty(s.toString())) {

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showPasswordErrorsIfAny();
                        }
                    },1000);

                } else {

                    setPasswordError("");
                    setEmailIdError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }

        };
    }
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
//        notifyPropertyChanged(BR.passwordError);
    }


    private void showPasswordErrorsIfAny() {
        ValidateResults results = new ValidateResults();
        results = mMembershipValidator.validatePassword(results, password);

        String errorMsgs = getErrorMessagesFromResults(results);
        setPasswordError(errorMsgs);
    }

    public boolean isValid() {

        LoginRequest request = new LoginRequest();
        request.setEmailId(emailId);
        request.setPassword(password);

        ValidateResults results = mMembershipValidator.validateLoginDetails(request);


        if(results.size()!=0) {

            for (int i = 0; i < results.size(); i++)
            {
                if (results.get(i).getFieldName().equals(Strings.SignUpDataObserver.EMAIL_ID)) {
                    setEmailIdError(results.get(i).getErrorMessage());
                }
                else if (results.get(i).getFieldName().equals(Strings.SignUpDataObserver.PASSWORD)) {
                    setPasswordError(results.get(i).getErrorMessage());
                }
            }
        }

        return !results.hasErrors();
    }
}
