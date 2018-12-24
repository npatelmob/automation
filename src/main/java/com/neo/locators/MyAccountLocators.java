package com.neo.locators;

public interface MyAccountLocators {
	String accountype="accountType";
	String accountnumber="accountNumber";
	String accountbalance="accountBalance";
	String accountbalancetype="accountBalanceType";
	String depositaccountview="//*[@id='viewChildItemHeaderParent' and @index='0']";
	String creditcardview="//*[@id='viewChildItemHeaderParent' and @index='1']";
	String prepaidcardview="//*[@id='viewChildItemHeaderParent' and @index='2']";
	String swiperefresh="swipeRefreshLayout";

}
