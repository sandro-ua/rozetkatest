package com.mysite.selenium;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;


public class RozetkaTest {

    public static String LNK_START_LINK = "http://rozetka.com.ua/";
    WebDriver driver = new FirefoxDriver();



    @Test
    public void LoginAsCustomer ()
    {
        driver.get(LNK_START_LINK);
        HomePage home = new HomePage(driver);
        LoginPage login = home.navigateToLoginPage(driver);
        login.performLogin("qatestermailbox@gmail.com", "q1w2e3");

    }



}
