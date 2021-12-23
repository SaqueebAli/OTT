package VIDEO.OTT;

import generics.BaseClass;
import generics.Wait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import pageObjects.GmailLogin;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OTTVideoplaybackTest extends BaseClass {
	


	public static String testoutput() throws InterruptedException {

	
		GmailLogin gmailLogin=new GmailLogin(driver);
		gmailLogin.getUsername().sendKeys(<user-email>);
		gmailLogin.getNext().click();
		Wait.visibilityOf(driver,gmailLogin.getPassword());
		gmailLogin.getPassword().sendKeys(<email password>);
		gmailLogin.getNext().click();
		driver.navigate().to("https://www.zee5.com/");
		Thread.sleep(10000);
		driver.navigate().to("https://www.zee5.com/tvshows/details/tech-conversations-with-my-dad/0-6-3361/twitter/0-1-manual-53u26ansisl0");
		String playedTime=Wait.pollCurrentTime(driver, "playkit-time-display", "01:20","5:00");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("document.evaluate(\"//*[text()='Watchlist']\" , document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue.click();");
		System.out.println(playedTime);
		driver.close();
		return playedTime;
	}
	
	public static String androidTestOutput() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Rayhaan");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage","com.graymatrix.did" );
		cap.setCapability("appActivity","com.zee5.splash.SplashActivity" );
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
		AndroidDriver<AndroidElement> driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
//		driver.findElementByAndroidUIAutomator("attribute(\"value\")");
		Thread.sleep(5000);
//		Wait.visibilityOf(driver,(MobileElement)driver.findElementById("com.graymatrix.did:id/home_toolbar_more_icon"));
		driver.findElementById("com.graymatrix.did:id/home_toolbar_more_icon").click();

		driver.findElementByAndroidUIAutomator("text(\"Watchlist\")").click();
		Thread.sleep(5000);	
//		Wait.visibilityOf(driver,driver.findElementById("com.graymatrix.did:id/btnGoogle"));
		driver.findElementById("com.graymatrix.did:id/btnGoogle").click();
		Thread.sleep(5000);
//		Wait.visibilityOf(driver,driver.findElementById("com.google.android.gms:id/container"));
		driver.findElementById("com.google.android.gms:id/container").click();
		Thread.sleep(5000);
//		Wait.visibilityOf(driver,driver.findElementById("com.graymatrix.did:id/txt_reminder_item_title"));
		driver.findElementById("com.graymatrix.did:id/txt_reminder_item_title").click();
		Thread.sleep(5000);
//		Wait.visibilityOf(driver,driver.findElementByClassName("android.view.ViewGroup"));
		driver.findElementByClassName("android.view.ViewGroup").click();
		String txt="";
		while(Wait.androidDeviceTimeout("5:00")) {
			try {
				
				driver.findElementById("com.graymatrix.did:id/playerTouchToHideShowController").click();
				if(driver.findElementById("com.graymatrix.did:id/playerDurationCurrent").isDisplayed()) {
					txt=driver.findElementById("com.graymatrix.did:id/playerDurationCurrent").getText();
					break;
				}
			}
			catch(Exception e) {
				Thread.sleep(500);
			}
		}
		driver.closeApp();
		
		return txt;

//		String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Episode 3\").instance(0))";
	//
//		driver.findElementByAndroidUIAutomator(scrollElement).click();
	}
	
	
	@Test
	public void OTTVideoTesting() throws MalformedURLException, InterruptedException {
		String webTimeStamp=testoutput();
		System.out.println("webTimeStamp : "+webTimeStamp);
		String androidTimeStamp=androidTestOutput();
		System.out.println("androidTimeStamp : "+ androidTimeStamp);
		Assert.assertTrue(Wait.compare(webTimeStamp,androidTimeStamp));
	}
}
