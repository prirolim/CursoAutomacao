package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvalidPage {
	private WebDriver driver;

	public InvalidPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDirected() {
		driver.findElement(By.id("not_me_link"));
		return true;
	}

}
