package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage open() {
		driver.get("https://www.facebook.com/");
		return this;	
	}

	public LoginPage sendUser(String username) {
		driver.findElement(By.id("email")).sendKeys(username);
		return this;
	}

	public LoginPage sendPassword(String password) {
		driver.findElement(By.id("pass")).sendKeys(password);
		return this;
	}

	public TimelinePage logarValido() {
		driver.findElement(By.id("loginbutton")).click();
		return new TimelinePage(driver);
	}
	
	public InvalidPage logarInvalido() {
		driver.findElement(By.id("loginbutton")).click();
		return new InvalidPage(driver);
	}
	
	

}
