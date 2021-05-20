package objectrepo;


import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ExcelUtill;

public class ProductDetailsOR {

	public WebDriver driver;

	public ProductDetailsOR(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	private WebElement homePageSearchBox;
	
	@FindBy(xpath = "//span[text()='SAMSUNG']//parent::a")
	private WebElement filterByBrandSamsung;
	
	@FindBy(xpath = "//h2//a[@class='a-link-normal a-text-normal']")
	private List<WebElement> getAllFilteredProductTitle;
	
	@FindBy(xpath = "(//h2//a[@class='a-link-normal a-text-normal'])[1]")
	private WebElement firstVisibleSearchedProduct;
	
	@FindBy(xpath = "//a[@id='nav-cart']")
	private WebElement AddToCartLink;
	
	@FindBy(xpath = "//h2[contains(text(),'Your Amazon Cart is empty')]")
	private WebElement getValidationTextForEmptyCart;
	
	@FindBy(xpath = "//input[@title='Add to Shopping Cart']")
	private WebElement addToCartButtonInProdDetailsPage;
	
	@FindBy(xpath = "//h1[contains(text(),'Added to Cart')]")
	private WebElement successMessageWhenProdIsAdded;

	
	public void productSearchUsingKeyword() throws Exception {
		homePageSearchBox.sendKeys(ExcelUtill.getCellData(1, 0)+ Keys.ENTER);
	}
	public void getOnProductDetailsPage()
	{
		filterByBrandSamsung.click();
	}
	public String getAllTheProductsTitleinPageOne() {
		String visibleProdTitle=null;
		for (WebElement getProdTitle : getAllFilteredProductTitle) {
			{
				visibleProdTitle=getProdTitle.getText();
				break;
			}
			
		}
		return visibleProdTitle;
			}
	public void navigateToTheaddToCart() {
		AddToCartLink.click();
	}
	
	public String getAddToCardExpectedTextBeforeProductAdd() {
		return getValidationTextForEmptyCart.getText();
	}
	
    public void AddProductInCart() {
		
	firstVisibleSearchedProduct.click();
	addToCartButtonInProdDetailsPage.click();
	}
	
    public String getProductAdditionSuccessExpectedText() {
		return successMessageWhenProdIsAdded.getText();
	}
	

}
