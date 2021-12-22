package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLogin {
	@FindBy (xpath="//*[@type='email']")
	private WebElement username;
	
	@FindBy (xpath="//*[@type='password']")
	private WebElement password;
	
	@FindBy (xpath="//button[.='Next']")
	private WebElement next;
	
	public GmailLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getNext() {
		return next;
	}
}
