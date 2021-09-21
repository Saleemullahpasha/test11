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

public class EwpSubType extends ExtentReportBaseClass {
	ChromeDriver driver;
	
	@Test
	public void AddSubType() {
		// login to the portal
		test = extent.createTest("AddSubTypeTest", "Adding new sub type");
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open super type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Sub Type']")));
		driver.findElementByXPath("//span[text()='EWP User Sub Type']").click();
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.findElementByXPath("//main/div[1]/div[2]/button").click();
		// add data in the form
		driver.findElementById("mui-component-select-superTypeId").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			list.get(1).click();
		}
		
		driver.findElementByName("subTypeName").sendKeys("Test-Sub-type");
		driver.findElementByXPath("//span[text()='Submit']").click();
		WebDriverWait wait1=new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		test.log(Status.INFO, "Record added successfully");
		driver.close();
	}
	
	//update sub type
	@Test
	public void UpdateSubType() throws Throwable {
		// login to the portal
		test = extent.createTest("UpdateSubTypeTest", "Testing the update feature");
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open sub type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Sub Type']")));
		driver.findElementByXPath("//span[text()='EWP User Sub Type']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[4]/button[1]")).click();
			driver.findElementById("mui-component-select-superTypeId").click();
			List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				list.get(1).click();
			}
			
			driver.findElementByName("subTypeName").sendKeys(Keys.chord(Keys.CONTROL,"a"),"Updated-Sub-Type-name");
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
	
	//delete sub type
	@Test
	public void DelSubType() throws InterruptedException {
		
		test = extent.createTest("DeleteSubTypeTest", "Testing the deletion of SubType");
		// login to the portal
	
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open sub type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Sub Type']")));
		driver.findElementByXPath("//span[text()='EWP User Sub Type']").click();
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
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[2]/td[4]/button[2]")).click();
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
}
