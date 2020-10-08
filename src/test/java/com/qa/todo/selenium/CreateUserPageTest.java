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

public class CreateUserPageTest {

	public static WebDriver driver;
	
	@BeforeClass
	public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Liam\\Documents\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\To-do-project\\src\\test\\resources\\drivers\\chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void navToUserCreate() {
		WebElement userNavBarButton;
		WebElement userCreateButton;
		
		driver.get("http://127.0.0.1:5500/html/index.html");
		
		userNavBarButton = driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]/a"));
		userNavBarButton.click();
		
		userCreateButton = driver.findElement(By.xpath("/html/body/div[2]/a"));
		userCreateButton.click();
		
		String title = driver.getTitle();
		assertEquals("Create User",title);
	}
	
	@Test
	public void createUserTest() {
		List<WebElement> users;
		WebElement createPageButton;
		WebElement userFirstNameBox;
		WebElement userSurnameBox;
		WebElement userAgeBox;
		WebElement createButton;
		WebElement readAllUsersButton;
		
		driver.get("http://127.0.0.1:5500/html/users.html");
		
		users = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		createPageButton = driver.findElement(By.xpath("/html/body/div[2]/a"));
		
		int originalUsersNumber = users.toArray().length;
		System.out.println(originalUsersNumber);
		createPageButton.click();
		
		userFirstNameBox = driver.findElement(By.xpath("//*[@id=\"userFirstName\"]"));
		userSurnameBox = driver.findElement(By.xpath("//*[@id=\"userSurname\"]"));
		userAgeBox = driver.findElement(By.xpath("//*[@id=\"userAge\"]"));
		createButton = driver.findElement(By.xpath("/html/body/div/form/button"));
		readAllUsersButton = driver.findElement(By.xpath("/html/body/div/ul/li[2]/a"));
		
		userFirstNameBox.sendKeys("Jonny");
		userSurnameBox.sendKeys("Bravo");
		userAgeBox.sendKeys("30");
		createButton.click();
		readAllUsersButton.click();
		
		users = driver.findElements(By.xpath("/html/body/div[2]/table/thead/*"));
		int newUsersNumber = users.toArray().length;
		System.out.println(newUsersNumber);
		
		assertEquals("Users",driver.getTitle());
	}
	
	
	@AfterClass
	public static void teardown() {
		driver.close();
	}
}
