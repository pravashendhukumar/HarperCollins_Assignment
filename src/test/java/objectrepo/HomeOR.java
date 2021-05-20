package objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomeOR {
	public WebDriver driver;

	public HomeOR(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'card-lite')]//span[contains(text(),'You are on amazon.com')]")
	private WebElement landingPageExpectedText;

	@FindBy(xpath = "//div[contains(@class,'navAccessibility')]//ul//li//a[text()='Careers']")
	private WebElement footerCarrersLink;

	@FindBy(xpath = "//a[@aria-label='Choose a language for shopping.']")
	private WebElement HeaderSectionLanguageSelector;

	@FindBy(xpath = "(//a//span[text()='Português - PT'])[1]")
	private WebElement portugeseLangSelectionForShoping;
	
	@FindBy(xpath = "//div[contains(@class,'radio')]//input//following::span[contains(text(),'English')]")
	private WebElement ReselectionToEnglish;
	
	@FindBy(xpath = "//span[@class='a-button-inner']//span[text()='Save Changes']")
	private WebElement LanguageSelectionSaveChangeButton;
	
	@FindBy(xpath = "//span[@class='a-button-inner']//span[text()='Save Changes']")
	private WebElement CarrerPageValidationText;
	
	@FindBy(xpath = "//a[text()='Click here to shop in English']")
	private WebElement changeBackToEnglishLink;
	
	public String getLandingPageActualText() {
		return landingPageExpectedText.getText();
	}

	public void goToCarrerPage() {
		footerCarrersLink.click();
	}
	
	public String getCarrerPageExpectedText() {
		return CarrerPageValidationText.getText();
	}

	public void changeShoopingLanguage() {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(HeaderSectionLanguageSelector).perform();
		Reporter.log("Done Mouse hover on Choose Language Icon");
	     //Mouse hover to portuguese Language selection
	     actions.moveToElement(portugeseLangSelectionForShoping).click().build().perform();
	     Reporter.log("Done Mouse hover on Portegese Language selection box");
	     Reporter.log("Test Pass-Successfully able to change the shoping Language");
	}
	
	public String getChangeLanguageEnglishLinkText()
	{
		return changeBackToEnglishLink.getText();
	}
	
	public void rechangeLanguageToEnglish()
	{
		Actions actions = new Actions(driver);
		changeBackToEnglishLink.click();
		actions.moveToElement(ReselectionToEnglish).click().build().perform();
		actions.moveToElement(LanguageSelectionSaveChangeButton).click().build().perform();		
	}
	
}
