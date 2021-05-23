package co.interseed.qa.ui_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterPageTest {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void SetUpEnvironment() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--start-fullscreen");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--headless");
		this.driver = new ChromeDriver(options);
		driver.get("https://app.interseed.co");
		this.wait = new WebDriverWait(driver, 5);
	}

	@AfterMethod
	public void ShutDown() {
		this.driver.close();
	}

	@Test
	public void RegisterSanityTest() throws InterruptedException {
		System.out.println("REGISTER PAGE!!");
		driver.findElement(By.linkText("Register")).click();

		String expectedLoginUrl = "https://app.interseed.co/register";
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, expectedLoginUrl);
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("User");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("user123");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("user123@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("superuser");
		driver.findElement(By.xpath("//input[@id='password2']")).sendKeys("superuser");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		wait.until(ExpectedConditions.textToBe(By.xpath("//small[text()='Username already exists']"), "Username already exists"));
		String expectedUrl = "https://app.interseed.co/register";
		String newUrl = driver.getCurrentUrl();
		Boolean hasAccountExisted = false;
		Boolean expectedHasAccountExisted = true;
		try {
			hasAccountExisted = driver.findElement(By.xpath("//small[text()='Username already exists']")).isDisplayed();
		} catch(Exception e) {
			System.out.println("Fail: Element is Not Found [" + e + "]");
		}
		Assert.assertEquals(newUrl, expectedUrl);
		Assert.assertEquals(hasAccountExisted, expectedHasAccountExisted);
	}
	
	@Test
	public void TestButton() throws InterruptedException {
		System.out.println("TEST BUTTON TEST");
		driver.findElement(By.linkText("Register")).click();
		String expectedUrl = "https://app.interseed.co/register";
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
	}

	@Test
	public void RegisterButtonsTest() throws InterruptedException {
		System.out.println("REGISTER BUTTON START");
		driver.findElement(By.linkText("Register")).click();
		String expectedUrl = "https://app.interseed.co/register";
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Log in")).click();
		
		System.out.println("REGISTER BUTTON LOGIN");

		expectedUrl = "https://app.interseed.co/login";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//button[text() = 'Login']")).click();
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		System.out.println("BACK TO LOGIN");

		driver.findElement(By.linkText("About")).click();
		expectedUrl = "https://app.interseed.co/about";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Resources")).click();
		expectedUrl = "https://app.interseed.co/resources";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Community")).click();
		expectedUrl = "https://app.interseed.co/community";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Landscapes")).click();
		expectedUrl = "https://app.interseed.co/landscapes";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
		
		driver.findElement(By.linkText("Dashboard")).click();
		expectedUrl = "https://app.interseed.co/dashboard";
		url = driver.getCurrentUrl();
		Assert.assertEquals(url, expectedUrl);
	}
}
