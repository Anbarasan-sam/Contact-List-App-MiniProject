package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.UtilsClass;

public class HomePage extends UtilsClass{
	
	private WebDriver driver;
	
	@FindBy(id="signup")
	private WebElement signupButton;
	
	@FindBy(id="submit")
	private WebElement loginButton;
	
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(id = "error")
    private WebElement errorMessage;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean isSignUpBtnVisible() {
		return signupButton.isDisplayed();
	}
	
	public boolean isSignUpBtnClickable() {
		return signupButton.isEnabled();
	}
	
	public HomePage clickSignUpBtn() {
		isSignUpBtnVisible();
		isSignUpBtnClickable();
		signupButton.click();
		return new HomePage(driver);
	}
	
	public boolean isLoginButtonVisible() {
		waitForElementVisible(loginButton, 10);
        return loginButton.isDisplayed();
    }

    public boolean isLoginButtonClickable() {
    	waitForElementClickable(loginButton, 10);
        return loginButton.isEnabled();
    }

    public HomePage login(String email, String password) {
    	waitForElementVisible(emailField, 10);
    	waitForElementClickable(emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public String getErrorMessage() {
    	waitForElementVisible(errorMessage, 10);
        return errorMessage.getText();
    }
	
}
