package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.LogoutPage;
import utils.UtilsClass;

public class LogoutTest extends ProjectSpecificationMethods {
    private HomePage homePage;
    private LogoutPage logoutPage;

    @BeforeMethod(groups="Logout")
    public void setUp() {
        
        homePage = new HomePage(UtilsClass.driver);
        logoutPage = new LogoutPage(UtilsClass.driver);

        homePage.login("guvi1234@gmail.com", "guvi123"); 
    }

    @Test(priority = 1,groups="Logout")
    public void testLogoutButtonIsVisible() {
        Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "Logout button is not visible.");
    }

    @Test(priority = 2,groups="Logout")
    public void testLogoutRedirectsToLoginPage() {
        logoutPage.clickLogoutButton();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://thinking-tester-contact-list.herokuapp.com/"), "Logout did not redirect to the login page.");
    }
}
