package com.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.pageobjects.BaseClass;
import com.pageobjects.MainPage;

@Test(description = "SheetOne Tests", dataProvider = "SheetOneData")
public class DataDriverTestClass1 extends BaseClass {

	public void test1(String FirstName, String LastName, String Email) {

		MainPage HomePG = PageFactory.initElements(driver, MainPage.class);

		Map<String, String> logInData = new HashMap<String, String>();
		logInData.put("userName", FirstName);
		logInData.put("password", LastName);
		logInData.put("email", Email);
		
		HomePG.FillUpRegistrationForm(logInData);
	}
}
