package com.neo.uiPageElements;

import com.neo.locators.LoginLocators;
import com.neo.utility.WebPageElements;

public class LoginPageLocators extends CommonLocatorsForAllPages{
    public WebPageElements L_Username = new WebPageElements("Username", "xpath", LoginLocators.username1);
    public WebPageElements L_Password = new WebPageElements("Password", "xpath",LoginLocators.password1);
    public WebPageElements L_LoginButton = new WebPageElements("Login Button on home screen", "xpath",LoginLocators.loginclick1);

}
