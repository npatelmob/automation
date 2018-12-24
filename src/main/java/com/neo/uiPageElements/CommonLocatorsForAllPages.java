package com.neo.uiPageElements;

import com.neo.locators.CommonLocators;
import com.neo.utility.WebPageElements;

public class CommonLocatorsForAllPages {
    public WebPageElements C_pageTitle = new WebPageElements("PageTitle", "xpath", CommonLocators.pageTitle);
    public WebPageElements C_pageTitleAfterLogin = new WebPageElements("PageTitle", "xpath", CommonLocators.pageTitleAfterLogin);

}
