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

public class IndexPageTests {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@Test
	public void pageTitleTest() {
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		String title = driver.getTitle();
		assertEquals("To-do list project",title);
	}
	
	@Test
	public void taskDeleteTest() {
		List<WebElement> tasks;
		WebElement deleteButton;
		
		
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		tasks = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		deleteButton = driver.findElement(By.xpath("//*[@id=\"delButton\"]"));
		
		int originalSize = tasks.toArray().length;
		
		deleteButton.click();
		tasks = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		int afterDeleteSize = tasks.toArray().length;
		
		assertEquals(originalSize - 1,afterDeleteSize);
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
	
}
