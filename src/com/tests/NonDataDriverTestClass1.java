package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pageobjects.BaseClass;

@Test
public class NonDataDriverTestClass1 extends BaseClass {

	public void simpletest1() {
		Assert.assertEquals("AAA", "AA");
	}

	public void simpletest2() {
		Assert.assertEquals("AAA", "AAA");
	}

	public void simpletest3() {
		Assert.assertEquals("AAA", "AAA");
	}

}
