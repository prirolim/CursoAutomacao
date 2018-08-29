package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import com.util.*;

public class TestWikipedia {
	public WebDriver driver;
	
  @Test(dataProvider = "dbPaises")
  public void testDataDrivenExcel(String nome) {
	  driver.findElement(By.name("search")).sendKeys(nome);;
	  driver.findElement(By.id("searchButton")).click();
	  
	  String nameCountry = driver.findElement(By.id("firstHeading")).getText();
	  
	  assertEquals(nome, nameCountry);
  }
  
  @BeforeTest
  public void beforeMethod() {
		driver = WebDriverUtils.getDriver(WebDriverUtils.getSeleniumProperties("selenium.browser"));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
  }

  @AfterTest
  public void afterMethod() {
	  driver.quit();
  }


  @DataProvider(name= "dbPaises")
  public Object[][] createData() {
	  Object[][] testData = SpreadsheetData.readExcelData("Paises", "src/test/resources/paises.xls", "Dados");
	  return testData;
  }
}
