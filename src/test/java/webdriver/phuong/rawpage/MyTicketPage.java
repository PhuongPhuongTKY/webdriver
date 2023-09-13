package webdriver.phuong.rawpage;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.phuong.datas.Subjects;
import webdriver.phuong.datas.TicketInformation;

public class MyTicketPage extends PageBase {

	private WebElement btnCancel(String depar, String arrive, String SeatType, String date, String amount) {
		return driver.findElement(By.xpath("//td[text()='" + depar + "']/following-sibling::td[text()='" + arrive
				+ "']/following-sibling::td[text()='" + SeatType + "']/following-sibling::td[text()='" + date
				+ "']/following-sibling::td[text()='" + amount + "']/following-sibling::td/input"));
	}

	private WebElement inforTicket(String value) {
		return driver.findElement(By.xpath("//th[contains(text(),'Price(VND)')]/../td[count(//td[text()='" + value
				+ "']/preceding-sibling::td)+1]"));
	}

	public MyTicketPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public MyTicketPage cancelTicket(TicketInformation ticketInformation) {
		btnCancel(ticketInformation.getDepartFrom(), ticketInformation.getArriveAt(), ticketInformation.getSeatType(),
				ticketInformation.getDate(), ticketInformation.getTicketAmount());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Alert alertOK = driver.switchTo().alert();
		alertOK.accept();
		return this;
	}

	public Boolean isCancelTicketSuccess(TicketInformation ticketInformation) {
		if (inforTicket(Subjects.DepartFrom.getValue()).getText().equals(ticketInformation.getDepartFrom())
				&& inforTicket(Subjects.Arrive.getValue()).getText().equals(ticketInformation.getArriveAt())
				&& inforTicket(Subjects.SeatType.getValue()).getText().equals(ticketInformation.getSeatType())
				&& inforTicket(Subjects.Date.getValue()).getText().equals(ticketInformation.getDate())
				&& inforTicket(Subjects.TicketAmount.getValue()).getText()
						.equals(ticketInformation.getTicketAmount())) {
			return false;
		} else {
			return true;
		}
	}
}
