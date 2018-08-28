package com.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.page.InvalidPage;
import com.page.LoginPage;
import com.page.TimelinePage;

public class LoginTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testLoginValido() {
		LoginPage loginPage = new LoginPage(driver);
		TimelinePage timelinePage = loginPage.open()
				.sendUser("treinoautomacao01@gmail.com")
				.sendPassword("teste1000")
				.logarValido();
		assertTrue("Deveria ter logado", timelinePage.isLogged("Selenium"));
	}
	
	@Test
	public void testLoginInvalido() {
		LoginPage loginPage = new LoginPage(driver);
		InvalidPage invalidPage = loginPage.open()
				.sendUser("treinoautomacao01@gmail.com")
				.sendPassword("teste")
				.logarInvalido();
		assertTrue("Deveria dar erro de login", invalidPage.isDirected());
	}
	
	@Test
	public void testPostTimeline() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		TimelinePage timelinePage = loginPage.open()
				.sendUser("treinoautomacao01@gmail.com")
				.sendPassword("teste1000")
				.logarValido()
				.post("Automation test with WebDrive Rocks. Eu");
		Thread.sleep(3000);
		//assertTrue("Deveria dar erro de login", invalidPage.isDirected());
	}

}
