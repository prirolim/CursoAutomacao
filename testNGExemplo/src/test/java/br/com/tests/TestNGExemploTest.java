package br.com.tests;

import org.testng.annotations.Test;

import com.util.CaptureScreenShot;

import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TestNGExemploTest {
	  WebDriver driver;
	
  @Test
  public void testCalculadora() {
	  driver.findElement(By.id("lst-ib")).sendKeys("2+2");
	  driver.findElement(By.name("btnK")).click();
	  assertEquals("3", driver.findElement(By.id("cwos")).getText());
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
  }

  @AfterMethod
  public void afterMethod(ITestResult testResult) throws IOException {
	System.out.println(testResult.getStatus());
	if (testResult.getStatus() == ITestResult.FAILURE) {
		  new CaptureScreenShot(driver).captureScreenShot(testResult.getName());
	  }
	  driver.quit();
  }

}
