package com.pageobjects;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class MainPage extends BaseClass {

	public MainPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "firstName-coldRegistrationForm")
	@CacheLookup
	public static WebElement firstNameTextBox;

	public MainPage TypeFirstName(String IncomingValue) {

		firstNameTextBox.sendKeys(IncomingValue);
		System.out.println("Type First Name ...");
		Reporter.log("Type First Name ...");
		return this;
	}

	@FindBy(how = How.ID, using = "lastName-coldRegistrationForm")
	@CacheLookup
	public static WebElement lastNameTextBox;

	public MainPage TypeLastName(String IncomingValue) {
		lastNameTextBox.sendKeys(IncomingValue);
		System.out.println("Type last Name ...");
		Reporter.log("Type Last Name ...");
		return this;
	}

	@FindBy(how = How.ID, using = "email-coldRegistrationForm")
	@CacheLookup
	public static WebElement emailTextBox;

	public MainPage TypeEMailAddress(String IncomingValue) {
		emailTextBox.sendKeys(IncomingValue);
		System.out.println("Type Email ...");
		Reporter.log("Type Email ...");
		return this;
	}

	public MainPage FillUpRegistrationForm(Map<String, String> data) {
		TypeFirstName(data.get("userName"));
		TypeLastName(data.get("password"));
		TypeEMailAddress(data.get("email"));

		// ClickSignInButton();
		System.out.println("Fill Up Registration Form with Data: " + data);
		Reporter.log("Fill Up Registration Form with Data: " + data);
		return this;
	}
}
