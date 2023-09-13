package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.phuong.datas.Subjects;
import webdriver.phuong.datas.TicketInformation;

public class BookTicketPage extends PageBase {
	private WebElement selectDate(String date) {
		return driver.findElement(By.xpath("//select[@name='Date']/option[text()='" + date + "']"));
	}

	private WebElement selectDepart(String depart) {
		return driver.findElement(By.xpath("//select[@name='DepartStation']/option[text()='" + depart + "']"));
	}

	private WebElement selectAririve(String arrive) {
		return driver.findElement(By.xpath("//select[@name='ArriveStation']/option[text()='" + arrive + "']"));
	}

	private WebElement selectSeatType(String seatType) {
		return driver.findElement(By.xpath("//select[@name='SeatType']/option[text()='" + seatType + "']"));
	}

	private WebElement selectTicketAmount(String amount) {
		return driver.findElement(By.xpath("//select[@name='TicketAmount']/option[text()='" + amount + "']"));
	}

	private WebElement btnBookTicket() {
		return driver.findElement(By.xpath("//input[@type='submit']"));
	}

	private WebElement msgBookTicketSuccess() {
		return driver.findElement(By.xpath("//h1[@align='center']"));
	}

	private WebElement inforTicket(String type, String value) {
		return driver.findElement(By.xpath("//tr[@class='TableSmallHeader']/th[text()='" + type
				+ "']//../following-sibling::tr/td[text()='" + value + "']"));
	}

	public BookTicketPage(WebDriver driver) {
		super(driver);
	}

	public boolean isBookTicketDisplay() {
		String getURLBookTicketPage = driver.getCurrentUrl();
		String expectedURL = "https://a08a-123-30-67-33.ngrok-free.app/Page/BookTicketPage.cshtml";
		if (getURLBookTicketPage.equals(expectedURL)) {
			return true;
		}
		return false;
	}

	public BookTicketPage bookTicketSuccess(TicketInformation ticketInformation) throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		selectDate(ticketInformation.getDate()).click();
		selectDepart(ticketInformation.getDepartFrom()).click();
		Thread.sleep(5000);
//		wait.until(ExpectedConditions.visibilityOf(selectAririve(ticketInformation.getArriveAt())));
		selectAririve(ticketInformation.getArriveAt()).click();
		selectSeatType(ticketInformation.getSeatType()).click();
		selectTicketAmount(ticketInformation.getTicketAmount()).click();
		btnBookTicket().click();
		return this;
	}

	public String getMsgBookTicketSuccess() {
		return msgBookTicketSuccess().getText();
	}

	public boolean isBookTicketSuccess(TicketInformation ticketInformation) {
		if (inforTicket(Subjects .DepartFrom.getValue(), ticketInformation.getDepartFrom()).getText()
				.equals(ticketInformation.getDepartFrom())
				&& inforTicket(Subjects.Arrive.getValue(), ticketInformation.getArriveAt()).getText()
						.equals(ticketInformation.getArriveAt())
				&& inforTicket(Subjects.SeatType.getValue(), ticketInformation.getSeatType()).getText()
						.equals(ticketInformation.getSeatType())
				&& inforTicket(Subjects.Date.getValue(), ticketInformation.getDate()).getText()
						.equals(ticketInformation.getDate())
				&& inforTicket(Subjects.TicketAmount.getValue(), ticketInformation.getTicketAmount()).getText()
						.equals(ticketInformation.getTicketAmount())) {
			return true;
		} else
			return false;
	}
}
