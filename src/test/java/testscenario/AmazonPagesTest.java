package testscenario;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import baseTest.TestBase;
import objectrepo.HomeOR;
import objectrepo.ProductDetailsOR;
import utility.ExcelUtill;

public class AmazonPagesTest extends TestBase {

	public HomeOR objHome;
	public ProductDetailsOR objProdDetails;

	@BeforeClass
	public void browserSetupAndUrlLaunch() throws Exception {

		browserSetup();
	}

	@Test(description = "Verify the correct redirection of url amazon.com")
	public void verifyHomePageLanding() throws Exception {
		objHome = new HomeOR(driver);
		Reporter.log(objHome.getLandingPageActualText());
		Assert.assertEquals(objHome.getLandingPageActualText(), prop.getProperty("homePageExpectedTitle"));
	}
	
	@Test(dependsOnMethods="verifyHomePageLanding",description = "Verify the selection of local shoping language and reselect the English language")
	public void verifyLanguageSelectionForShoping() throws Exception {
		objHome = new HomeOR(driver);
		objHome.changeShoopingLanguage();
		Assert.assertEquals(objHome.getChangeLanguageEnglishLinkText(), prop.getProperty("ChangeLanguageExpectedText"));
		//Change back to the English language
		objHome.rechangeLanguageToEnglish();
		Assert.assertEquals(objHome.getLandingPageActualText(), prop.getProperty("homePageExpectedTitle"));
		
	}
	
	@Test(dependsOnMethods="verifyHomePageLanding")
	public void verifyProductSearch() throws Exception
	{
		String expectedKeywordsInTheProductTitle=ExcelUtill.getCellData(1, 1);
		objProdDetails= new ProductDetailsOR(driver);
		objProdDetails.productSearchUsingKeyword();
		objProdDetails.getOnProductDetailsPage();
		assertTrue(objProdDetails.getAllTheProductsTitleinPageOne().toLowerCase().contains(expectedKeywordsInTheProductTitle.toLowerCase()));
	}
	
	@Test(dependsOnMethods="verifyHomePageLanding")
	public void verifyAddToCartFunctionality() throws Exception
	{
		String actualMessageForEmptyCart=ExcelUtill.getCellData(1, 2);
		String actualMessageForProductAddedInCart=ExcelUtill.getCellData(1, 3);
		objProdDetails= new ProductDetailsOR(driver);
		objProdDetails.navigateToTheaddToCart();
		Assert.assertEquals(objProdDetails.getAddToCardExpectedTextBeforeProductAdd().toLowerCase(), actualMessageForEmptyCart.toLowerCase());
		verifyProductSearch();
		objProdDetails.AddProductInCart();
		Assert.assertEquals(objProdDetails.getProductAdditionSuccessExpectedText().toLowerCase(), actualMessageForProductAddedInCart.toLowerCase());	
	}
	

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
