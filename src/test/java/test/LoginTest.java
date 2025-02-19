package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import utils.UtilsClass;

public class LoginTest extends ProjectSpecificationMethods {

	private HomePage homepage;

	@BeforeMethod(groups = "Login")
	public void setup() {
		
		homepage = new HomePage(UtilsClass.driver);
	}

	@Test(groups = "Login", priority = 1)
	public void testLoginButtonVisibility() {
		
		Assert.assertTrue(homepage.isLoginButtonVisible(), "Login button is not visible. ");
	}

	@Test(groups = "Login", priority = 2)
	public void testLoginButtonClickability() {
		Assert.assertTrue(homepage.isLoginButtonClickable(), "Login button is not visible. ");
	}

	@Test(groups = "Login", priority = 3)
	public void verifyInvalidLogin() {
		
		homepage.login("abcdarasan.com", "guvi123");
		Assert.assertTrue(homepage.getErrorMessage().contains("Incorrect username or password"),
				"Error message for invalid login is not displayed.");
	}

	@Test(groups = "Login", priority = 4)
	public void verifyValidLogin() {
		try {
			homepage.login("guviminiproject2@gmail.com", "guvi123");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(),
					"https://thinking-tester-contact-list.herokuapp.com/contactList",
					"User is not redirected to the contact list after valid login.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
