package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactPage;
import pages.HomePage;
import utils.UtilsClass;

public class ContactTests extends ProjectSpecificationMethods{

	private HomePage homepage;
    private ContactPage contactPage;

    @BeforeMethod(groups="Contact")
    public void setUp() {
        
        homepage = new HomePage(UtilsClass.driver);
        contactPage = new ContactPage(UtilsClass.driver);
        
        homepage.login("guvi1234@gmail.com", "guvi123");
    }

    @Test(priority=1,groups="Contact")
    public void testAddContactWithMandatoryFields() throws InterruptedException {
        Thread.sleep(2000);
    	contactPage.clickAddContact();
        contactPage.fillContactDetails("John", "Doe", "raguraj@gmail.com", "9654873215", "2000-01-01");
        contactPage.submitContact();
        Assert.assertTrue(contactPage.contactDetailsName().contains("John Doe"), "Contact is not added");
    }
    
    @Test(priority=2,groups="Contact")
    public void testAddContactWithoutMandatoryFields()throws InterruptedException {
    	Thread.sleep(2000);        
    	contactPage.clickAddContact();
        contactPage.fillContactDetails(null, "Smith", "jane.smith@gmail.com", "9876543210", "1995-05-15");
        contactPage.submitContact();
        Assert.assertTrue(contactPage.getErrorMessage().contains("Contact validation failed: firstName: Path `firstName` is required."), 
        		"Error message is not displayed.");
        driver.navigate().back();
    }
    @Test(priority = 3,groups="Contact")
    public void testAddDuplicateContact() throws InterruptedException {
    	Thread.sleep(2000);
       for(int i=2;i>=1;i--) {
    	contactPage.clickAddContact();
        contactPage.fillContactDetails("Anbarasan", "z", "john.doe@gmail.com", "9632587412", "1990-01-01");
        contactPage.enterStreetAdd1("Raja street");
        contactPage.enterCity("Chennai");
        contactPage.enterState("Tamil Nadu");
        contactPage.enterPostalCode("600048");
        contactPage.enterCountry("India");
        contactPage.submitContact();
        Thread.sleep(2000);
    }
       System.out.println("Duplicate contact added there is no error.");
    }
    @Test(priority = 4,groups="Contact")
    public void testInvalidBirthDateFormat() throws InterruptedException {
    	Thread.sleep(2000);
        contactPage.clickAddContact();
        contactPage.fillContactDetails("Jane", "Doe", "jane.doe@gamil.com", "5555555555", "01-01-1990");
        contactPage.submitContact();
        Assert.assertTrue(contactPage.getErrorMessage().contains("Birthdate is invalid"),
                "Application did not restrict invalid birthdate format.");
    }
}
