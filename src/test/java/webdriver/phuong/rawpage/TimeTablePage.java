package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimeTablePage extends PageBase {
//	private String departFrom;
//	private String arriveAt;
	public WebElement checkPrice() {
		return driver.findElement(By.xpath("//tbody"));
	}

	public TimeTablePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public TicketPricePage checkPrice(String departFrom, String arriveAt) {
		WebElement clickCheckPriceLink = checkPrice().findElement(By.xpath("//td[text()='" + departFrom
				+ "']/following-sibling::td[text()='" + arriveAt + "']/following-sibling::td/a[text()='check price']"));
		clickCheckPriceLink.click();
		return new TicketPricePage(driver);
	}
}
