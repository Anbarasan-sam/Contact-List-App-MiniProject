package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.UtilsClass;

public class ProjectSpecificationMethods extends UtilsClass {

    @Parameters({"browser", "url"})
    @BeforeMethod
    public void launchBrowser(String browser, String url) {
        browserLaunch(browser, url);
    }

    @AfterMethod
    public void close() throws InterruptedException {
        closeBrowser();
    }
}
	
//	@DataProvider
//	public String[][] excelRead() throws IOException {
//		
//		String[][] data = readExcel(sheetname);
//		return data;
//		
//	}
	
