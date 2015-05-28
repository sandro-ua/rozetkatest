package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    public static WebDriver driver;

    public static final String LOC_BTN_ADD_TO_CART = "topurchases";
    public static final String LOC_PRICE = ".//div[@name='price']/span[@itemprop='price']";

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int GetPrice (WebDriver driver)

    {
        WebElement priceElement = driver.findElement(By.xpath(LOC_PRICE));
        String price = priceElement.getText();
        price = price.replaceAll("\\D+","");
        return Integer.parseInt(price);
    }

    public CartPage addProductToCart (WebDriver driver)
    {
        WebElement addToCartButton = (new WebDriverWait(driver, 3))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_BTN_ADD_TO_CART)));
        addToCartButton.click();
        return new CartPage(driver);
    }


}
