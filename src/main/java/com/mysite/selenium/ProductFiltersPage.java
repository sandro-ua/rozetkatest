package com.mysite.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductFiltersPage {

    public static WebDriver driver;

    public  ProductFiltersPage (WebDriver driver)

    {
        this.driver = driver;
    }

    public ProductFiltersPage applySorting (String sortingType)

    {
        WebElement sortingDropdown = driver.findElement(By.name("drop_link"));
        sortingDropdown.click();
        WebElement sortingDropdownItem = driver.findElement(By.xpath("//li/a[contains(@href, 'sort=" + sortingType + "')]"));

        return new ProductFiltersPage(driver);
    }

}
