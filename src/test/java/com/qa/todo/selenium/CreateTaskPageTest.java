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

public class CreateTaskPageTest {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void createTaskPageTest() {
		WebElement createButton;
		
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		createButton = driver.findElement(By.xpath("/html/body/div[2]/a"));
		createButton.click();
		
		String title = driver.getTitle();
		assertEquals("Create Task",title);
	}
	
	@Test
	public void createTaskTest() {
		WebElement taskCategoryBox;
		WebElement taskDescBox;
		WebElement userIdBox;
		WebElement createButton;
		WebElement readAllTasksButton;
		
		driver.get("http://127.0.0.1:5500/html/createTask.html");
		
//		WebDriverWait wait = new WebDriverWait(driver, 200);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"taskCategory\"]")));
		
		taskCategoryBox = driver.findElement(By.xpath("//*[@id=\"taskCategory\"]"));
		taskDescBox = driver.findElement(By.xpath("//*[@id=\"taskDesc\"]"));
		userIdBox = driver.findElement(By.xpath("//*[@id=\"userId\"]"));
		createButton = driver.findElement(By.xpath("/html/body/div/form/button"));
		readAllTasksButton = driver.findElement(By.xpath("/html/body/div/ul/li[1]/a"));
		
		taskCategoryBox.sendKeys("School");
		taskDescBox.sendKeys("History homework");
		userIdBox.sendKeys("1");
		createButton.click();
		readAllTasksButton.click();		
	}
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}

}
