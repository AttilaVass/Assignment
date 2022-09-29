package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilterElement;
import pages.FilterModalElement;
import pages.HomePage;
import java.text.SimpleDateFormat;

import java.util.*;


public class AirbnbTests extends BaseTests {


    @Test
    public void test1() throws InterruptedException {
        driver.navigate().to("https://www.airbnb.com/");
        homePage = new HomePage(driver);
        driver.manage().window().fullscreen();
        homePage.clickButton("anywhere");

        homePage.setDestinationAndSelectFirstOption("Spain");


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String checkIn = sdf.format(cal.getTime());
        System.out.println(checkIn+" is the date before adding days");

        cal.add(Calendar.DAY_OF_MONTH, 3);
        String checkOut = sdf.format(cal.getTime());
        //date after adding three days to the given date
        System.out.println("\n" + checkOut+" is the date after adding 3 days.");

        cal.add(Calendar.DAY_OF_MONTH, -4);
        String dateToCheck = sdf.format(cal.getTime());
        System.out.println("\n" + dateToCheck+" is the date after substracting a day.");
        homePage.setDate(checkIn, checkOut);
        Assert.assertTrue(homePage.checkDateIsNotSelectable( dateToCheck), "Date can be selected");

        Thread.sleep(2000);
        homePage.clickSearch();
        Thread.sleep(5000);

        FilterElement filterElement = homePage.getFilterElement();
        System.out.println("Number of filters " + filterElement.getNumberOfActiveFilters());
        System.out.println("Number of results for search "+homePage.getNumberOfResultsTopLeft());
        System.out.println("Number of results on page " +homePage.goThroughPagesAndReturnnumberOfResults());
    }

    @Test
    public void test2() throws InterruptedException {
        driver.navigate().to("https://www.airbnb.com/");
        //2 - Fullscreen mode
        driver.manage().window().fullscreen();
        homePage = new HomePage(driver);

        homePage.clickButton("anywhere");

        homePage.setDestinationAndSelectFirstOption("Spain");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String checkIn = sdf.format(cal.getTime());
        System.out.println(checkIn+" is the date before adding days");

        cal.add(Calendar.DAY_OF_MONTH, 3);
        String checkOut = sdf.format(cal.getTime());
        //date after adding three days to the given date
        System.out.println("\n" + checkOut+" is the date after adding 3 days.");

        cal.add(Calendar.DAY_OF_MONTH, -4);
        String dateToCheck = sdf.format(cal.getTime());
        System.out.println("\n" + dateToCheck+" is the date after substracting a day.");
        homePage.setDate(checkIn, checkOut);
        Assert.assertTrue(homePage.checkDateIsNotSelectable(dateToCheck), "Date can be selected");

        Thread.sleep(2000);
        homePage.clickSearch();
        Thread.sleep(3000);

        //homePage.clickButton("showmap");
        homePage.hoverOverCard(1);
        Thread.sleep(2000);
        Assert.assertTrue(homePage.checkIsSelectedPriceOnMap(), "No selected element on map found");
        /*System.out.println(filterElement.getNumberOfActiveFilters());
        System.out.println(homePage.getNumberOfResultsTopLeft());
        System.out.println(homePage.goThroughPagesAndReturnnumberOfResults());*/
    }

    @Test
    public void test3() throws InterruptedException {

        List<String> typesOfRoomFilter = new ArrayList<>(List.of("Entire Place", "Private Room"));

        driver.navigate().to("https://www.airbnb.com/");
        //2 - Fullscreen mode
        driver.manage().window().fullscreen();
        homePage = new HomePage(driver);

        homePage.clickButton("anywhere");

        homePage.setDestinationAndSelectFirstOption("Spain");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String checkIn = sdf.format(cal.getTime());
        System.out.println(checkIn + " is the date before adding days");

        cal.add(Calendar.DAY_OF_MONTH, 3);
        String checkOut = sdf.format(cal.getTime());
        //date after adding three days to the given date
        System.out.println("\n" + checkOut + " is the date after adding 3 days.");

        cal.add(Calendar.DAY_OF_MONTH, -4);
        String dateToCheck = sdf.format(cal.getTime());
        System.out.println("\n" + dateToCheck + " is the date after substracting a day.");
        homePage.setDate(checkIn, checkOut);
        Assert.assertTrue(homePage.checkDateIsNotSelectable(dateToCheck), "Date can be selected");

        Thread.sleep(2000);
        homePage.clickSearch();
        Thread.sleep(3000);

        FilterElement filterElement = homePage.getFilterElement();
        filterElement.click();
        Thread.sleep(2000);

        FilterModalElement filterModalElement = homePage.getFilterModalElement();
        filterModalElement.selectTypeOfRoom(typesOfRoomFilter);

        filterModalElement.clickShowHomes();
        filterElement = homePage.getFilterElement();
        Assert.assertEquals(filterElement.getNumberOfActiveFilters() , "2" , "Number of active filters is not as expected");
        Thread.sleep(3000);
    }
}
