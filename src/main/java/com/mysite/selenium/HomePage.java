package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage

{
    public static WebDriver driver;
    public static final String LOC_LNK_privateCabinet = "signin";
    public static final String LOC_LNK_searchInput = "text";
    public static final String LOC_LNK_searchSubmitButton = "search-button";
    public static final String LOC_LNK_cart = "//a[@href = 'https://my.rozetka.com.ua/cart/']";
    public static final String LOC_LNK_CATEGORY_TOURISM = "http://rozetka.com.ua/outdoorsman/c81202/";

    public HomePage (WebDriver driver)     {
        this.driver = driver;
    }

    public LoginPage navigateToLoginPage (WebDriver driver)
    {
        WebElement signInLink = driver.findElement(By.name(LOC_LNK_privateCabinet));
        signInLink.click();

        //verifying that login window is opened
        if (driver.findElements(By.name("login")).isEmpty()) driver.manage().logs() ;
        return new LoginPage (driver);
    }

    public CategoryPage navigateToSpecificCategory (WebDriver driver, String categoryLink)
    {
        WebElement categoryElement = driver.findElement(By.xpath("//a[@href='" + categoryLink + "']"));
        categoryElement.click();
        return new CategoryPage(driver);
    }

    public SearchResultsPage performSearch (WebDriver driver, String searchTerm)

    {
        WebElement searchInput = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_LNK_searchInput)));
        WebElement searchSubmitButton = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_LNK_searchSubmitButton)));

        searchInput.sendKeys(searchTerm);
        searchSubmitButton.click();

        return new SearchResultsPage(driver);
    }

    public CartPage openCart (WebDriver driver)
    {
        WebElement cart = driver.findElement(By.xpath(LOC_LNK_cart));
        cart.click();
        return new CartPage(driver);
    }

    public String getLoggeInCUsomerName (WebDriver driver)
    {
        //WebElement custName = driver.findElement(By.name("profile"));
        WebElement custName = (new WebDriverWait(driver, 3))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("profile")));
        return custName.getText();
    }
}
