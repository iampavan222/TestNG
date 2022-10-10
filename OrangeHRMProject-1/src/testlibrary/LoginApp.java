package testlibrary;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import utils.LaunchApp;

public class LoginApp extends LaunchApp 
{
	@BeforeTest
	public void loginpage(String uid,String pwd) 
	{
		driver.findElement(By.id("txtUsername")).sendKeys(uid);
		driver.findElement(By.id("txtPassword")).sendKeys(pwd);
		driver.findElement(By.id("btnLogin")).click();
	}
	
	public boolean isAdminornot()
	{
		if(driver.findElement(By.linkText("Admin")).isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
   }
	
	public boolean logout() {
		driver.findElement(By.partialLinkText("Welcome")).click();
		driver.findElement(By.linkText("Logout")).click();
		if(driver.findElement(By.id("btnLogin")).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean errmsgdisplay() {
		String errmsgs=driver.findElement(By.id("spanMessage")).getText();
		if(errmsgs.toLowerCase().contains("invalid")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEmpModuleDisplayed()
	{
     	try 
		{
			driver.findElement(By.linkText("Admin"));
			return false;
		} 
     	catch (Exception e) 
		{
			return true;
		}	
	}
	

}
