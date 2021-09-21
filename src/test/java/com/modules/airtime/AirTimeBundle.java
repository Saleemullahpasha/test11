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

public class AirTimeBundle extends ExtentReportBaseClass  {
	ChromeDriver driver;
	
	@Test
	public void AddBundle() throws Exception  {
		  test = extent.createTest("AddAirtimeBundleTest", "Testing the addition of Airtime Bundle");
		// login the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// open bundle module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		// open bundle page
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]")));
		driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]").click();
		Thread.sleep(500);
	
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
		driver.findElementByXPath("//span[text()='Add New']").click();
		// add data in the form
		driver.findElementById("mui-component-select-airtimeBundleCategoryId").click();
		driver.findElementByXPath("//*[@id=\"menu-airtimeBundleCategoryId\"]/div[3]/ul/li[2]").click();
		driver.findElementByName("airTimeBundleNameEnglish").sendKeys("My test bundle");
		driver.findElementByName("airTimeBundleNameUrdu").sendKeys("My test bundle");
		driver.findElementByName("airTimeBundleNameFrench").sendKeys("My test bundle");
		driver.findElementByName("airTimeBundleNameArabic").sendKeys("My test bundle");
		driver.findElementByName("airTimeBundleNameBengali").sendKeys("My test bundle");
		driver.findElementByName("airTimeBundleNameTagalog").sendKeys("My test bundle");
		//driver.findElementByName("bundleNameArabic").sendKeys("My test bundle");
	    driver.findElementById("mui-component-select-currency").click();
	    driver.findElementByXPath("//*[@id=\"menu-currency\"]/div[3]/ul/li[2]").click();
		driver.findElementByName("discountPercentage").sendKeys("10");
		driver.findElementByName("discountedPrice").sendKeys("80");
		driver.findElementByName("price").sendKeys("180");
		driver.findElementByName("subscriptionPeriod").sendKeys("7");
		driver.findElementById("mui-component-select-type").click();
		driver.findElementByXPath("//*[@id=\"menu-type\"]/div[3]/ul/li[2]").click();
		driver.findElementById("mui-component-select-visibilityStatus").click();
		driver.findElementByXPath("//*[@id=\"menu-visibilityStatus\"]/div[3]/ul/li[2]").click();
		//set order
		int i=1;
		while(i<=1000) {
			String num=String.valueOf(i);
			driver.findElementByName("order").click();
			driver.findElementByName("order").sendKeys(num);
		if(driver.findElementByXPath("//form/div/div[12]/div/p").isDisplayed()) {
			if(driver.findElementByXPath("//form/div/div[12]/div/p").getText().matches("Order is already assigned"))
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
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
		
		driver.close();
				
	}
	
	//delete airtime bundle
	@Test
	public void DeleteBundle() throws Throwable {
		  test = extent.createTest("DeleteBundleTest", "Testing Delete Bundle Usecase");
		// login the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// open bundle module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		// open bundle page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]")));
		driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]").click();
		Thread.sleep(500);
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//if records are present, check the number of record present using pagination field
			//adding this line to avoid error on cmd
			driver.findElementByXPath("//main/div[2]/div[2]/div/p[2]");
			String size=driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
			String oldsize=size.substring(size.indexOf('f')+1);
			oldsize=oldsize.trim();
			
			//store the current number of records
			int length=Integer.parseInt(oldsize);
			//perform deletion by clicking the delete button
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[18]/button[2]")).click();
		driver.findElementByXPath("//span[text()='Yes']").click();
		Thread.sleep(1500);
	/*	WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[14]/button[2]")));*/
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
		
	//update bundle
	@Test
	public void UpdateBundle() throws Throwable {
		  test = extent.createTest("UpdateBundleTest", "Testing Update Bundle Usecase");
		// login the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// open bundle module
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Airtime Bundle']")));
		driver.findElementByXPath("//span[text()='Airtime Bundle']").click();
		// open bundle page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]")));
		driver.findElementByXPath("(//span[text()='Airtime Bundle'])[2]").click();
		Thread.sleep(500);
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[18]/button[1]")).click();
			
			//update data
			driver.findElementById("mui-component-select-airtimeBundleCategoryId").click();
			driver.findElementByXPath("//*[@id=\"menu-airtimeBundleCategoryId\"]/div[3]/ul/li[2]").click();
			driver.findElementByName("airTimeBundleNameEnglish").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
			driver.findElementByName("airTimeBundleNameUrdu").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
			driver.findElementByName("airTimeBundleNameFrench").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
			driver.findElementByName("airTimeBundleNameArabic").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
			driver.findElementByName("airTimeBundleNameBengali").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
			driver.findElementByName("airTimeBundleNameTagalog").sendKeys(Keys.chord(Keys.CONTROL, "a"),"My updated bundle");
		    driver.findElementById("mui-component-select-currency").click();
		    driver.findElementByXPath("//*[@id=\"menu-currency\"]/div[3]/ul/li[2]").click();
			driver.findElementByName("discountPercentage").sendKeys(Keys.chord(Keys.CONTROL, "a"),"10");
			driver.findElementByName("discountedPrice").sendKeys(Keys.chord(Keys.CONTROL, "a"),"50");
			driver.findElementByName("price").sendKeys(Keys.chord(Keys.CONTROL, "a"),"150");
			driver.findElementByName("subscriptionPeriod").sendKeys(Keys.chord(Keys.CONTROL, "a"),"10");
			driver.findElementById("mui-component-select-type").click();
			driver.findElementByXPath("//*[@id=\"menu-type\"]/div[3]/ul/li[2]").click();
			driver.findElementById("mui-component-select-visibilityStatus").click();
			driver.findElementByXPath("//*[@id=\"menu-visibilityStatus\"]/div[3]/ul/li[2]").click();
			//submit the form
			driver.findElementByXPath("//span[text()='Update']").click();
			wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
			test.log(Status.INFO, "Record Updated successfully");
		}
		else
		{
			//Reporter.log("No data exists");
			 test.log(Status.INFO, "No data found for update");
		}
		
		driver.close();
	}

}
