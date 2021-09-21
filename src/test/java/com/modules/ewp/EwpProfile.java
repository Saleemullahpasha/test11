package com.modules.ewp;

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

public class EwpProfile extends ExtentReportBaseClass {
ChromeDriver driver;
	
	@Test
	public void AddProfile() {
		test = extent.createTest("AddProfileTest", "Testing the addition of Profile");
		// login to the portal
	
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open profile page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profile']")));
		driver.findElementByXPath("//span[text()='EWP Profile']").click();
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.findElementByXPath("//span[text()='Add New']").click();
		// add data in the form
		driver.findElementById("mui-component-select-subTypeId").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			list.get(1).click();
		}
		driver.findElementByName("profileName").sendKeys("Test Merchant");
		driver.findElementByXPath("//span[text()='Submit']").click();
		WebDriverWait wait1=new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		test.log(Status.INFO, "Record added successfully");
		driver.close();
	}
	
	//delete profile
	@Test
	public void DelProfile() throws Exception {
		test = extent.createTest("DeleteCompanyCategoryTest", "Testing the deletion of Category");
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open profile page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profile']")));
		driver.findElementByXPath("//span[text()='EWP Profile']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//if records are present, check the number of record present using pagination field
			String size = driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
			String oldsize=size.substring(size.indexOf('f')+1);
			oldsize=oldsize.trim();
			//store the current number of records
			int length=Integer.parseInt(oldsize);
			//perform deletion by clicking the delete button
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[3]/button[2]")).click();
			driver.findElementByXPath("//span[text()='Yes']").click();
			test.log(Status.INFO, "Clicked the delete button");
			Thread.sleep(1500);
			//get the new number of records after deletion using pagination field
			size = driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
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
	
	//update profile
	@Test
	public void UpdateProfile() {
		test = extent.createTest("UpdateCategoryTest", "Testing Update Feature");
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open profile page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profile']")));
		driver.findElementByXPath("//span[text()='EWP Profile']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//perform update by clicking the edit button
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[3]/button[1]")).click();
	    //update data
		driver.findElementById("mui-component-select-subTypeId").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			//selecting last element of the list
			list.get(1).click();
		}
		
		driver.findElementByName("profileName").sendKeys(Keys.chord(Keys.CONTROL,"a"),"Updated Test Merchant");
		test.log(Status.INFO, "Form Submitted with updated data");
		//submit the form
		driver.findElementByXPath("//span[text()='Update']").click();
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		test.log(Status.INFO, "Record Updated successfully");
	}
	else
	{
		 test.log(Status.INFO, "No data found for update");
		
	}
    driver.close();
	}
	
}
