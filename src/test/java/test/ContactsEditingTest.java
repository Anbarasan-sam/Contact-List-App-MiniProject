package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactEditingPage;
import pages.ContactPage;
import pages.HomePage;
import utils.UtilsClass;

public class ContactsEditingTest extends ProjectSpecificationMethods {

	private HomePage homePage;
	private ContactPage contactPage;
	private ContactEditingPage editingPage;
	
	@BeforeMethod(groups = "Edit Contact")
	public void setup() {
		
		homePage = new HomePage(UtilsClass.driver);
		contactPage = new ContactPage(UtilsClass.driver);
	    editingPage = new ContactEditingPage(UtilsClass.driver);

	    homePage.login("guviminiproject2@gmail.com", "guvi123");
	}
	@Test(groups = "Edit Contact")
	public void testRedirectsToContactDetailsPage() {
		
		contactPage.clickContact();
	
		Assert.assertTrue(driver.getCurrentUrl().contains("contactDetails"), "Not redirected to contact details page.");
	}
	
	 @Test(priority = 2,groups = "Edit Contact")
	    public void testEditContactDetails() throws InterruptedException {
		 
		 contactPage.clickContact();
		 	Thread.sleep(2000);
	        editingPage.clickEditButton();

	        String updatedFirstName = "JohnUpdated";
	        String updatedLastName ="DoeUpdated";
	        String updatedEmail = "john.updated@example.com";
	        String updatedPhNum = "9876543210";

	        Thread.sleep(2000);
	        editingPage.updateContactDetails(updatedFirstName, updatedLastName, updatedEmail, updatedPhNum);
	        editingPage.submitEditedContact();

	 }

	    @Test(priority = 3,groups = "Edit Contact")
	    public void testFieldChangesDontAffectOtherFields() throws InterruptedException {
	    	
	    	contactPage.clickContact();
	    	Thread.sleep(2000);
	    	editingPage.clickEditButton();

	        String originalFirstName = "John";
	        String originalLastName = "Doe";
	        editingPage.updateContactDetails(originalFirstName, "Smith", null, null);  // Only update last name

	        editingPage.submitEditedContact();
	        Thread.sleep(2000);
	        Assert.assertFalse(driver.getPageSource().contains("Doe"), "Original last name should not appear.");
	    }

	    @Test(priority = 4,groups = "Edit Contact")
	    public void verifySaveWithEmptyFields() throws InterruptedException {
	    	contactPage.clickContact();
	    	Thread.sleep(2000);
	    	editingPage.clickEditButton();

	    	editingPage.updateContactDetails(null, null, null, null);  

	    	editingPage.submitEditedContact();

	        String errorMessage = editingPage.getErrorMessage();
	        Assert.assertFalse(errorMessage.isEmpty(), "Should not allow saving empty fields.");
	    }	
}
