package generics;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Wait {
	
	public static void visibilityOf(WebDriver driver,WebElement element) {
	WebDriverWait t = new WebDriverWait(driver, 10);
	t.until(ExpectedConditions.visibilityOf(element));  
	t.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void visibilityOf(AndroidDriver<AndroidElement> driver,MobileElement element) {
		
		WebDriverWait t = new WebDriverWait(driver, 10);
		t.until(ExpectedConditions.visibilityOf(element));  
		
	}
	
	public static String pollCurrentTime(WebDriver driver,String className, String timeStamp,String timeOut) {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("mm:ss");
		LocalTime Time =LocalTime.now().plusMinutes(Integer.valueOf(timeOut.split(":")[0])).plusSeconds(Integer.valueOf(timeOut.split(":")[1]));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		String p = "";
		while(true) {
			try {
			p=(String) js.executeScript(" return document.getElementsByClassName(\""+className+"\")[0].textContent");
			if(compare(p, timeStamp)) {
				js.executeScript("document.getElementsByClassName(\"playkit-icon playkit-icon-play\")[0].click();");
				break;	
			}
			}
			catch(Exception e){
			}
			if(!p.isEmpty())
			{
				if(Integer.valueOf(p.split(":")[0])>=Integer.valueOf(timeStamp.split(":")[0]) ) {
					if(Integer.valueOf(p.split(":")[1])>=Integer.valueOf(timeStamp.split(":")[1])||LocalTime.now()==Time ) {
						js.executeScript("document.getElementsByClassName(\"playkit-icon playkit-icon-play\")[0].click();");
						break;
					}
				}
			}
		}
		return p;
		}
	public static boolean androidDeviceTimeout(String timeOut) {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("mm:ss");
		LocalTime Time =LocalTime.now().plusMinutes(Integer.valueOf(timeOut.split(":")[0])).plusSeconds(Integer.valueOf(timeOut.split(":")[1]));
		if(LocalTime.now()==Time) {
			return false;
		}
		else {
			return true;
		}
	}
	public static boolean compare(String webTimeStamp, String androidTimeStamp) {
		int result=(Integer.valueOf(webTimeStamp.split(":")[0])*60+Integer.valueOf(webTimeStamp.split(":")[1]))-(Integer.valueOf(androidTimeStamp.split(":")[0])*60+Integer.valueOf(androidTimeStamp.split(":")[1]));
		if((result>=0 && result<=40)) {
			return true;
		}
		else {
			
			return false;
		}
	}
	
	
}
