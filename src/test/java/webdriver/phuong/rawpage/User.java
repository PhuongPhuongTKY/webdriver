package webdriver.phuong.rawpage;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
	private String email;
	private String password;
	private String confirmPassword;
	private String pid;
	
	public User() {
		this.email = generateEmail();
		this.password = generatePassword();
		this.confirmPassword = this.password;
		this.pid = generatePID();		
	}
	
	

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	public String getPid() {
		return pid;
	}



	public void setPid(String pid) {
		this.pid = pid;
	}



	public String generateEmail() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timeString = dateTime.format(formatter);
		String genString = timeString + "@gmail.com";
		return genString ;	
	}
	
	public String generatePassword() {
		
		final String chars = "ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890";
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int randomIndex = random.nextInt(chars.length());
			password.append(chars.charAt(randomIndex));	
		}
		return password.toString();			
	}
	
public String generatePID() {
		
		final String chars = "ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890";
		SecureRandom random = new SecureRandom();
		StringBuilder pid = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int randomIndex = random.nextInt(chars.length());
			pid.append(chars.charAt(randomIndex));	
		}
		return pid.toString();
	}
}
