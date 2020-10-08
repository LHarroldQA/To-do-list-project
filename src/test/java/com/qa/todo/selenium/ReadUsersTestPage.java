package com.qa.todo.selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ReadUsersTestPage {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver(options);
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
	
	@Test
	public void userDeleteTest() {
		List<WebElement> users;
		WebElement deleteButton;
		
		
		driver.get("http://127.0.0.1:5500/html/users.html");
		
		users = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		deleteButton = driver.findElement(By.xpath("/html/body/div[2]/table/thead/tr[2]/td[7]/a"));
		
		int originalSize = users.toArray().length;
		
		deleteButton.click();
		users = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		int afterDeleteSize = users.toArray().length;
		
		assertEquals(originalSize - 1,afterDeleteSize);
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}

}
