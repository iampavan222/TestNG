package testlibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import utils.LaunchApp;

 public class EmployeeRegistration extends LaunchApp {

	public boolean empreg(String fname, String lname) {
		
		driver.findElement(By.linkText("PIM")).click();
		driver.findElement(By.linkText("Add Employee")).click();
		
		driver.findElement(By.id("firstName")).sendKeys(fname);
		driver.findElement(By.id("lastName")).sendKeys(lname);
		String id=driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.linkText("PIM")).click();
		driver.findElement(By.id("empsearch_id")).sendKeys(id);
		driver.findElement(By.id("searchBtn")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.id("resultTable")));
		
		WebElement table=driver.findElement(By.id("resultTable"));
		List<WebElement> rows,cols;
		rows=table.findElements(By.tagName("tr"));
		boolean display = false;
		for(int i=1;i<=rows.size();i++) {
			cols=rows.get(i).findElements(By.tagName("td"));
			if(cols.get(1).getText().equals(id))
			{
				display=true;
				break;
			}
		}
		return display;
	}
	
}
