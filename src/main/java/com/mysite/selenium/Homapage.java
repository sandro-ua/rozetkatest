package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


class HomePage

{
    public static WebDriver driver;
    public static final String LOC_LNK_privateCabinet = "signin";
    public static final String LOC_LNK_searchInput = "text";
    public static final String LOC_LNK_searchSubmitButton = "search-button";

    public static final String LOC_LNK_CATEGORY_TOURISM = "http://rozetka.com.ua/outdoorsman/c81202/";





    public HomePage (WebDriver driver)
    {
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

}
