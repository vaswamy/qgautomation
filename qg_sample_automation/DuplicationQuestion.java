package testngQGTestCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DuplicationQuestion {

	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}

	// Ask A Question to specific "first user" in the feed , and try to ask the same  Question again. Verify that you were able to ask the same question again or not
	@Test
	public void DuplicationQuestionTest() throws InterruptedException {

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys("itsmesaketh@gmail.com");
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='my_all_feed']/div/div/div[2]")).click();

		WebElement askusername = driver.findElement(By.xpath("//div[@class='f-user-name text-left pl-3']/p/a"));
		Thread.sleep(2000);
		String nameText = askusername.getText();
		
		driver.findElement(By.xpath("//div[@id='extramenu']/button")).click();
		Thread.sleep(2000);
		WebElement inputText = driver.findElement(By.xpath("//textarea[@id='myInput']"));
		Thread.sleep(2000);
		WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary float-right mt-2']"));

		String Que = date();
		String dupQue = Que;

		inputText.sendKeys(Que);
		submit.click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[@class='my_all_feed']/div/div/div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='extramenu']/button")).click();

		Thread.sleep(2000);

		WebElement inputText1 = driver.findElement(By.xpath("//textarea[@id='myInput']"));
		Thread.sleep(2000);
		WebElement submit1 = driver.findElement(By.xpath("//button[@class='btn btn-primary float-right mt-2']"));
		Thread.sleep(2000);
		inputText1.sendKeys(dupQue);
		submit1.click();
		Thread.sleep(2000);

		
		int no= driver.findElements(By.xpath("//div[@class='jconfirm-buttons']")).size();
		
		Assert.assertNotEquals(no, 0);
		
		if (driver.findElements(By.xpath("//div[@class='jconfirm-buttons']")).size() != 0)

		{

			driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button:nth-child(2)")).click();

		}

	}

	public void scrollToElement(int x, int y) {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");

	}

	public String date() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		Date date = new Date();

		String date1 = dateFormat.format(date);

		return date1;

	}

}