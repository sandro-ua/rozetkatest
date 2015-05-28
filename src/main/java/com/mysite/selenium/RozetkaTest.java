package com.mysite.selenium;
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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RozetkaTest {

    public static String LNK_START_LINK = "http://rozetka.com.ua/";
    public static WebDriver driver = new FirefoxDriver();

    public static final String VALID_USERNAME = "qatestermailbox@gmail.com";
    public static final String INVALID_PASSWORD = "a";
    public static final String TXT_NOT_VALID_USERNAME = "q1w2e373@gmail.com";
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH-mm-ss";
    public static final String USER_NOT_EXIST_MESSAGE_1 = "Пользователь с логином ";
    public static final String USER_NOT_EXIST_MESSAGE_2 = " не зарегистрирован";
    public static final String PASSWORD_IS_INCORRECT = "Введен неверный пароль!";
    public static final String PASSWORD_HINT = "password_hint";


    public static final String LNK_TOURISM = "http://rozetka.com.ua/outdoorsman/c81202/";
    public static final String LNK_SUB_TOURISM_TENTS = "http://rozetka.com.ua/tents/c82412/";

    //several links with products
    public static final String LNK_PRD_TENT = "http://rozetka.com.ua/terra_incognita_ksena3alu/p82522/";
    public static final String LNK_PRD_BALL = "http://rozetka.com.ua/reflex_field/p1743697/";
    public static final String LNK_PRD_BYCICLE = "http://rozetka.com.ua/author_1300087/p2983055/";

    public static final String TXT_SEARCH_TERM = "asus tablet";

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
        //user is logged in

    }

    @Test
    public void LoginWithNotExistingUsername()

    {
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin(TXT_NOT_VALID_USERNAME, "q1w2e3");

        //verify that error message is shown
        WebElement emailHint = (new WebDriverWait(driver, 5)) .until(ExpectedConditions.presenceOfElementLocated(By.name(PASSWORD_HINT)));
        Assert.assertEquals(emailHint.getText(), USER_NOT_EXIST_MESSAGE_1 + TXT_NOT_VALID_USERNAME + USER_NOT_EXIST_MESSAGE_2);

    }

    @Test
    public void LoginWIthInvalidPassword ()

    {
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin(VALID_USERNAME, INVALID_PASSWORD);

        //verify that error message is shown
        WebElement password_hint = (new WebDriverWait(driver, 5)) .until(ExpectedConditions.presenceOfElementLocated(By.name(PASSWORD_HINT)));
        Assert.assertEquals(password_hint.getText(), PASSWORD_IS_INCORRECT );
    }

    @Test
    public void NavigateToCategory()
    {
        HomePage home = new HomePage(driver);
        CategoryPage catPage = home.navigateToSpecificCategory(driver, LNK_TOURISM);
        catPage.selectSubCategory(LNK_SUB_TOURISM_TENTS);

    }

    @Test
    public void SearchProductFirstPageResults()
    {

        HomePage home= new HomePage(driver);
        SearchResultsPage resultsPage = home.performSearch(driver, TXT_SEARCH_TERM);
        resultsPage.GetResultsFromFirstPage(driver);

    }

    @Test
    public void SearchProductAllResults()
    {
        HomePage home= new HomePage(driver);
        SearchResultsPage resultsPage = home.performSearch(driver, TXT_SEARCH_TERM);
        resultsPage.GetResultsFromAllPages(driver);
    }

    @Test
    // comparing the given number of results with real one. It will fail due bug :)
    // To repro, follow this link: http://rozetka.com.ua/search/?section=%2F&text=asus+tablet&search-button=
    // it says there are 29 found items, but really there are 32
    public void testTheNumberOfReturnedResults()
    {
        HomePage home = new HomePage(driver);
        SearchResultsPage resultsPage = home.performSearch(driver, TXT_SEARCH_TERM);
        List results = resultsPage.GetResultsFromAllPages(driver);
        assert (results.size() == resultsPage.getNumberOfResults(driver));
    }

    @Test
    public void GetDetailsFromProductPage()
    {
        ProductDetailsPage prodDetails = new ProductDetailsPage(driver);
        driver.get("http://rozetka.com.ua/terra_incognita_ksena3alu/p82522/");
        int expextedPrice = 4459;
        assert (expextedPrice == prodDetails.GetPrice(driver));

    }

    @Test
    public void AddingRemovingProductsToFromCart ()

    {
        ProductDetailsPage productDetails = new ProductDetailsPage(driver);
        HomePage home = new HomePage(driver);
        CartPage cart;

        List <String> linksToProducts = new ArrayList();
        linksToProducts.add(LNK_PRD_BALL);
        linksToProducts.add(LNK_PRD_BYCICLE);
        linksToProducts.add(LNK_PRD_TENT);

        for (int i = 0; i< linksToProducts.size(); i++)
        {
            driver.get (linksToProducts.get(i));
            productDetails.addProductToCart(driver);
        }

        cart = home.openCart(driver);
        cart.removeAllProductFromCart(driver);

    }



}
