package com.example.stockapplication.loginManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stockapplication.R;
import com.example.stockapplication.SplashActivity;
import com.example.stockapplication.databinding.ActivityLoginBinding;
import com.example.stockapplication.loginManager.componants.MambershipValidator;
import com.example.stockapplication.loginManager.listners.ILoginActionListener;
import com.example.stockapplication.loginManager.observer.LoginDataObserver;
import com.example.stockapplication.marketWatchUI.activity.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginActionListener {
    ActivityLoginBinding activityLoginBinding;
    private LoginDataObserver mDataObserver;
    private String  emailId,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mDataObserver = new LoginDataObserver(getApplication(),new MambershipValidator());
        activityLoginBinding.setDataObserver(mDataObserver);
        activityLoginBinding.setActionListener(this);

    }

    @Override
    public void onClickLogin() {
        emailId = mDataObserver.getEmailId();
        password = mDataObserver.getPassword();
        //If not valid then return
        if (!mDataObserver.isValid()) {
           Toast.makeText(getApplicationContext(), "Login is invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }


}