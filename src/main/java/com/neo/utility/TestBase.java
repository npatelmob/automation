package com.neo.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public Properties properties;

    public static WebDriver driver = null;
    public static String platform;
    public static String serverUrl;
    public static String driverPath;
    public static String versionInfo;
    public static String deviceName;
    public static String appPaths;
    public static boolean noReset;
    public static String propertyFile = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    public static String classPath;
    public static String dataProvider;
    public static ExtentHtmlReporter html;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String description;
    public static String methodName;
    public static String username;
    public static String password;


    public void getPropertiesData() throws IOException {
        properties = new Properties();
        FileInputStream Locator = new FileInputStream(propertyFile);
        properties.load(Locator);
        deviceName = properties.getProperty("deviceName");
        platform = properties.getProperty("platformName");
        serverUrl = properties.getProperty("localUrl");
        driverPath = properties.getProperty("driverPath");
        versionInfo = properties.getProperty("versionInfo");
        noReset = Boolean.parseBoolean(properties.getProperty("noReset"));
        appPaths = properties.getProperty("appPaths");
        dataProvider = properties.getProperty("dataProvider");
    }


    public String[] getAppPaths() {
        String[] paths = new String[2];
        String[] tempPath = appPaths.split("/");
        paths[1] = tempPath[tempPath.length - 1];
        paths[0] = appPaths.split(paths[1])[0];
        return paths;
    }


    public void setupAndroid() {
        // Set device name using capabilities
        File appDir = new File(getAppPaths()[0]);
        File app = new File(appDir, getAppPaths()[1]);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability(CapabilityType.VERSION, versionInfo);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("appPackage", "npatel.neo.npatel");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("appActivity", ".views.LoginActivity");
        capabilities.setCapability("noReset", noReset);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("nativeWebScreenshot", true);
    }

    public WebDriver initializeDriver() throws IOException {
        if (platform.contains("android") || platform.contains("Android")) {
            setupAndroid();
            //  driver = new AndroidDriver(new URL(serverUrl), capabilities);
            AndroidDriver driver1 = new AndroidDriver(new URL(serverUrl), capabilities);
            driver=driver1;
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } else if (platform.contains("iOS")) {
            IOSDriver driver1 = new IOSDriver(new URL(serverUrl), capabilities);
           // driver = driver1;
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return this.driver;
    }

    /*public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws IOException {

        // Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        // Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        // Move image file to new destination

        File DestFile = new File(fileWithPath);

        // Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }*/

    public void skipThisTest(){
        throw new SkipException("");
    }
    @BeforeSuite
    public void extent_reports() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        html = new ExtentHtmlReporter(System.getProperty("user.dir") + "/BPI_AppiumTesting/Android/Android_TestResults_"
                + timeStamp + ".html");
        extent = new ExtentReports();
        extent.attachReporter(html);
    }

    @BeforeMethod(dependsOnMethods = "getDescription")
    public void initialize() throws Exception{
        ValidateTestCase validateTestCase = new ValidateTestCase();
        if(validateTestCase.isTestCaseNeedToTest(methodName)){
            username=validateTestCase.getUserName(methodName);
            password=validateTestCase.getPassword(methodName);
            initializeDriver();
        }
        else {
            skipThisTest();
        }
    }

    @BeforeMethod()
    public void getDescription(Method method) {
        Test test = method.getAnnotation(Test.class);
        description =test.description();
        methodName = method.getName();
    }
    @AfterMethod
    public void getResult(ITestResult result) throws Exception {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed is " + result.getName());
            test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
        }

       // driver.quit();

    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        System.out.println("Cases Executed");
    }

}
