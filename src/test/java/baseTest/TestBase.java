package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.ExcelUtill;

public class TestBase {

	public static WebDriver driver;

	public static Properties prop;

	public static long PAGE_LOAD_TIMEOUT = 20;

	public static long IMPLICIT_WAIT = 20;

	public static final String testDataExcelFileName = "testData.xsls";

	public TestBase() {

		try {

			System.out.println(System.getProperty("user.dir"));
			prop = new Properties();

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");

			prop.load(fis);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void browserSetup() throws Exception {

		ExcelUtill.setExcelFile(System.getProperty("user.dir") + prop.getProperty("excelPath"),
				prop.getProperty("sheetName"));

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\library\\chromedriver\\chromedriver.exe");

			driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("FF") || browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\library\\geckodriver\\geckodriver.exe");

			driver = new FirefoxDriver();

		}

		else if (browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internetexplorer")) {

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\library\\IEDriver.exe");

			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}

	//Creating a method getScreenshot and passing two parameters 
	//driver and screenshotName
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
	                //below line is just to append the date format with the screenshot name to avoid duplicate names		
	                String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
	                //Returns the captured file path
			return destination;
	}
}
