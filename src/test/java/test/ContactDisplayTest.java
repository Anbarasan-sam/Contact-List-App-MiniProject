package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactPage;
import pages.HomePage;
import utils.UtilsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDisplayTest extends ProjectSpecificationMethods{
    private HomePage homePage;
    private ContactPage contactPage;

    @BeforeMethod(groups = "Contact Display")
    public void setUp() {
        
        homePage = new HomePage(UtilsClass.driver);
        contactPage = new ContactPage(UtilsClass.driver);

        homePage.login("guvi1234@gmail.com", "guvi123");
    }

    @Test(priority = 1,groups = "Contact Display")
    public void testContactDetailsDisplayedCorrectly() {
        contactPage.clickAddContact();
        contactPage.fillContactDetails("John", "Doe", "john.doe@example.com", "9685743214", "1990-01-01");
        contactPage.enterStreetAdd1("123 Main St");
        contactPage.enterStreetAdd2("Apt 4B");
        contactPage.enterCity("New York");
        contactPage.enterState("NY");
        contactPage.enterPostalCode("10001");
        contactPage.enterCountry("USA");
        contactPage.submitContact();

        boolean isContactFound = contactPage.isContactDisplayed("John", "Doe", "9685743214");
        Assert.assertTrue(isContactFound, "Contact details are not displayed correctly.");
    }


    @Test(priority = 2,groups = "Contact Display")
    public void testContactsSortedByLastName() {
        List<String> lastNames = contactPage.getAllLastNames();
        List<String> sortedLastNames = new ArrayList<>(lastNames);
        Collections.sort(sortedLastNames);
        
    }

    @Test(priority = 3,groups = "Contact Display")
    public void testPhoneNumberDisplay() {
        List<String> phoneNumbers = contactPage.getAllPhoneNumbers();
        for (String phoneNumber : phoneNumbers) {
            Assert.assertFalse(phoneNumber.startsWith("+91"), "Phone number not have the +91 extension.");
        }
    }
}
