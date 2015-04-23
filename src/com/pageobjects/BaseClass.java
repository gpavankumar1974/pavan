package com.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import atu.ddf.file.ExcelFile;
import atu.ddf.selenium.SeleniumTestNGHelper;

public class BaseClass {

	/**
	 * @author ITS User QA
	 */

	public static WebDriver driver;

	{
		if (System.getProperties().getProperty("os.name").toLowerCase()
				.contains("windows")) {
			System.setProperty("atu.reporter.config",
					"C:\\Users\\shaider\\\\workspace\\Demo\\atu.properties");
		}
	}

	public static boolean implicitwait(long time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// @BeforeMethod
	public static boolean LaunchBrowser() {
		driver.get("https://www.linkedin.com");
		System.out.println("Launching Browser ......");
		Reporter.log("Launching Browser ......");
		return true;
	}

	@Parameters("browser")
	@BeforeMethod
	public static boolean LaunchBrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox")
				&& System.getProperties().getProperty("os.name").toLowerCase()
						.contains("windows")) {

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, true);
			System.setProperty("webdriver.firefox.bin",
					"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver(capabilities);

		} else if (browser.equalsIgnoreCase("ie")
				&& System.getProperties().getProperty("os.name").toLowerCase()
						.contains("windows")) {

			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();

			capabilities.setCapability(
					InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);

			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capabilities.setCapability(
					InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capabilities.setCapability(
					InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(
					InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");

			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\shaider\\workspace\\Demo\\\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
		}

		else if (browser.equalsIgnoreCase("chrome")
				&& System.getProperties().getProperty("os.name").toLowerCase()
						.contains("windows")) {
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\shaider\\workspace\\Demo\\chromedriver.exe");
			driver = new ChromeDriver(capabilities);
		}

		driver.navigate().to("https://www.linkedin.com");
		driver.manage().window().maximize();
		implicitwait(2000);
		System.out.println("Launching Browser : " + browser);
		Reporter.log("Launching Browser : " + browser);

		return true;
	}

	@Parameters("browser")
	@AfterMethod
	public static boolean CloseBrowser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			WindowsUtils.tryToKillByName("firefox.exe");
			// driver.quit();
		} else if (browser.equalsIgnoreCase("ie")) {
			// WindowsUtils.tryToKillByName("iexplore.exe");
			WindowsUtils.tryToKillByName("IEDriverServer.exe");
			// driver.quit();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// WindowsUtils.tryToKillByName("chrome.exe");
			// WindowsUtils.tryToKillByName("chromedriver.exe");
			driver.quit();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver.quit();
		}

		System.out.println("Closing Browser : " + browser);
		Reporter.log("Closing Browser : " + browser);
		return true;
	}

	// @AfterMethod
	public static boolean CloseBrowser() {
		WindowsUtils.tryToKillByName("firefox.exe");
		System.out.println("Closing Browser ......");
		Reporter.log("Closing Browser ......");
		return true;
	}

	@AfterMethod
	public void PrintTestCaseEnd(ITestResult result) {
		System.out
				.println("End Of Test: " + result.getMethod().getMethodName());
		Reporter.log("End Of Test: " + result.getMethod().getMethodName());
	}

	@DataProvider(name = "SheetOneData")
	public Iterator<Object[]> SheetOneTestData()
			throws DataDrivenFrameworkException {
		String excelResource = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + "ExcelTestData.xlsx";
		ExcelFile excelFile = new ExcelFile(excelResource);
		excelFile.setSheetName("SheetOne");
		excelFile.setTestCaseHeaderName("TestCaseNameColumn");
		List<List<String>> OneTestData = excelFile
				.getDataUsingTestCaseName("SheetOneTestCase");
		return SeleniumTestNGHelper.toObjectArrayIterator(OneTestData);
	}

	@DataProvider(name = "SheetTwoData")
	public Iterator<Object[]> SheetTwoTestData()
			throws DataDrivenFrameworkException {
		String excelResource = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + "ExcelTestData.xlsx";
		ExcelFile excelFile = new ExcelFile(excelResource);
		excelFile.setSheetName("SheetTwo");
		excelFile.setTestCaseHeaderName("TestCaseNameColumn");
		List<List<String>> TwoTestData = excelFile
				.getDataUsingTestCaseName("SheetTwoTestCase");
		return SeleniumTestNGHelper.toObjectArrayIterator(TwoTestData);
	}
}
