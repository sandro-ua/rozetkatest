package com.mysite.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

    public class SearchResultsPage {
        public static WebDriver driver;
        public static final String LOC_LNK_SHOW_MORE = "button_text";

        public SearchResultsPage(WebDriver driver) {
            this.driver = driver;
        }


        //returns an array of found product's titles at one\first page
        public List GetResultsFromFirstPage(WebDriver driver) {
            int pagesCount = getNumberOfResults(driver);
            List results = new ArrayList();
            List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='g-i-list-title']/a"));
            for (int i = 0; i<searchResults.size(); i++)
            {
                results.add (i, searchResults.get(i).getText());
            }
            return results;
        }


        //returns count of found products
        public int getNumberOfResults(WebDriver driver) {
            WebElement resultsNumber = driver.findElement(By.className("search-result-count"));

            String countStr = (resultsNumber.getText());
            countStr = countStr.replaceAll("\\D+","");
            int count = Integer.parseInt(countStr);
            return count;
        }


        //returns number of pages
        public int GetNumberOfPages(WebDriver driver) {
            int pages = driver.findElements(By.xpath("//ul[@name='paginator']/li")).size();
            return pages;
        }

        //clicks Get more items till all are shown
        public List GetResultsFromAllPages (WebDriver driver)
        {
            //WebElement more = driver.findElement(By.name(LOC_LNK_SHOW_MORE));
            List allResults = new ArrayList();


            while (driver.findElement(By.name(LOC_LNK_SHOW_MORE)).isDisplayed())
            {
                WebElement showMore = (new WebDriverWait(driver, 5))
                        .until(ExpectedConditions.presenceOfElementLocated(By.name(LOC_LNK_SHOW_MORE)));
                showMore.click();
            }

            allResults = GetResultsFromFirstPage(driver);
            return allResults;
        }



    }