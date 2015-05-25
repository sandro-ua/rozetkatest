package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


class HomePage

{
    public static WebDriver driver;
    public static final String LOC_LNK_privateCabinet = ".header-user-link.sprite-side.novisited.xhr";
    public static final String TTL_homePageTitle = "»нтернет-магазин ROZETKAЩ: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие";
    WebElement signInLink = driver.findElement(By.cssSelector(LOC_LNK_privateCabinet));

    public HomePage (WebDriver driver)
    {

        this.driver = driver;
        if (driver.getTitle() != TTL_homePageTitle)
            throw new IllegalStateException("This is not the login page");





    }


    public LoginPage navigateToLoginPage (WebDriver driver)
    {
        signInLink.click();
        return new LoginPage (driver);
    }


}
