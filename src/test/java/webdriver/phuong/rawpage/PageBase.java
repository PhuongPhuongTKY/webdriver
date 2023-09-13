package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageBase {
	protected WebDriver driver;

	By mnLogin = By.xpath("//span[text()='Login']/..");

	public PageBase(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage gotoLoginPage() {
		driver.findElement(mnLogin).click();
		return new LoginPage(driver);
	}

	private WebElement contactTab() {
		return driver.findElement(By.xpath("//span[text() = 'Contact']"));
	}

	private WebElement bookTicketTab() {
		return driver.findElement(By.xpath("//span[text()='Book ticket']"));
	}

	private WebElement logout() {
		return driver.findElement(By.xpath("//span[text()='Log out']"));
	}

	private WebElement registerTab() {
		return driver.findElement(By.xpath("//span[text()='Register']"));
	}

	private WebElement changePasswordTab() {
		return driver.findElement(By.xpath("//span[text()='Change password']"));
	}

	private WebElement timeTableTab() {
		return driver.findElement(By.xpath("//span[text()='Timetable']"));
	}

	private WebElement myticketTab() {
		return driver.findElement(By.xpath("//span[text()='My ticket']"));
	}

	public ContactPage gotoContactPage() {
		contactTab().click();
		return new ContactPage(driver);
	}

	public BookTicketPage gotoBookTicketPage() {
		bookTicketTab().click();
		return new BookTicketPage(driver);
	}

	public LoginPage gotoBookTicketWithoutLogin() {
		bookTicketTab().click();
		return new LoginPage(driver);
	}

	public RegisterPage gotoRegisterPage() {
		registerTab().click();
		return new RegisterPage(driver);
	}

	public TimeTablePage gotoTimPage() {
		timeTableTab().click();
		return new TimeTablePage(driver);
	}

	public TicketPricePage gotoTicketPricePage() {
		registerTab().click();
		return new TicketPricePage(driver);
	}

	public MyTicketPage gotoMyTicketPage() {
		myticketTab().click();
		return new MyTicketPage(driver);
	}

	public HomePage gotoLogoutPage() {
		logout().click();
		return new HomePage(driver);
	}

	public ChangePasswordPage gotoChangePasswordPage() {
		changePasswordTab().click();
		return new ChangePasswordPage(driver);
	}

}
