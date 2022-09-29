package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilterModalElement {
    private WebDriver driver;
    public final WebElement rootElement;


    private By typeOfPlaceFilter = By.cssSelector("[data-section-id = \"FILTER_SECTION_CONTAINER:ROOM_TYPE:TAB_ALL_HOMES\"]");
    private By checkBoxEntirePlace = By.cssSelector("input[name= \"Entire place\"]");
    private By checkBoxPrivateRoom = By.cssSelector("input[name= \"Private room\"]");
    private By showhomesButton = By.cssSelector("._1ku51f04");



    public FilterModalElement(WebElement rootElement, WebDriver driver){
        super();
        this.rootElement = rootElement;
        this.driver = driver;
    }

    public void selectTypeOfRoom(List<String> typesOfRoom) throws InterruptedException {
        for (String type : typesOfRoom) {
            switch (type){
                case "Entire Place":
                    driver.findElement(checkBoxEntirePlace).click();
                    Thread.sleep(1000);
                    break;
                case "Private Room":
                    driver.findElement(checkBoxPrivateRoom).click();
                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println("No option " + type);

            }
        }
    }

    public void clickShowHomes() {
        driver.findElement(showhomesButton).click();
    }

}
