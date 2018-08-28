package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class TimelinePage {
	private WebDriver driver;

	public TimelinePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLogged(String nome) {
		String nomeLogado = driver.findElement(By.cssSelector("._1vp5")).getText();
		return nome.contains(nomeLogado);
	}

	public TimelinePage post(String post) {
		driver.findElement(By.name("xhpc_message")).sendKeys(post);
		driver.findElement(By.name("xhpc_message")).sendKeys(Keys.ENTER);
		return this;
	}
	
	

}
