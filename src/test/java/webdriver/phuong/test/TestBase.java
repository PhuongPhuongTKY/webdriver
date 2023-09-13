package webdriver.phuong.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import webdriver.phuong.rawpage.HomePage;

public class TestBase {
	
	protected WebDriver driver = null;
	protected HomePage homePage = null;
	
	private WebElement btnVisits () {
		return driver.findElement(By.xpath("//span[text()='Visit Site']/.."));
	}
	
	public void activeAcc () throws Exception {
		Thread.sleep(5000);
		GmailPageAccess gmail = new GmailPageAccess();
		String activeURL = gmail.getURL("thanhletraining01@gmail.com");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(activeURL);
//		btnVisit().click();
	}
	
	public void resetPassword () throws Exception {
		Thread.sleep(5000);
		GmailPageAccess gmail = new GmailPageAccess();
		String activeURL = gmail.getURL("thanhletraining01@gmail.com");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(activeURL);
	}
 
  
  @BeforeMethod
  public void beforeMethod() {
	  WebDriverManager.chromedriver().setup();
	  
	  driver = new ChromeDriver();
	  
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  
	  driver.manage().window().maximize();
	  
	  driver.get("http://sele");
	  
//	  btnVisit().click();
	  
	  homePage = new HomePage(driver);
	  	  
  }

  @AfterMethod
  public void afterMethod() {
	//driver.quit();
  }

}
