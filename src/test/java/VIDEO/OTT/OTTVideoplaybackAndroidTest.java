package VIDEO.OTT;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import generics.Wait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class OTTVideoplaybackAndroidTest {


	public String androidTestOutput() throws MalformedURLException, InterruptedException {
	
	DesiredCapabilities cap= new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Rayhaan");
	cap.setCapability("platformName", "Android");
	cap.setCapability("appPackage","com.graymatrix.did" );
	cap.setCapability("appActivity","com.zee5.splash.SplashActivity" );
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
	cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
	AndroidDriver<AndroidElement> driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
//	driver.findElementByAndroidUIAutomator("attribute(\"value\")");
	Thread.sleep(5000);
//	Wait.visibilityOf(driver,(MobileElement)driver.findElementById("com.graymatrix.did:id/home_toolbar_more_icon"));
	driver.findElementById("com.graymatrix.did:id/home_toolbar_more_icon").click();

	driver.findElementByAndroidUIAutomator("text(\"Watchlist\")").click();
	Thread.sleep(5000);	
//	Wait.visibilityOf(driver,driver.findElementById("com.graymatrix.did:id/btnGoogle"));
	driver.findElementById("com.graymatrix.did:id/btnGoogle").click();
	Thread.sleep(5000);
//	Wait.visibilityOf(driver,driver.findElementById("com.google.android.gms:id/container"));
	driver.findElementById("com.google.android.gms:id/container").click();
	Thread.sleep(5000);
//	Wait.visibilityOf(driver,driver.findElementById("com.graymatrix.did:id/txt_reminder_item_title"));
	driver.findElementById("com.graymatrix.did:id/txt_reminder_item_title").click();
	Thread.sleep(5000);
//	Wait.visibilityOf(driver,driver.findElementByClassName("android.view.ViewGroup"));
	driver.findElementByClassName("android.view.ViewGroup").click();
	String txt;
	while(true) {
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
	return txt;

//	String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Episode 3\").instance(0))";
//
//	driver.findElementByAndroidUIAutomator(scrollElement).click();
}
}