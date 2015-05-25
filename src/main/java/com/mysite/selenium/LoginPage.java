package com.mysite.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage {
    public WebDriver driver;
    By username = By.name("login");
    By password = By.name("password");

    public LoginPage (WebDriver driver)
    {
        this.driver = driver;
    }



    public HomePage performLogin(String password, String username)
    {
        this.driver = driver;




        return new HomePage (driver);


    }










}
