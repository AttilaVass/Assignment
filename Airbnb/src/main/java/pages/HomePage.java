package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private By anywhereButton = By.cssSelector("body header [data-index='0']");
    private By searchDestinationInput = By.cssSelector("#bigsearch-query-location-input");
    private By searchDestinationInputSuggestion1 = By.cssSelector("#bigsearch-query-location-suggestion-0");
    private By presentDatesOnPage = By.cssSelector("._2hyui6e ._1lds9wb [role='button']");
    private By searchButton = By.cssSelector("[data-testid='structured-search-input-search-button']");
    private By topLeftNumberOfResults = By.cssSelector("._14i3z6h span");
    private By filterButtonElement = By.cssSelector("body .b1a88q73");
    private By searchResultCards = By.cssSelector("body .cy5jw6o");
    private By searchResultCardsInfo = By.cssSelector("body .cy5jw6o"); // .t1jojoys names
    private By searchResultsPagination = By.cssSelector("[aria-label=\"Search results pagination\"]");
    private By filterModal = By.cssSelector("[aria-label=\"Filters\"]");
    private By searchResultsPaginationPreviousButton = By.cssSelector("[aria-label=\"Search results pagination\"] [aria-label=\"Previous\"]");
    private By searchResultsPaginationNextButton = By.cssSelector("[aria-label=\"Search results pagination\"] [aria-label=\"Next\"]");
    private By searchResultsPaginationSelectablePages = By.cssSelector("[aria-label=\"Search results pagination\"] [aria-label=\"Next\"]");
    private By searchResultsPaginationCurrentPage = By.cssSelector("[aria-label=\"Search results pagination\"] [aria-current=\"page\"]");
    private By mapIsSelectedElement = By.cssSelector(".czgw0k9 .a8jt5op");
    private By showMapButton = By.cssSelector("body ._ft1uei");
    private By mapCardelement = By.cssSelector("._1mhd7uz");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }



    public FilterElement getFilterElement(){
        WebElement filterElement = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(filterButtonElement));
        return new FilterElement(filterElement, driver);
    }

    public FilterModalElement getFilterModalElement(){
        WebElement filterModalElement = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(filterModal));
        return new FilterModalElement(filterModalElement, driver);
    }


    public void clickButton(String option) {
        if (option.equalsIgnoreCase("anywhere")) {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(anywhereButton));
            driver.findElement(anywhereButton).click();
        }
        if (option.equalsIgnoreCase("showmap")){
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(showMapButton));
            driver.findElement(showMapButton).click();
        }
    }

    public void setDestinationAndSelectFirstOption(String input) {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(searchDestinationInput));
        driver.findElement(searchDestinationInput).sendKeys(input);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(searchDestinationInputSuggestion1));
        driver.findElement(searchDestinationInputSuggestion1).click();
    }

    public void setDate(String checkIn, String checkOut) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(presentDatesOnPage));
        List<WebElement> datesOnPage = driver.findElements(presentDatesOnPage);
        for (WebElement date : datesOnPage) {
            WebElement dateForTest = date.findElement(By.cssSelector("div"));
            String datesString = dateForTest.getAttribute("data-testid");
            if (datesString.contains(checkIn)) {
                date.click();
            }
            if (datesString.contains(checkOut)) {
                date.click();
            }
        }
    }

    public boolean checkDateIsNotSelectable(String dateToCheck) {

        List<WebElement> datesOnPage = driver.findElements(presentDatesOnPage);
        for (WebElement date : datesOnPage) {
            WebElement dateForTest = date.findElement(By.cssSelector("div"));
            String datesString = dateForTest.getAttribute("data-testid");
            if (datesString.contains(dateToCheck)){
                date.getAttribute("aria-disabled").equalsIgnoreCase("true");
                return date.getAttribute("aria-disabled").equalsIgnoreCase("true");
            }
        }
        return false;
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public int getNumberOfResultsTopLeft(){
        String numberOfResultsText = driver.findElement(topLeftNumberOfResults).getText().replaceAll("\\D+", "");
        return Integer.valueOf(numberOfResultsText);
    }

    public int goThroughPagesAndReturnnumberOfResults()  throws InterruptedException {
        WebElement navigationNextButton = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(searchResultsPaginationNextButton));
        List<WebElement> searchResultsCards = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultCards));
           /* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", navigationNextButton);
            Thread.sleep(500);*/

        int numberOfResults = 0;
        boolean nextButtonEnabled = navigationNextButton.isEnabled();
        do {
            navigationNextButton = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(searchResultsPaginationNextButton));
            nextButtonEnabled = navigationNextButton.isEnabled();
            //System.out.println(searchResultsCards.size());
            numberOfResults = numberOfResults + searchResultsCards.size();
            navigationNextButton.click();
            Thread.sleep(500);
        } while(nextButtonEnabled);
        return numberOfResults;
    }

    public void hoverOverCard(int index){
        WebElement card = driver.findElements(searchResultCardsInfo).get(index - 1);
        System.out.println(card.getText());
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", card);

        Actions actions = new Actions(driver);
        actions.moveToElement(card).perform();
    }

    public boolean checkIsSelectedPriceOnMap(){
        List<WebElement> isSelect = driver.findElements(mapIsSelectedElement);
        for (WebElement element: isSelect) {
            if (element.getText().contains("selected")){
                return true;
            }
        }
        return false;
    }

}