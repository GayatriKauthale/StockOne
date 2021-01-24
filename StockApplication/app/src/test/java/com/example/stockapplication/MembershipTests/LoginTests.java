package com.example.stockapplication.MembershipTests;

import android.content.Context;

import com.example.stockapplication.constant.Strings;
import com.example.stockapplication.loginManager.componants.MambershipValidator;
import com.example.stockapplication.loginManager.componants.ValidateResults;
import com.example.stockapplication.loginManager.messages.LoginRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;



import static org.junit.Assert.assertNotNull;

public class LoginTests {

    private MambershipValidator mMembershipValidator;

    @Mock
    Context mContext;



    @Before
    public void testSetup(){

        mMembershipValidator = new MambershipValidator();

        mContext = Mockito.mock(Context.class);

        assertNotNull(mContext);


    }

    @Test
    public void testCanLoginUsingValidCredentials(){

        LoginRequest loginModel = new LoginRequest();
        loginModel.setEmailId("gayatri@gmail.com");
        loginModel.setPassword("Gayatri");

        ValidateResults results = mMembershipValidator.validateLoginDetails(loginModel);

        if(results.size()!=0) {

            System.out.println("Fail to login");

        }
        else
        {
            System.out.println("Success");
        }



    }

    @After
    public void testTeardown(){

    }

}
