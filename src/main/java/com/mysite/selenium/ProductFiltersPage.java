package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductFiltersPage {

    public static WebDriver driver;
    public static final String LOC_ELM_SORTING_DROPDOWN = "drop_link";


    public  ProductFiltersPage (WebDriver driver)   {
        this.driver = driver;
    }


    //todo: finish method
    public ProductFiltersPage applySorting (String sortingType)

    {
        WebElement sortingDropdown = driver.findElement(By.name(LOC_ELM_SORTING_DROPDOWN));
        sortingDropdown.click();
        WebElement sortingDropdownItem = driver.findElement(By.xpath("//li/a[contains(@href, 'sort=" + sortingType + "')]"));

        return new ProductFiltersPage(driver);
    }

}
