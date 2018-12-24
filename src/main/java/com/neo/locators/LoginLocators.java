package com.neo.locators;

public interface LoginLocators {
	//BPI Application Login Functionality
	//String login="//android.widget.TextView[contains(@text,'Login') and @index='1']";//[@resource-id='com.bpi.ng.mobilebanking:id/btnName']";
	String bpilogo="//*[@content-desc='Splash BPI logo']";
	String username1="//*[@id='editUsername']";
	String password1="//*[@id='editPassword']";
	String loginclick1="//*[@id='buttonLogin']";
	String loginsidemenu="//*[@text='Login' and @id='menuName']";
	String humburgermenuicon="//*[@contentDescription='Navigate up']";
	String usernameicon="((//*[@class='android.view.View' and ./parent::*[@class='android.view.View']]/*/*[@class='android.view.View' and ./parent::*[@class='android.view.View']])[12]/*[@text and @class='android.view.View'])[1]";
	String passwordicon="(//*[@class='android.view.View' and ./parent::*[@class='android.view.View']]/*/*/*[@text and @class='android.view.View' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.EditText']])[2]";
	String termscondition="//*[@text='By using this service, you confirm that you have read, understood and that you accept our ']";
}
