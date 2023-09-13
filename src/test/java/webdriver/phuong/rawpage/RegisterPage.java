package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.phuong.datas.User;

public class RegisterPage extends PageBase {

	private WebElement txtEmail() {
		return driver.findElement(By.xpath("//input[@id='email']"));
	}

	private WebElement txtPassWord() {
		return driver.findElement(By.xpath("//input[@id='password']"));
	}

	private WebElement txtConfirmPaw() {
		return driver.findElement(By.xpath("//input[@id='confirmPassword']"));
	}

	private WebElement txtPID() {
		return driver.findElement(By.xpath("//input[@id='pid']"));
	}

	private WebElement btnRegister() {
		return driver.findElement(By.xpath("//input[@value='Register']"));
	}

	private WebElement errMsg() {
		return driver.findElement(By.xpath("//p[@class='message error']"));
	}

	private WebElement welcomMsg() {
		return driver.findElement(By.xpath("//h1[@align='center']"));
	}

	private WebElement loginTab() {
		return driver.findElement(By.xpath("//span[text()='Login']/.."));
	}

	private WebElement errMsgPsw() {
		return driver.findElement(By.xpath("//label[@for='password' and @class='validation-error']"));
	}

	private WebElement errMsgPid() {
		return driver.findElement(By.xpath("//label[@for='pid' and @class='validation-error']"));
	}

	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage gotoLoginPage() {
		loginTab().click();
		return new LoginPage(driver);
	}

	public RegisterPage registerAc(User user) {
		txtEmail().sendKeys(user.getEmail());
		txtPassWord().sendKeys(user.getPassword());
		txtConfirmPaw().sendKeys(user.getConfirmPassword());
		txtPID().sendKeys(user.getPid());
		btnRegister().click();
		return this;
	}

	public RegisterPage registerAcFail(String email, String password, String confirmPassword, String pid) {
		txtEmail().sendKeys(email);
		txtPassWord().sendKeys(password);
		txtConfirmPaw().sendKeys(confirmPassword);
		txtPID().sendKeys(pid);
		btnRegister().click();
		return this;
	}

	public String getErrorMessage() {
		return errMsg().getText();
	}

	public String getWelcomeMsg() {
		return welcomMsg().getText();
	}

	public String getErrorMsgPasw() {
		return errMsgPsw().getText();
	}

	public String getErrorMsgPid() {
		return errMsgPid().getText();
	}
}
