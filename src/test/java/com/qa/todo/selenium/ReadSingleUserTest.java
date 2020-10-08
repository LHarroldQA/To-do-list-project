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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadSingleUserTest {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	
	//only works with spring running
	@Test
	public void readUserNavTest() {
		WebElement usersPageNav;
		WebElement viewButton;
		
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		usersPageNav = driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]/a"));
		usersPageNav.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/table/thead/tr[2]/td[6]/a")));
		
		viewButton = driver.findElement(By.xpath("/html/body/div[2]/table/thead/tr[2]/td[6]/a"));
		viewButton.click();
		
		String title = driver.getTitle();
		assertEquals("User",title);
	}
	
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}

}
