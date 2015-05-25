package com.mysite.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
    public WebDriver driver;
    WebElement username = driver.findElement(By.name("login"));
    WebElement password = driver.findElement(By.name("password"));
    WebElement submit = driver.findElement(By.name("auth_submit"));

    public LoginPage (WebDriver driver)
    {
        this.driver = driver;
    }



    public HomePage performLogin(String s_password, String s_username)
    {
        username.sendKeys(s_password);
        password.sendKeys(s_username);
        submit.submit();

        return new HomePage (driver);

    }










}
