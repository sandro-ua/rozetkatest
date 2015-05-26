package com.mysite.selenium;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RozetkaTest {

    public static String LNK_START_LINK = "http://rozetka.com.ua/";
    WebDriver driver = new FirefoxDriver();

    String date = new SimpleDateFormat("dd-MM-yyyy-hh-ss").format(new Date());
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";


    // take screenshots
    public void takeScreenshot(String testName) {
        if (driver instanceof TakesScreenshot) {
            File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                String screenshotName = "scr_ "+ testName+ "_" + new SimpleDateFormat(DATE_FORMAT_NOW).format(new Date());
                FileUtils.copyFile(tempFile, new File("C:\\Dev\\Screenshots\\" + screenshotName + ".png"));
            } catch (IOException e) {
                // TODO handle exception
            }
        }
    }

    // rule to take a screenshots
    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        public void failed(Throwable t, Description test) {
            takeScreenshot(name.getMethodName());
        }
    };


    //getting the test name for scr name
    @Rule
    public TestName name= new TestName();

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
    }

    @AfterClass
    public static void oneTimeTearDown() {

    }

    @Before
    public void setUp() {
        driver.get(LNK_START_LINK);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void LoginAsCustomer ()
    {
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin("qatestermailbox@gmail.com", "q1w2e3");

    }




}
