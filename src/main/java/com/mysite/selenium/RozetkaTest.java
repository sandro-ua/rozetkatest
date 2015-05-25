package com.mysite.selenium;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RozetkaTest {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void LoginAsCustomer ()
    {
        driver.get("http://rozetka.com.ua/");
        HomePage home = new HomePage(driver);
        home.navigateToLoginPage(driver);
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
