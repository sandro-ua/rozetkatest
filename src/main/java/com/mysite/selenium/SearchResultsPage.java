package com.mysite.selenium;

import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage {
        public static WebDriver driver;
        public static final String LOC_LNK_SHOW_MORE = "button_text";
        public static final String LOC_LNK_PRODUCT_TITLE = "//div[@class='g-i-list-title']/a";
        public static final String LOC_LNK_SEARCH_RESULT_COUNT = "search-result-count";
        public static final String LOC_LNK_PAGINATION = "//ul[@name='paginator']/li";

        public SearchResultsPage(WebDriver driver) {
            this.driver = driver;
        }

        //returns an array of found product's titles at one\first page
        public List GetResultsFromFirstPage(WebDriver driver) {
            int pagesCount = getNumberOfResults(driver);
            List results = new ArrayList();
            List<WebElement> searchResults = driver.findElements(By.xpath(LOC_LNK_PRODUCT_TITLE));
            for (int i = 0; i<searchResults.size(); i++)
            {
                results.add (i, searchResults.get(i).getText());
            }
            return results;
        }

        //returns count of found products
        public int getNumberOfResults(WebDriver driver) {
            WebElement resultsNumber = driver.findElement(By.className(LOC_LNK_SEARCH_RESULT_COUNT));

            String countStr = (resultsNumber.getText());
            countStr = countStr.replaceAll("\\D+","");
            int count = Integer.parseInt(countStr);
            return count;
        }

        //returns number of pages
        public int GetNumberOfPages(WebDriver driver) {
            int pages = driver.findElements(By.xpath(LOC_LNK_PAGINATION)).size();
            return pages;
        }

        //clicks 'Get more items' button till all are shown
        public List GetResultsFromAllPages (WebDriver driver)
        {
            if (GetNumberOfPages(driver)>1) {
                List allResults;

                while (isPresentAndDisplayed(driver.findElement(By.name(LOC_LNK_SHOW_MORE))) == true) {
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    driver.findElement(By.name(LOC_LNK_SHOW_MORE)).click();
                }

                allResults = GetResultsFromFirstPage(driver);
                return allResults;
            } else return GetResultsFromFirstPage(driver);
        }

        public static boolean isPresentAndDisplayed (WebElement element) {
            try {
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                return element.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
            catch (StaleElementReferenceException e){
                return false;
            }
        }
    }