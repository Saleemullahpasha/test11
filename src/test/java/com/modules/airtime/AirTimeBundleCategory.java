package com.modules.airtime;


import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import com.extent.report.ExtentReportBaseClass;
import com.login.LoginScenarios;


public class AirTimeBundleCategory extends ExtentReportBaseClass  {
	ChromeDriver driver;
	
	@Test
	
	public void AddBundleCat() {
		test = extent.createTest("AddBundleCategoryTest", "Testing the addition of Bundle Category");
		//login the portal
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		//open bundle module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		//open category page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Bundle Category']")));
		driver.findElementByXPath("//span[text()='Bundle Category']").click();
		//click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.findElementByXPath("//span[text()='Add New']").click();
		//add data in the form
	
		driver.findElementByName("categoryNameArabic").sendKeys("arabic");
		driver.findElementByName("categoryNameFrench").sendKeys("french");
		driver.findElementByName("categoryNameBengali").sendKeys("bengali");
		driver.findElementByName("categoryNameTagalog").sendKeys("tagalog");
		driver.findElementByName("categoryNameEnglish").sendKeys("english");
		driver.findElementByName("categoryNameUrdu").sendKeys("urdu");
		//set the order number
		int i=1;
		while(i<=1000) {
			String num=String.valueOf(i);
			driver.findElementByName("order").sendKeys(num);
		if(driver.findElementByXPath("//form/div/div[8]/div[1]/p").isDisplayed()) {
			if(driver.findElementByXPath("//form/div/div[8]/div[1]/p").getText().matches("Order is already assigned"))
			{ 
			i++;
			num=String.valueOf(i);
			Actions action=new Actions(driver);
			WebElement orderfield=driver.findElementByName("order");
			action.keyDown(orderfield, Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).build().perform();
			driver.findElementByName("order").sendKeys(num);
			}
			else
				break;
		}
		else
			break;
			
		}//end of while loop
		//submit the form
		driver.findElementByXPath("//span[text()='Submit']").click();
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.close();
	}
	@Test
	public void DeleteAirCategory() {
		test = extent.createTest("DeleteCategoryTest", "Testing the deletion of Bundle Category");
		//login to portal
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Bundle Category']")));
		driver.findElementByXPath("//span[text()='Bundle Category']").click();
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
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[12]/button[2]")).click();
		driver.findElementByXPath("//span[text()='Yes']").click();
		 WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[12]/button[2]")));
		//get the new number of records after deletion using pagination field
		size=driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
		oldsize=size.substring(size.indexOf('f')+1);
		oldsize=oldsize.trim();
		//this line will check if record is deleted from the list
		if(length>Integer.parseInt(oldsize))
			test.log(Status.INFO, "Record deleted successfully");

		else
			test.log(Status.INFO, "Record could not be deleted. Deletion failed");	

		}
		else
		{
			test.log(Status.INFO, "No Data Exists");

		}
		driver.close();
	}
	
	//update category
	@Test
	public void UpdateAirtimeCat() {
		 test = extent.createTest("UpdateCategoryTest", "Testing Update Feature");	
		//login the portal
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		//open bundle module
		WebDriverWait wait = new WebDriverWait(driver, 40);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		//open category page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Bundle Category']")));
		driver.findElementByXPath("//span[text()='Bundle Category']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//click on update button of first row
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[12]/button[1]")).click();
			//update the text
			driver.findElementByName("categoryNameEnglish").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			driver.findElementByName("categoryNameFrench").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			driver.findElementByName("categoryNameTagalog").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			driver.findElementByName("categoryNameBengali").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			driver.findElementByName("categoryNameArabic").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			driver.findElementByName("categoryNameUrdu").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My Test Category updated");
			
			//submit the form
			driver.findElementByXPath("//span[text()='Update']").click();
			
			test.log(Status.INFO, "Record Updated successfully");
		}
		else
		{
			 test.log(Status.INFO, "No data found for update");
			
		}
	  driver.close();
		
		}
		
	
	

	
	
	
	
}
