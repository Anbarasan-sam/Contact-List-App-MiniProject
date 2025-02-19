package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.UtilsClass;

import java.util.List;

public class ContactDeletionPage extends UtilsClass {
    protected WebDriver driver;

    @FindBy(id="delete")
    private WebElement deleteButton;

    @FindBy(xpath = "//td[2]")
    private List<WebElement> contactRows;

    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "submitEdit")
    private WebElement submitButton;

    public ContactDeletionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ContactPage deleteContact() {
        deleteButton.click();
        return new ContactPage(driver);
    }

    public List<WebElement> getContactRows() {
        return contactRows;
    }

    public boolean isContactPresentClick(String contactName) {
        for (WebElement row : contactRows) {
            if (row.getText().contains(contactName)) {
            	row.click();
                return true;
            }
        }
        return false;
    }
    
    public boolean isContactPresent(String contactName) {
        for (WebElement row : contactRows) {
            if (row.getText().contains(contactName)) {
                return true;
            }
        }
        return false;
    }

    public ContactPage clickAddContact() {
        waitForElementVisible(addContactButton, 10);
        addContactButton.click();
        return new ContactPage(driver);
    }

    public ContactEditingPage clickContactToEdit(int index) {
        contactRows.get(index).click();
        return new ContactEditingPage(driver);
    }

    public ContactEditingPage clickEditButton() {
        waitForElementVisible(submitButton, 10);
        submitButton.click();
        return new ContactEditingPage(driver);
    }

    public ContactEditingPage updateContactDetails(String firstName, String lastName, String email, String phone) {
        if (firstName != null) firstNameField.clear();
        if (firstName != null) firstNameField.sendKeys(firstName);

        if (lastName != null) lastNameField.clear();
        if (lastName != null) lastNameField.sendKeys(lastName);
        return new ContactEditingPage(driver);
    }

    public ContactPage submitEditedContact() {
        waitForElementVisible(submitButton, 10);
        submitButton.click();
        return new ContactPage(driver);
    }
}
