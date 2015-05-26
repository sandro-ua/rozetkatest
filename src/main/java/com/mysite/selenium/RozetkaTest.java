package com.mysite.selenium;
import com.google.common.base.Verify;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RozetkaTest {

    public static String LNK_START_LINK = "http://rozetka.com.ua/";
    public static WebDriver driver = new FirefoxDriver();

    public static String TXT_NOT_VALID_USERNAME = "q1w2e3@gmail.com";
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH-mm-ss";


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

    }

    @AfterClass
    public static void oneTimeTearDown() {
        driver.quit();
    }

    @Before
    public void setUp() {
        driver.get(LNK_START_LINK);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void LoginAsValidCustomer ()
    {
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin("qatestermailbox@gmail.com", "q1w2e3");
        driver.quit();

    }

    @Test
    public void LoginWithWrongUsername()

    {
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin(TXT_NOT_VALID_USERNAME, "q1w2e3");
        //WebElement emailHint = driver.findElement(By.name("email_hint"));
        WebElement emailHint2 = (new WebDriverWait(driver, 5)) .until(ExpectedConditions.presenceOfElementLocated(By.name("email_hint")));
        Assert.assertEquals(emailHint2.getText(), "ѕользователь с логином " + TXT_NOT_VALID_USERNAME + " не зарегистрирован");



    }


}
