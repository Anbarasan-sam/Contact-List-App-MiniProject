package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.UtilsClass;

public class ContactEditingPage extends UtilsClass {
    protected WebDriver driver;

    @FindBy(xpath = "//tr[contains(@class,'contact-row')]//td[contains(text(),'John Doe')]")
    private WebElement contactRow;  // Locator for specific contact (adjust as needed)

    @FindBy(id = "edit-contact")  // Adjust to actual edit button's locator
    private WebElement editButton;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "submit")  // Submit button for saving edited contact
    private WebElement submitButton;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public ContactEditingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ContactEditingPage clickContactToEdit() {
        contactRow.click();
        return new ContactEditingPage(driver);
    }

    public ContactEditingPage clickEditButton() {
        waitForElementVisible(editButton, 10);
        editButton.click();
        return new ContactEditingPage(driver);
    }
  

    public ContactEditingPage updateContactDetails(String firstName, String lastName, String email, String phone) {
        
    	if (firstName != null) firstNameField.clear();
        
        if (firstName != null) firstNameField.sendKeys(firstName);

        if (lastName != null) lastNameField.clear();
        if (lastName != null) lastNameField.sendKeys(lastName);

        if (email != null) emailField.clear();
        if (email != null) emailField.sendKeys(email);

        if (phone != null) phoneField.clear();
        if (phone != null) phoneField.sendKeys(phone);
        
        return new ContactEditingPage(driver);
    }

    public ContactEditingPage submitEditedContact() {
        waitForElementVisible(submitButton, 10);
        submitButton.click();
        return new ContactEditingPage(driver);
    }

    public String getErrorMessage() {
        waitForElementVisible(errorMessage, 10);
        return errorMessage.getText();
    }
}
