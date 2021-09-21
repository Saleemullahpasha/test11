package com.modules;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.extent.report.ExtentReportBaseClass;
import com.login.LoginScenarios;

public class BankTest extends ExtentReportBaseClass{
	ChromeDriver driver;
	
	@Test
	
	public void AddBankTest() {
		 test = extent.createTest("AddBankTest", "Testing Add Bank Usecase");
		//login to the portal
		
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		//open banks module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Banks']")));
		driver.findElementByXPath("//span[text()='Banks']").click();
		//click on Add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
		driver.findElementByXPath("//main/div[1]/div[2]/button").click();
		//add bank data
		driver.findElementByName("bankNameEnglish").sendKeys("Habib Bank Limited");
		driver.findElementByName("bankNameUrdu").sendKeys("حبیب بینک لمیٹڈ");
		driver.findElementByName("shortName").sendKeys("HBL");
		driver.findElementByName("accountNumberFormat").sendKeys("12345-67890123-123");
		driver.findElementByName("sp").sendKeys("Pakistan State Bank");
		//submit the form
		driver.findElementByXPath("//span[text()='Submit']").click();
		
		driver.close();
	}
	
	@Test
	
	public void DeleteBank() throws Exception {
		 test = extent.createTest("DeleteBankTest", "Testing Delete Bank Usecase");
		//login to the portal
		
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		//open banks module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Banks']")));
		driver.findElementByXPath("//span[text()='Banks']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//if records are present, check the number of record present using pagination field
			String size=driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
			String oldsize=size.substring(size.indexOf('f')+1);
			oldsize=oldsize.trim();
		
			//store the current number of records
			int length=Integer.parseInt(oldsize);
			//perform deletion by clicking the delete button
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[11]/button[2]")).click();
		driver.findElementByXPath("//span[text()='Yes']").click();
		Thread.sleep(1500);
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[11]/button[2]")));
		//get the new number of records after deletion using pagination field
		size=driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
				oldsize=size.substring(size.indexOf('f')+1);
				oldsize=oldsize.trim();
				//this line will check if record is deleted from the list
				if(length>Integer.parseInt(oldsize)) 
				    test.log(Status.PASS, "Record deleted successfully");
					
					else
					test.log(Status.FAIL, "Record could not be deleted. Deletion failed");	
					}
			else
			{
				 test.log(Status.INFO, "No Data Exists");
				
			}
		driver.close();
		
	}
	
	@Test
	public void UpdateBankTest() throws Throwable {
		test = extent.createTest("UpdateBankTest", "Testing Update Bank Usecase");

		//login to the portal

		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		//open banks module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Banks']")));
		driver.findElementByXPath("//span[text()='Banks']").click();
		driver.findElementByXPath("//*[@id=\"root\"]/div[2]/main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
		WebElement htmltable=driver.findElement(By.tagName("table"));
		//check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
		//click on edit button of first row
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[11]/button[1]")).click();
			
			//update bank data
			driver.findElementByName("bankNameEnglish").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Updated Bank");
			driver.findElementByName("bankNameUrdu").sendKeys(Keys.chord(Keys.CONTROL, "a"),"حبیب بینک لمیٹڈ");
			driver.findElementByName("shortName").sendKeys(Keys.chord(Keys.CONTROL, "a"),"ABC");
			driver.findElementByName("accountNumberFormat").sendKeys(Keys.chord(Keys.CONTROL, "a"),"14 digits (AAA BBBB DDDD)");
			driver.findElementByName("sp").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Updated SP");
			//submit the form
			driver.findElementByXPath("//span[text()='Update']").click();
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[11]/button[1]")));

	}
		else
		{
			 test.log(Status.INFO, "No data found for update");
			
		}
        driver.close();
		
	}
	
	

}
