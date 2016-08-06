package com.suning.sample.web.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class UserListTest extends WebBaseTest{
	
	@Test
	public void testUserList(){
		WebDriver driver = initWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(getWebRoot() + "/ftl/user/search.htm");
		
		WebElement element = driver.findElement(By.id("ttlcnt"));
        String ttlcnt = element.getText();
        System.out.println(ttlcnt);
        assert "total: 31".equals(ttlcnt);
		System.out.println("Page title is: " + driver.getTitle());
		//assert("table result".equals(element.getText()));
		driver.quit();
	}

}
