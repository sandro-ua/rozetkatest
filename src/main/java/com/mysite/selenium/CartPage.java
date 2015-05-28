package com.mysite.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CartPage {
    public static WebDriver driver;

    public static final String LOC_BTN_CONTINUE_SHOPPING = "close";
    public static final String LOC_BTN_REMOVE_ITEM_FROM_CART = "before_delete";
    public static final String LOC_BTN_REMOVE_ITEM_FROM_CART_CONFIRM = "delete";
    public CartPage (WebDriver driver) {
        this.driver = driver;
    }


public ProductDetailsPage continueShopping (WebDriver driver)
{
    WebElement continueShoppingButton = driver.findElement(By.name(LOC_BTN_CONTINUE_SHOPPING));
    continueShoppingButton.click();
    return new ProductDetailsPage(driver);
}

    public  CartPage removeAllProductFromCart (WebDriver driver)
    {
        //WebElement toDel = driver.findElement(By.name(LOC_BTN_REMOVE_ITEM_FROM_CART));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        while (isPresentAndDisplayed(driver.findElement(By.name(LOC_BTN_REMOVE_ITEM_FROM_CART))) == true)
        {
            WebElement delete = (new WebDriverWait(driver, 3))
                    .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_BTN_REMOVE_ITEM_FROM_CART)));
            delete.click();

            WebElement confirm_delete = (new WebDriverWait(driver, 3))
                    .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_BTN_REMOVE_ITEM_FROM_CART_CONFIRM)));
            confirm_delete.click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return new CartPage(driver);
    }

    public static boolean isPresentAndDisplayed (WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        catch (StaleElementReferenceException e){
            return false;
        }
    }
}
