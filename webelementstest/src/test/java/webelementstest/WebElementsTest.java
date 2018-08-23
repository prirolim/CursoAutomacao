package webelementstest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://treinoautomacao.hol.es/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaNome() throws InterruptedException {
		WebElement txtNome = driver.findElement(By.name("txtbox1"));
		WebElement txtDisabled = driver.findElement(By.name("txtbox2"));
		if (txtNome.isEnabled()) {
			txtNome.sendKeys("Priscila");
		}
		
		Thread.sleep(3000);
		assertEquals("O nome é igual ao esperado", "Priscila", txtNome.getAttribute("value"));
		assertFalse("O componente deveria estar desebilitado!", txtDisabled.isEnabled());
	}
	
	@Test
	public void testValidacaoRadio() throws InterruptedException{
		List<WebElement> elementsRadio  = driver.findElements(By.name("radioGroup1"));
		WebElement elementRadio3 = elementsRadio.get(2);
		elementRadio3.click();
		Thread.sleep(3000);
		
		assertTrue("O elemento está selecionado!", elementRadio3.isSelected());
	}
	
	@Test
	public void testValidacaoCheck() throws InterruptedException{
		List<WebElement> elementsCheck  = driver.findElements(By.name("chkbox"));
		WebElement elementCheck3 = elementsCheck.get(2);
		WebElement elementCheck4 = elementsCheck.get(3);
		elementCheck3.click();
		elementCheck4.click();
		Thread.sleep(3000);
		
		assertTrue("O elemento está selecionado!", elementCheck3.isSelected());
		assertTrue("O elemento está selecionado!", elementCheck4.isSelected());
	}
	
	@Test
	public void testValidacaoDrop7() throws InterruptedException{
		WebElement dropdownlist = driver.findElement(By.name("dropdownlist"));
		Select listboxelements = new Select(dropdownlist);
		listboxelements.selectByIndex(6);
		List<WebElement> elementos = listboxelements.getOptions();
		
		Thread.sleep(3000);
		
		assertTrue("Opção está selecionada", elementos.get(6).isSelected());
	}
	
	@Test
	public void testValidacaoDropMulti() throws InterruptedException{
		WebElement multiselectdropdown = driver.findElement(By.name("multiselectdropdown"));
		Select listboxelementsMulti = new Select(multiselectdropdown);
		
		if (listboxelementsMulti.isMultiple()) {
			listboxelementsMulti.selectByIndex(4);
			listboxelementsMulti.selectByIndex(7);
			listboxelementsMulti.selectByIndex(8);
		}
		
		Thread.sleep(3000);
		
		List<WebElement> elementosSelecionados = listboxelementsMulti.getAllSelectedOptions();
		
		for (WebElement e : elementosSelecionados) {
			System.out.println("Lista selecionada: " + e.getText());
			boolean isSelected = ((e.getText().equals("Item 5")) 
					|| (e.getText().equals("Item 8"))
					|| (e.getText().equals("Item 9")));
			assertTrue(isSelected);
		}
		
//		assertTrue("Opções estão selecionadas", elementosSelecionados.get(4).isSelected() 
//				&& elementosSelecionados.get(7).isSelected() 
//				&& elementosSelecionados.get(8).isSelected());
	}
}
