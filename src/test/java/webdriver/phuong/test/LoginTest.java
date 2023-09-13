package webdriver.phuong.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import webdriver.phuong.datas.SeatType;
import webdriver.phuong.datas.Station;
import webdriver.phuong.datas.TicketInformation;
import webdriver.phuong.rawpage.BookTicketPage;
import webdriver.phuong.rawpage.ChangePasswordPage;
import webdriver.phuong.rawpage.ContactPage;
import webdriver.phuong.rawpage.ForgotPasswordPage;
import webdriver.phuong.rawpage.HomePage;
import webdriver.phuong.rawpage.LoginPage;
import webdriver.phuong.rawpage.MyTicketPage;
import webdriver.phuong.rawpage.RegisterPage;
import webdriver.phuong.rawpage.TicketPricePage;
import webdriver.phuong.datas.User;

public class LoginTest extends TestBase {

	@Test
	public void TC001() {
		//User can log with valid username and password
		String username = "105128423@gmail.com";
		String passWord = "11111111";
		HomePage home = homePage.gotoLoginPage().loginSuccess(username, passWord);
		String expectWelcome = "Welcome " + username;
		assertEquals(home.getMsgHomepage(), expectWelcome, "Welcome user message is not display");
	}

	@Test
	public void TC002() {
		//User can't login with blank "Username" textbox
		String username = "";
		String password = "11111111";
		LoginPage loginPage = homePage.gotoLoginPage().loginFail(username, password);
		String expectMessage = "There was a problem with your login and/or errors exist in your form.";
		assertEquals(loginPage.getErrorMessages(), expectMessage, "Error message is displayed as expected.");
	}

	@Test
	public void TC003() {
		//User cannot log into Railway with invalid password 
		String username = "105128423@gmail.com";
		String password = "1111111111";
		LoginPage loginPage = homePage.gotoLoginPage().loginFail(username, password);
		String expectMessage = "There was a problem with your login and/or errors exist in your form.";
		assertEquals(loginPage.getErrorMessages(), expectMessage, "Error message is displayed as expected.");
	}

	@Test
	public void TC005() {
		//System shows message when user enters wrong password several times
		String username = "105128423@gmail.com";
		String password = "1111111111";
		LoginPage loginPage = null;
		for (int i = 0; i < 4; i++) {
			loginPage = homePage.gotoLoginPage().loginFail(username, password);
		}
		String expectMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		assertEquals(loginPage.getErrorMessages(), expectMessage, "Error message is displayed as expected.");
	}

	@Test
	public void TC006() {
		//User is redirected to Home page after logging out
		String username = "105128423@gmail.com";
		String passWord = "11111111";
		HomePage home = homePage.gotoLoginPage().loginSuccess(username, passWord);
		ContactPage contact = home.gotoContactPage();
		HomePage logout = contact.gotoLogoutPage();
		assertEquals(logout.isHomePageDisplay(), true, "HomePage is not display");
	}

	@Test
	public void TC004() {
		String username = "105128423@gmail.com";
		String password = "11111111";
		LoginPage loginFromBookTicket = homePage.gotoBookTicketWithoutLogin();
		assertEquals(loginFromBookTicket.isLoginDisplayFromBookTicket(), true, "User is not directed to Login page");
		BookTicketPage bookTicketPage = loginFromBookTicket.loginFromBookTicket(username, password);
		assertEquals(bookTicketPage.isBookTicketDisplay(), true, "Book ticket is not display");
	}

	public RegisterPage createAccWithoutActivate(User user) {
		return homePage.gotoRegisterPage().registerAc(user);
	}

	public User createAccAndActivate(User user) throws Exception {
		homePage.gotoRegisterPage().registerAc(user);
		activeAcc();
		return user;
	}

	@Test
	public void TC007() throws Exception {
		User user = new User();
		RegisterPage registerPage = createAccWithoutActivate(user);
		String expectMsgWelcome = "Thank you for registering your account";
		assertEquals(registerPage.getWelcomeMsg(), expectMsgWelcome, "Actual message is not as expected");
	}

	@Test
	public void TC008() {
		//User can't login with an account hasn't been activated
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccWithoutActivate(user);

		LoginPage loginPage = homePage.gotoLoginPage().loginFail(user.getEmail(), user.getPassword());
		String expectMsg = "Invalid username or password. Please try again.";
		assertEquals(loginPage.getErrorMessages(), expectMsg, "Error message is not expected");
	}

	@Test
	public void TC009() throws Exception {
		//User can't change password when "New Password" and "Confirm Password" are different.
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);

