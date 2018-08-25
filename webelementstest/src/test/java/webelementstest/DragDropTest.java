package webelementstest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragDropTest {
	public WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testArrasta() throws InterruptedException, IOException {
		File scrnShot;
		
		WebElement drag = driver.findElement(By.id("draggable"));
		assertEquals("Drag me to my target", drag.getText());
		
		WebElement drop = driver.findElement(By.id("droppable"));
		assertEquals("Drop here", drop.getText());
		
		scrnShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrnShot , new File("C:\\Priscila\\screenshot01.png"));
		
		new Actions(driver).dragAndDrop(drag, drop).perform();
		
		Thread.sleep(3000);
		
		scrnShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrnShot , new File("C:\\Priscila\\screenshot02.png"));
		
		assertEquals("Dropped!", drop.getText());
	}

}
