package com.mysite.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetails {

    public static WebDriver driver;

    public static final String LOC_PRICE = ".//div[@name='price']/span[@itemprop='price']";

    public ProductDetails  (WebDriver driver) {
        this.driver = driver;
    }

    public int GetPrice (WebDriver driver)

    {
        WebElement priceElement = driver.findElement(By.xpath(LOC_PRICE));
        String price = priceElement.getText();
        price = price.replaceAll("\\D+","");
        return Integer.parseInt(price);

    }
}
