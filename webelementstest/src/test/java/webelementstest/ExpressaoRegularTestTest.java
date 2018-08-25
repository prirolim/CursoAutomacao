package webelementstest;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExpressaoRegularTestTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.geradordecpf.org/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testCPFsemPonto() throws InterruptedException {
		driver.findElement(By.id("btn-gerar-cpf")).click();
		
		Thread.sleep(3000);
		
		String cpf = driver.findElement(By.id("numero")).getAttribute("value");
		
		boolean valida = cpf.matches("^[0-9]{11}$");
		
		assertTrue(valida);
	}
	
	@Test
	public void testCPFcomPonto() throws InterruptedException {
		driver.findElement(By.id("cbPontos")).click();
		driver.findElement(By.id("btn-gerar-cpf")).click();
		
		Thread.sleep(3000);
		
		String cpf = driver.findElement(By.id("numero")).getAttribute("value");
		
		boolean valida = cpf.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$");
		
		assertTrue(valida);
	}
	
	

}
