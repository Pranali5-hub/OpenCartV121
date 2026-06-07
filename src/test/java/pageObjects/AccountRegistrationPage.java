package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	//Locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//Action Methods
	
	public void setFirstName(String fname) {
		
		txtFirstname.sendKeys(fname);
	}
	
    public void setLastName(String lname) {
		
    	txtLastname.sendKeys(lname);
	}
	
    public void setEmail(String email) {
		
    	txtEmail.sendKeys(email);
	}
    
    public void setTelephone(String tel) {
		
    	txtTelephone.sendKeys(tel);
	}
    
    public void setPassword(String pwd) {
		
    	txtPassword.sendKeys(pwd);
	}
    
    public void setConfirmPassword(String pwd) {
		
    	txtconfPassword.sendKeys(pwd);
	}
    
    public void setPrivacyPolicy() {
		
		chkdPolicy.click();
	}
    
    public void clickContinue() {
		
    	//Solution 1
		btnContinue.click(); //if this method is not working then use other options
		
		//Solution 2
		
//		btnContinue.submit();
		
		//Solution 3
		
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//Solution 4
		
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();",btnContinue);
		
		//Solution 5
		
//		btnContinue.sendKeys(Keys.RETURN);
		
		//Solution 6
		
//		WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
    
    public String getConfirmationMsg() {
    	
    	try {
    		return(msgConfirmation.getText()); //based on the confirmation msg we will do validation inside test case
    	}
    	catch(Exception e) {
    		
    		return (e.getMessage());
    	}
    }
    
    

}
