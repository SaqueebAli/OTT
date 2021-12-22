package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Zee5 {
	@FindBy (xpath="//*[@class='loginBtn noSelect ']")
	private WebElement LoginBtn;
	
	public Zee5(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement LoginButton() {
		return LoginBtn;
	}
}
