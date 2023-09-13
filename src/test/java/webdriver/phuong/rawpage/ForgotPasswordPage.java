package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {
	private WebElement txtEmail() {
		return driver.findElement(By.xpath("//input[@type='text']"));
	}

	private WebElement btnSendInstructions() {
		return driver.findElement(By.xpath("//input[@type='submit']"));
	}

	private WebElement txtNewPassword() {
		return driver.findElement(By.xpath("//input[@id='newPassword']"));
	}

	private WebElement txtConfirmPassword() {
		return driver.findElement(By.xpath("//input[@id='confirmPassword']"));
	}

	private WebElement erroMessage() {
		return driver.findElement(By.xpath("//p[@class='message error']"));
	}

	private WebElement txtTocken() {
		return driver.findElement(By.xpath("//input[@id='resetToken']"));
	}

	private WebElement erroConfirmPassword() {
		return driver.findElement(By.xpath("//label[@for='confirmPassword' and @class='validation-error']"));
	}

	private WebElement erroTokenMsg() {
		return driver.findElement(By.xpath("//label[@class='validation-error']"));
	}

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public ForgotPasswordPage forgotPsw(String email) {
		txtEmail().sendKeys(email);
		btnSendInstructions().click();
		return this;
	}

	public ForgotPasswordPage changePswWithResetTocken(String password) {
		txtNewPassword().sendKeys(password);
		txtConfirmPassword().sendKeys(password);
		txtTocken().clear();
		btnSendInstructions().click();
		return this;
	}

	public ForgotPasswordPage changePswConfirmPswDif(String password, String confirmPassword) {
		txtNewPassword().sendKeys(password);
		txtConfirmPassword().sendKeys(confirmPassword);
		btnSendInstructions().click();
		return this;
	}

	public String getErrorMessage() {
		return erroMessage().getText();
	}

	public String getConfimPasswordMessage() {
		return erroConfirmPassword().getText();
	}

	public String getErrorTockenMsg() {
		return erroTokenMsg().getText();
	}
}
