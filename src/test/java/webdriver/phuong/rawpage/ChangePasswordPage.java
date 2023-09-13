package webdriver.phuong.rawpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends PageBase {
	private WebElement txtCurrenPasw() {
		return driver.findElement(By.xpath("//input[@title='Current password']"));
	}

	private WebElement txtNewPasw() {
		return driver.findElement(By.xpath("//input[@title='New password']"));
	}

	private WebElement txtConfirmPasw() {
		return driver.findElement(By.xpath("//input[@title='Confirm new password']"));
	}

	private WebElement btnChangePasw() {
		return driver.findElement(By.xpath("//input[@title='Change password']"));
	}

	private WebElement message() {
		return driver.findElement(By.xpath("//p[@class='message success']"));
	}

	private WebElement messageCofirmPasw() {
		return driver.findElement(By.xpath("//label[@class='validation-error']"));
	}

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
	}

	public ChangePasswordPage changePassword(String currentPassword, String newPassword, String confirmPassword) {
		txtCurrenPasw().sendKeys(currentPassword);
		txtNewPasw().sendKeys(newPassword);
		txtConfirmPasw().sendKeys(confirmPassword);
		btnChangePasw().click();
		return this;
	}

	public String getMessage() {
		return message().getText();
	}

	public String getMessageConfirmPasw() {
		return messageCofirmPasw().getText();
	}

}
