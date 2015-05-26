package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TakesScreenshot;


class HomePage

{
    public static WebDriver driver;
    public static final String LOC_LNK_privateCabinet = "signin";
    public static final String LOC_LNK_searchInput = "text";
    //public static final String TTL_homePageTitle = "»нтернет-магазин ROZETKAЩ: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие";


    public HomePage (WebDriver driver)
    {
        this.driver = driver;

        WebElement myDynamicElement = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_LNK_searchInput)));
    }


    public LoginPage navigateToLoginPage (WebDriver driver)
    {
        WebElement signInLink = driver.findElement(By.name(LOC_LNK_privateCabinet));
        signInLink.click();

        //verifying that login window is opened
        if (driver.findElements(By.name("login")).isEmpty()) driver.manage().logs() ;

        return new LoginPage (driver);
    }


}
