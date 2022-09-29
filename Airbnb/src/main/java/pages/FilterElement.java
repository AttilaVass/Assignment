package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterElement {

    private WebDriver driver;
    public final WebElement rootElement;

    public enum E{Viewbox, Text, Message}

    private By viewBoxBy = By.cssSelector("svg");
    private By textBy = By.cssSelector(".t1psh3xd");
    //private By messageby = By.cssSelector(".n198op1o");
    private By messageby = By.cssSelector(".boygwtc");

    public FilterElement(WebElement rootElement, WebDriver driver){
        super();
        this.rootElement = rootElement;
        this.driver = driver;
    }


    public String getNumberOfActiveFilters() {

        try {
            WebElement filterElement = new WebDriverWait(this.driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfNestedElementLocatedBy(rootElement, messageby));
            String numberOfFilters = filterElement.getText();
            return numberOfFilters;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0";
    }

    public void click() {
        this.rootElement.click();
    }


}
