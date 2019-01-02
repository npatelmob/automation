package com.neo.pages;
import com.aventstack.extentreports.Status;
import com.neo.uiPageElements.LoginPageLocators;
import com.neo.utility.SeleniumUtility;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.lang.reflect.Method;

public class LoginPage extends SeleniumUtility {

    LoginPageLocators l=new LoginPageLocators();


    public void login (String username, String password) {
        test= extent.createTest(description);
        enterUserName(username);
        enterPassword(password);
        clickOnLoginButton();
    }

    public void enterUserName(String username){
        waitForElementPresent(500,l.L_Username, test);
        click(l.L_Username, test);
        test.log(Status.PASS,"click on username");
        verifyElementPresent(l.L_Username , test);
        sendKey_element(l.L_Username,username, test);
        test.log(Status.PASS,"Enter username");
    }


    public void enterPassword(String password){
        click(l.L_Password, test);
        verifyElementPresent(l.L_Password, test);
        sendKey_element(l.L_Password,password, test);
        test.log(Status.PASS,"Enter password");
    }

    public void clickOnLoginButton(){
        verifyElementPresent(l.L_LoginButton, test);
        click(l.L_LoginButton, test);
        test.log(Status.PASS,"click on login button from login screen");
    }

    public boolean isLoginSucceed(){
        if(verifyElementPresent(l.C_pageTitleAfterLogin,test)==true) {
            String pageTitleText = getText(l.C_pageTitleAfterLogin, test);
            return pageTitleText.equalsIgnoreCase("Product Summary");
        }
        else {
            return false;
        }
    }

    public void validateLogin(){


    }

}
