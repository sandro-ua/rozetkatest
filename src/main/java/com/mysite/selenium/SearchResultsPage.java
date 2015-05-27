package com.mysite.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    public static WebDriver driver;


    public SearchResultsPage (WebDriver driver)
    {
        this.driver = driver;
    }


WebElement searchReslts = driver.findElements(By.xpath("//div[@class='g-i-list-title']/a/text()"));



}
