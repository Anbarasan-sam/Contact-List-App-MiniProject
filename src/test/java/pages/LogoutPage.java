package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.UtilsClass;

public class LogoutPage extends UtilsClass {
    protected WebDriver driver;

    @FindBy(id = "logout") 
    private WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoutButtonVisible() {
        waitForElementVisible(logoutButton, 10);
        return logoutButton.isDisplayed();
    }

    public HomePage clickLogoutButton() {
        waitForElementVisible(logoutButton, 10);
        logoutButton.click();
        return new HomePage(driver);
    }
}
