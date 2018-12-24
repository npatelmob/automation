package com.neo.testcases;

import com.aventstack.extentreports.Status;
import com.neo.pages.LoginPage;
import com.neo.utility.DataProvider;
import com.neo.utility.TestBase;
import com.neo.utility.ValidateTestCase;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTestCases extends ValidateTestCase {

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
        Assert.assertTrue(p.isLoginSucceed(),"Login is not succeed");
    }

    @Test(priority = 0, enabled = true, description = "NAG_Login With invalid UserName Password")
    public void TC2() {
                p.login(username, password);
                Assert.assertFalse(p.isLoginSucceed(),"Login is succeed");
        }

}
