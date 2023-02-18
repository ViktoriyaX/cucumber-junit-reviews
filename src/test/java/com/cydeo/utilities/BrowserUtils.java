package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

     /*
    This class will be storing only the utility methods that can be used across the project
     */


    //This method will accept int (in seconds) and execute Thread.sleep() method for given duration. Arg: int second
    public static void sleep(int second){
        second *=1000;
        try {
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    public static void switchWindowAndVerify(String expectedInURL, String expectedInTitle){

        //Return and store all window handles in a Set
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowHandles){

            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if(Driver.getDriver().getCurrentUrl().contains(expectedInURL)){
                break;
            }
        }

        //Assert: Title contains
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitleContains(String expectedInTitle){
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    //This method accepts WebElement target, and waits for that WE not to be displayed on the page
    public static void waitForInvisibilityOf(WebElement target){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(target));
    }

    //This method accepts String title, and waits for that Title to contain given String value
    public static void waitForTitleContains(String title){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(title));
    }

    //This method accepts a dropdown element and returns a List<String> that contains all options values as a String.
    /*
     @param dropdownElement
     @return actualResult_as_STRING
     */

    public static List<String> dropdownOptions_as_STRING (WebElement dropdownElement) {

        Select select = new Select(dropdownElement);
//Storing all the ACTUAL options into a list of WebElements
        List<WebElement> actualResult_as_WebElement = select.getOptions();

        //Creating an EMPTY list of String to store ACTUAL <option> as String
        List<String> actual_result_as_a_String = new ArrayList<>();

        //Looping through the List<WebElement> getting all options' texts, ans storing them into List<String>
        for (WebElement each : actualResult_as_WebElement) {
            actual_result_as_a_String.add(each.getText());
        }

        return actual_result_as_a_String;

    }
}
