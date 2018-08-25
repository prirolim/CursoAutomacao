package webelementstest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavegacaoJanelasTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://treinoautomacao.hol.es/index.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testNavegacaoJanelas() throws InterruptedException {
		assertHomeIndex();
		
		WebElement linkDragDrop = driver.findElement(By.linkText("Drag and Drop"));
		linkDragDrop.click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(tabs.get(1));
		
		assertDagAndDrop();
		
		Thread.sleep(3000);
		
		driver.switchTo().window(tabs.get(0));
		
		Thread.sleep(3000);
		
		assertHomeIndex();
	}
	
	@Test
	public void testValidaAcoesBrowse() throws InterruptedException {
		assertHomeIndex();
		
		WebElement calculadora = driver.findElement(By.linkText("Calculadora"));
		calculadora.click();
		
		Thread.sleep(3000);
		
		assertCalculadora();
		
		WebElement localizarTable = driver.findElement(By.linkText("Localizar Table"));
		localizarTable.click();
		
		Thread.sleep(3000);
		
		assertLocalizarTable();
		
		driver.navigate().back();
		driver.navigate().back();
		
		Thread.sleep(3000);
		
		assertHomeIndex();
		
		driver.navigate().forward();
		driver.navigate().forward();
		
		Thread.sleep(3000);
		
		assertLocalizarTable();
	}
	
	private void assertHomeIndex() {
		assertEquals("Treino Automação de Testes", driver.getTitle());
	}
	
	private void assertDagAndDrop() {
		assertEquals("Mootools Drag and Drop Example", driver.getTitle());
	}
	
	private void assertCalculadora() {
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
	}
	
	private void assertLocalizarTable() {
		assertEquals("Trabalhando com tables", driver.getTitle());
	}
	
	
}
