package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase{
	
	private WebElement txtUsername() {
		return driver.findElement(By.xpath("//input[@id='username']"));
	}
	
	private WebElement txtPassword() {
		return driver.findElement(By.xpath("//input[@id='password']"));
	}
	
	private WebElement btnLogin() {
		return driver.findElement(By.xpath("//input[@value='login']"));
	}
	
	private WebElement errorMessage() {
		return driver.findElement(By.xpath("//p[@class='message error LoginForm']"));
	}
	
	private WebElement forgotPswLink() {
		return driver.findElement(By.xpath("//a[@href='/Account/ForgotPassword.cshtml']"));
	}

	
	public LoginPage(WebDriver driver) {
		super(driver);	
	}
	
	public ForgotPasswordPage gotoForgotPasswordPage() {
		forgotPswLink().click();
		return new ForgotPasswordPage(driver);
	}
	
	public LoginPage loginFail(String username, String password) {
		txtUsername().sendKeys(username);
		txtPassword().sendKeys(password);
		btnLogin().click();
		return this;	
	}
	
	public HomePage loginSuccess(String username, String password) {
		txtUsername().sendKeys(username);
		txtPassword().sendKeys(password);
		btnLogin().click();
		return new HomePage(driver);
	}
	
	public String getErrorMessages() {;
		return errorMessage().getText();	
	}
	
	public BookTicketPage loginFromBookTicket(String username, String password) {
		txtUsername().sendKeys(username);
		txtPassword().sendKeys(password);
		btnLogin().click();
		return new BookTicketPage(driver);
	}

	
	public boolean isLoginDisplayFromBookTicket() {
		String getURLLoginPage = driver.getCurrentUrl();
		String expectURLLoginPage = "https://a08a-123-30-67-33.ngrok-free.app/Account/Login.cshtml?ReturnUrl=/Page/BookTicketPage.cshtml";
		if(getURLLoginPage.equals(expectURLLoginPage)) {
			return true;
		}
		return false;	
	}
}
