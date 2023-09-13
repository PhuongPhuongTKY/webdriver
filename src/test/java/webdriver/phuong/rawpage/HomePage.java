package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase {
	private WebElement welcomMessage() {
		return driver.findElement(By.xpath("//*[@id=\"banner\"]/div/strong"));
	}

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getMsgHomepage() {
		return welcomMessage().getText();
	}

	public boolean isHomePageDisplay() {
		String getURLHomePage = driver.getCurrentUrl();
		String expectHomePageURL = "https://a08a-123-30-67-33.ngrok-free.app/Page/HomePage.cshtml";
		if (getURLHomePage.equals(expectHomePageURL)
				&& (driver.findElements(By.xpath("//span[text()='Log out']")).size() == 0)) {
			return true;
		}
		return false;
	}

}
