package com.neo.testcases;

import com.neo.pages.LoginPage;
import com.neo.utility.Assert;
import com.neo.utility.ValidateTestCaseAndGetData;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestCases extends ValidateTestCaseAndGetData {

    public String RUNMODE = null;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    LoginPage p = new LoginPage();
    public String myProperties = "/Users/nishantpatel/Desktop/automation/src/main/java/com/neo/propertiesData/test.properties";

    public LoginTestCases() throws IOException {
        propertyFile = myProperties;
        getPropertiesData();
        classPath = this.getClass().getName();
    }

    @Test(priority = 0, enabled = true, description = "POS_Login With Valid UserName Password")
    public void TC1() {
        p.login(username, password);
        Assert.assertTrue(p.isLoginSucceed(), "Login is not succeed");
    }

//    @Test(priority = 0, enabled = true, description = "NAG_Login With invalid UserName Password")
//    public void TC2() {
//        p.login(username, password);
//        Assert.assertTrue(p.isLoginSucceed(), "Login is succeed where expected result is not to succeed");
//
//    }

}