		HomePage homePage01 = homePage.gotoLoginPage().loginSuccess(user.getEmail(), user.getPassword());
		ChangePasswordPage changePassword = homePage01.gotoChangePasswordPage().changePassword(user.getPassword(),
				"a123:\"/{}!@$\\", "b456:\"/{}!@$\\");
		String expectMsg = "The password confirmation does not match the new password.";
		assertEquals(changePassword.getMessageConfirmPasw(), expectMsg, "Error message is not expected");
	}

	@Test
	public void TC0010() throws Exception {
		//User can't create account with an already in-use email
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);

		RegisterPage registerCreatedAccount = homePage.gotoRegisterPage().registerAc(user);
		String expectMsg = "This email address is already in use.";
		assertEquals(registerCreatedAccount.getErrorMessage(), expectMsg, "Error message is not expected");
	}

	@Test
	public void TC0011() {
		//User can't create account while password and PID fields are empty
		User user = new User();
		RegisterPage registerAcOtherEmty = homePage.gotoRegisterPage().registerAcFail(user.getEmail(), "", "", "");
		String expectMsg = "There're errors in the form. Please correct the errors and try again.";
		String expectMsgPsw = "Invalid password length";
		String expectMsgPid = "Invalid ID length";
		assertEquals(registerAcOtherEmty.getErrorMessage(), expectMsg, "Error message is not expected");
		assertEquals(registerAcOtherEmty.getErrorMsgPasw(), expectMsgPsw, "Error message is not expected");
		assertEquals(registerAcOtherEmty.getErrorMsgPid(), expectMsgPid, "Error message is not expected");
	}

	@Test
	public void TC0012() throws Exception {
		//Errors display when password reset token is blank
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);
		ForgotPasswordPage forgotPasword = homePage.gotoLoginPage().gotoForgotPasswordPage().forgotPsw(user.getEmail());
		resetPassword();
		ForgotPasswordPage changePassWord = forgotPasword.changePswWithResetTocken(user.generatePassword());
		String expectMsg = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
		String expectTokenMsg = "The password reset token is invalid.";
		assertEquals(changePassWord.getErrorMessage(), expectMsg, "Error message is not expected");
		assertEquals(changePassWord.getErrorTockenMsg(), expectTokenMsg, "Error message is not expected");
	}

	@Test
	public void TC0013() throws Exception {
		//Errors display if password and confirm password don't match when resetting password
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);
		ForgotPasswordPage forgotPasword = homePage.gotoLoginPage().gotoForgotPasswordPage().forgotPsw(user.getEmail());
		resetPassword();
		ForgotPasswordPage changePassWord = forgotPasword.changePswConfirmPswDif(user.generatePassword(),
				user.generatePassword());
		String expectConfirmPswMsg = "The password confirmation did not match the new password.";
		String ecpectErrorMsg = "Could not reset password. Please correct the errors and try again.";
		assertEquals(changePassWord.getConfimPasswordMessage(), expectConfirmPswMsg, "Error message is not expected");
		assertEquals(changePassWord.getErrorMessage(), ecpectErrorMsg, "Error message is not expected");
	}

	@Test
	public void TC0014() throws Exception {
		//User can book many tickets at a time
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);
		HomePage home = homePage.gotoLoginPage().loginSuccess(user.getEmail(), user.getPassword());
		TicketInformation ticKetInformation = new TicketInformation(Station.NhaTrang.getValue(), Station.Hue.getValue(),
				SeatType.SeatAirConditioner.getValue(), 2);
		BookTicketPage bookTicket = home.gotoBookTicketPage().bookTicketSuccess(ticKetInformation);
		String expectWelcomeMsg = "Ticket booked successfully!";
		assertEquals(bookTicket.getMsgBookTicketSuccess(), expectWelcomeMsg, "Welcome message is not expected");
		assertEquals(bookTicket.isBookTicketSuccess(ticKetInformation), true, "Bool Ticket is not successful");
	}

	@Test
	public void TC0015() throws Exception {
		//"Ticket price" page displays with ticket details after clicking on "check price" link in "Train timetable" page
		// Pre-Condition: RegisterAccount
		User user = new User();
		createAccAndActivate(user);
		
		HomePage home = homePage.gotoLoginPage().loginSuccess(user.getEmail(),user.getPassword());
		TicketPricePage checkPrice = home.gotoTimPage().checkPrice(Station.DaNang.getValue(),Station.SaiGon.getValue());	
		String expectMSg = "Ticket price from Đà Nẵng to Sài Gòn";
		assertEquals(checkPrice.getTitlePriceTable(), expectMSg, "Welcome message is not expected");
		assertEquals(checkPrice.isPriceCorrect(), true, "Price of seat types are not expected");
	}

	@Test
	public void TC0016() throws Exception {
		//User can cancel a ticket
		User user = new User();
		createAccAndActivate(user);
		HomePage home = homePage.gotoLoginPage().loginSuccess(user.getEmail(), user.getPassword());
		TicketInformation ticKetInformation = new TicketInformation(Station.DaNang.getValue(), Station.Hue.getValue(),SeatType.HardSeat.getValue(),1);
		BookTicketPage bookTicket = home.gotoBookTicketPage().bookTicketSuccess(ticKetInformation);
		MyTicketPage cancelTicket = bookTicket.gotoMyTicketPage().cancelTicket(ticKetInformation);
		assertEquals(cancelTicket.isCancelTicketSuccess(ticKetInformation), true,"Can not cancel ticket");

	}

}
