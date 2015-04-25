package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pageobjects.BaseClass;

@Test
public class NonDataDriverTestClass3 extends BaseClass {

	public void SeleniumNonDataDrivenTest4() {
		Assert.assertEquals("AAA", "AA");
	}

	public void SeleniumSimpleTest5() {
		Assert.assertEquals("AAA", "AAA");
	}

	public void SeleniumSimpleTest6() {
		Assert.assertEquals("AAA", "AAA");
	}

}
