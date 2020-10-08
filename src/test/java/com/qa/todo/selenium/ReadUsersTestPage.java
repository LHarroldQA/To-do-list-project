package com.qa.todo.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadUsersTestPage {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void readUsersPage() {
		WebElement userNavBarButton;
		
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		userNavBarButton = driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]/a"));
		userNavBarButton.click();
		
		String title = driver.getTitle();
		assertEquals("Users",title);
	}
	
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}

}
