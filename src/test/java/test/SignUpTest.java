package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import utils.UtilsClass;

public class SignUpTest extends ProjectSpecificationMethods {
	private HomePage homepage;
	
	@BeforeMethod(groups="Signup")
	public void setup() {
		
		homepage = new HomePage(UtilsClass.driver);
	}

	@Test(groups="Signup",priority=1)
	public void testSignUpBtnVisibility() {
		
		Assert.assertTrue(homepage.isSignUpBtnVisible(),"Signup button is not visible.");
	}
	
	@Test(groups="Signup",priority=2)
	public void testSignUpBtnClickability() {
		
		Assert.assertTrue(homepage.isSignUpBtnClickable(),"Signup button is not clickable.");
	}
	
	@Test(groups="Signup",priority=3)
	public void testHomePageRedirectsToAddUserPage() {
		
		homepage.clickSignUpBtn();
		
		if(driver.getTitle().equalsIgnoreCase("Add User")) {
			System.out.println(" After clicking signup button redirects to Add use page.");
		}else {
			System.out.println(" After clicking signup button redirects to wrong page.");
		}
	}
}
