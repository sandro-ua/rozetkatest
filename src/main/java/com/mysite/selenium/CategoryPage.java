package com.mysite.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CategoryPage {

    public static WebDriver driver;


public CategoryPage (WebDriver driver)
{
    this.driver = driver;
}

  public CategoryPage selectSubCategory (String subCategoryLink)

  {
      WebElement categoryElement = driver.findElement(By.xpath("//a[@href='" + subCategoryLink + "']"));
      categoryElement.click();
      return new CategoryPage(driver);

  }




}
