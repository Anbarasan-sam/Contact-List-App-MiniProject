package test;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactDeletionPage;
import pages.ContactPage;
import pages.HomePage;
import utils.UtilsClass;

public class ContactDeletionTest extends ProjectSpecificationMethods{

	private HomePage homePage;
	private ContactDeletionPage contactDeletionPage;
	private ContactPage contactPage;

	@BeforeMethod(groups="Contact Delete")
	public void setUp() {
		
		homePage = new HomePage(UtilsClass.driver);
		contactPage = new ContactPage(UtilsClass.driver);
		contactDeletionPage = new ContactDeletionPage(UtilsClass.driver);

		homePage.login("guvi1234@gmail.com", "guvi123");
	}

	@Test(priority = 1,groups="Contact Delete")
	public void testDeleteContactShowsConfirmationAlert() {
		contactPage.clickContact();
		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test(priority = 2,groups="Contact Delete")
	public void testDeleteSingleContact() throws InterruptedException {
		Thread.sleep(2000);
		String contactToDelete = "John Doe";

		Assert.assertTrue(contactDeletionPage.isContactPresentClick(contactToDelete), "Contact to delete not found.");

		Thread.sleep(2000);
		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test(priority = 3,groups="Contact Delete")
	public void testDeleteContactDoesNotAffectOtherContacts() throws InterruptedException {
		Thread.sleep(2000);
		String contactToDelete = "Anbarasan z";

		Assert.assertTrue(contactDeletionPage.isContactPresentClick(contactToDelete), "Contact to delete not found.");

		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(2000);
		Assert.assertTrue(contactDeletionPage.isContactPresent("John Doe"),
				"Other contacts were affected by the deletion.");
	}
}
