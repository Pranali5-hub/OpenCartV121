package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//This is the parent of all Page Object Classes
public class BasePage {
	
	WebDriver driver;
	
	//Constructor
	
	public BasePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
