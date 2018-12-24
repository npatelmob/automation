package com.neo.utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SeleniumUtility extends TestBase {


    public void click(WebPageElements ele, ExtentTest test) {
        WebElement element = null;
        try {
            /*waitForElement(driver, ele);*/
            element = getWebElement(ele, test);

            element.click();

        } catch (Exception e) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
            } catch (Exception e2) {
                e.printStackTrace();
                test.log(Status.INFO, "Not able to click on element::" + ele.getName());
            }

        }

    }

    public String getPageTitle(ExtentTest test) {
        String title = null;
        try {
            //*[@id='textTitle']

          title = driver.getTitle();

        } catch (Exception e) {
                e.printStackTrace();
                test.log(Status.INFO, "Not able to get Page Title");
            }
            return title;
        }


    public void moveAndClick(WebPageElements ele, ExtentTest test) throws InterruptedException {
        WebElement element = null;
        TouchAction actions = new TouchAction((MobileDriver) driver);
        element = getWebElement(ele, test);
      //  actions.moveTo(element).perform();
        Thread.sleep(2000);
        actions.tap(element).perform();
    }

    @SuppressWarnings("unused")
    private WebElement getWebElement(WebDriver driver, WebElement ele) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setText(WebPageElements ele, String text) {
        try {
            WebElement element = null;
            /*waitForElement(driver, ele);*/
            element = getWebElement(ele, test);
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(text + " not entered in " + ele.getName() + " textbox.");

        }


    }


    public String getText(WebPageElements ele, ExtentTest test) {
        String text = null;
        try {
            WebElement element = null;
            element = getWebElement(ele, test);
            text = element.getText();
            /*System.out.println(text);*/
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.INFO, "Not able to get text for::" + ele.getName());
        }
        return text;
    }

    public String getValue(WebPageElements ele, ExtentTest test) {
        String text = null;
        try {
            WebElement element = null;
            element = getWebElement(ele, test);
            text = element.getAttribute("value");
            /*System.out.println(text);*/
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.INFO, "Not able to get text for " + ele.getName());
        }
        return text;
    }


    public void clearText(WebPageElements ele, String text) {
        try {
            WebElement element = null;
            /*waitForElement(driver, ele);*/
            element = getWebElement(ele, test);
            element.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement getWebElementByAttributes(WebPageElements element) {
        WebElement ele = null;
        try {
            if (element.getLocator().equalsIgnoreCase("xpath")) {
                try {
                    ele = driver.findElement(By.xpath(element.getValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            } else if (element.getLocator().equalsIgnoreCase("id")) {
                ele = driver.findElement(By.id(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("name")) {
                ele = driver.findElement(By.name(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("linktext")) {
                ele = driver.findElement(By.linkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
                ele = driver.findElement(By.partialLinkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("classname")) {
                ele = driver.findElement(By.className(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("tagname")) {
                ele = driver.findElement(By.tagName(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("css")) {
                ele = driver.findElement(By.cssSelector(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("cssSelector")) {
                ele = driver.findElement(By.cssSelector(element.getValue()));
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail();
        }
        if (ele == null) {
            Assert.fail();
        }
        return ele;
    }

    public void waitForElementPresent(int seconds, final WebPageElements ele, ExtentTest test) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            if (ele.getLocator().equalsIgnoreCase("xpath")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("id")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("linktext")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("name")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("classname")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("css")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.getValue())));
            } else if (ele.getLocator().equalsIgnoreCase("cssSelector")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele.getValue())));
            }
            test.log(Status.PASS, "Wait for an element is displayed::" + ele.getName());
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.INFO, "Wait for an element is not displayed::" + ele.getName());
        }


    }

    public WebElement getWebElement(WebPageElements element, ExtentTest test) {
        WebElement ele = null;
        try {
            if (element.getLocator().equalsIgnoreCase("xpath")) {
                ele = driver.findElement(By.xpath(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("id")) {
                ele = driver.findElement(By.id(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("name")) {
                ele = driver.findElement(By.name(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("linktext")) {
                ele = driver.findElement(By.linkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
                ele = driver.findElement(By.partialLinkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("classname")) {
                ele = driver.findElement(By.className(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("tagname")) {
                ele = driver.findElement(By.tagName(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("css")) {
                ele = driver.findElement(By.cssSelector(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("cssSelector")) {
                ele = driver.findElement(By.cssSelector(element.getValue()));
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            test.log(Status.INFO, "Not able to find element " + element.getName());
        }
        if (ele == null) {

            test.log(Status.INFO, "Not able to find element " + element.getName());
        }
        return ele;
    }

    public void sendKey_element(WebPageElements ele, String exp_input, ExtentTest test) {
        WebElement element1 = null;

        try {
            element1 = getWebElement(ele, test);
            element1.sendKeys(exp_input);
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.INFO, "Not able to send data on text field::" + ele.getName());
        }
    }

    public void verticalScroll(WebPageElements fromElementList, WebPageElements ele) {
        //WebElement element1=null;

        try {
            List<WebElement> getList = getWebElements(fromElementList);
            System.out.println("list size : " + getList.size());
            WebElement fromElement = getList.get(0);
            int x = fromElement.getLocation().getX();
            int startY = (int) ((driver.manage().window().getSize().getHeight()) * 0.80);
            int endY = (int) ((driver.manage().window().getSize().getHeight()) * 0.20);
            TouchAction action = new TouchAction((MobileDriver) driver);

            while (!getWebElement(ele, test).isDisplayed()) {
                System.out.println("startY : " + startY + " endY : " + endY);
                action.press(x, startY).moveTo(x, endY).release().perform();

            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    //from element list : from where sscrolling will start
    //ele : element to which need to scroll
    public void horizontalScroll(WebPageElements fromElementList, WebPageElements ele) {
        //WebElement element1=null;

        try {
            List<WebElement> getList = getWebElements(fromElementList);
            WebElement fromElement = getList.get(0);
            int y = fromElement.getLocation().getY();
            int startX = (int) ((driver.manage().window().getSize().getWidth()) * 0.80);
            int endX = (int) ((driver.manage().window().getSize().getWidth()) * 0.20);
            TouchAction action = new TouchAction((MobileDriver) driver);

            while (!getWebElement(ele, test).isDisplayed()) {
                System.out.println("startX : " + startX + " endX : " + endX);
                action.press(startX, y).moveTo(endX, y).release().perform();

            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public List<WebElement> getWebElements(WebPageElements element) {
        List<WebElement> ele = null;
        try {
            if (element.getLocator().equalsIgnoreCase("xpath")) {
                ele = driver.findElements(By.xpath(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("id")) {
                ele = driver.findElements(By.id(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("name")) {
                ele = driver.findElements(By.name(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("linktext")) {
                ele = driver.findElements(By.linkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
                ele = driver.findElements(By.partialLinkText(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("classname")) {
                ele = driver.findElements(By.className(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("tagname")) {
                ele = driver.findElements(By.tagName(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("css")) {
                ele = driver.findElements(By.cssSelector(element.getValue()));
            } else if (element.getLocator().equalsIgnoreCase("cssSelector")) {
                ele = driver.findElements(By.cssSelector(element.getValue()));
            }

        } catch (NoSuchElementException e) {

            Assert.fail("Not able to find element " + element.getName());
            //Assert.fail();
        }
        if (ele == null) {

            Assert.fail("Not able to find element " + element.getName());
        }
        return ele;
    }

    public boolean verifyElementPresent(WebPageElements element, ExtentTest test) {

        boolean isPresent = false;

        if (element.getLocator().equalsIgnoreCase("xpath")) {
            isPresent = driver.findElements(By.xpath(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("id")) {
            isPresent = driver.findElements(By.id(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("name")) {
            isPresent = driver.findElements(By.name(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("linktext")) {
            isPresent = driver.findElements(By.linkText(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
            isPresent = driver.findElements(By.partialLinkText(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("classname")) {
            isPresent = driver.findElements(By.className(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("tagname")) {
            isPresent = driver.findElements(By.tagName(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("css")) {
            isPresent = driver.findElements(By.cssSelector(element.getValue())).size() > 0;
        } else if (element.getLocator().equalsIgnoreCase("cssSelector")) {
            isPresent = driver.findElements(By.cssSelector(element.getValue())).size() > 0;
        }

        if (!isPresent) {
            try {
                throw new Exception();//to throw exception, which will be handled in catch. It will be logged in report and eclipse console as well to identify easily.
            } catch (Exception e) {
                e.printStackTrace();
                test.log(Status.INFO, "Not Able to Verified element::" + element.getName());
            }

        } else {
            test.log(Status.PASS, "Verified::" + element.getName());
        }
        return isPresent;
    }

    public boolean verifyElementPresent(WebPageElements element) {

        boolean isPresent = false;
        try {
            if (element.getLocator().equalsIgnoreCase("xpath")) {
                isPresent = driver.findElements(By.xpath(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("id")) {
                isPresent = driver.findElements(By.id(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("name")) {
                isPresent = driver.findElements(By.name(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("linktext")) {
                isPresent = driver.findElements(By.linkText(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("partiallinktext")) {
                isPresent = driver.findElements(By.partialLinkText(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("classname")) {
                isPresent = driver.findElements(By.className(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("tagname")) {
                isPresent = driver.findElements(By.tagName(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("css")) {
                isPresent = driver.findElements(By.cssSelector(element.getValue())).size() > 0;
            } else if (element.getLocator().equalsIgnoreCase("cssSelector")) {
                isPresent = driver.findElements(By.cssSelector(element.getValue())).size() > 0;
            }

            return isPresent;
        } catch (NoSuchElementException e) {
            Assert.fail("Not able to find element " + element.getName());//-----changes(Remove if not work properly)
            return isPresent;
        }

    }

    public void swipe(int startX, int startY, int endX, int endY, int duration) {
        AndroidDriver driver1 = (AndroidDriver) driver;
        driver1.swipe(startX, startY, endX, endY, duration);
    }


}
