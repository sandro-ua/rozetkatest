package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class LoginPage {
    public static WebDriver driver;

    public LoginPage (WebDriver driver)
    {
        this.driver = driver;
    }



    public HomePage performLogin (String s_password, String s_username)
    {
        WebElement username = driver.findElement(By.name("login"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.name("auth_submit"));

        username.sendKeys(s_password);
        password.sendKeys(s_username);
        submit.click();

        //verifying that user is logged in
        //WebElement myDynamicElement = (new WebDriverWait(driver, 5))
        //        .until(ExpectedConditions.presenceOfElementLocated(By.name("profile")));

        return new HomePage (driver);
    }










}
