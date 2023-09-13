package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.phuong.datas.PriceSeatTypeDNSG;

public class TicketPricePage extends PageBase {
	public WebElement titlePriceTable() {
		return driver.findElement(By.xpath("//th[@colspan='7']"));
	}

	public WebElement priceTable(String value) {
		return driver.findElement(By.xpath("//th[contains(text(),'Price(VND)')]/../td[count(//td[text()='" + value
				+ "']/preceding-sibling::td)+1]"));
	}

	public TicketPricePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String getTitlePriceTable() {
		return titlePriceTable().getText();
	}

	public boolean isPriceCorrect() {
		if (priceTable("HS").getText().equals(PriceSeatTypeDNSG.HardSeat.getValue())
				&& priceTable("SS").getText().equals(PriceSeatTypeDNSG.SoftSeat.getValue())
				&& priceTable("SSC").getText().equals(PriceSeatTypeDNSG.SeatAirConditioner.getValue())
				&& priceTable("HB").getText().equals(PriceSeatTypeDNSG.HardSeat.getValue())
				&& priceTable("SB").getText().equals(PriceSeatTypeDNSG.HardSeat.getValue())
				&& priceTable("SBC").getText().equals(PriceSeatTypeDNSG.HardSeat.getValue())) {
			return true;
		} else {
			return false;
		}
	}

}
