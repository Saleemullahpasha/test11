package com.modules.company;



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

public class Company extends ExtentReportBaseClass{
	ChromeDriver driver;
	
	@Test
	public void AddCompany()  {
		test = extent.createTest("AddCompanyTest", "Testing the addition of Company");
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open company page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Company'])[2]")));
		driver.findElementByXPath("(//span[text()='Company'])[2]").click();
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.findElementByXPath("//span[text()='Add New']").click();
		
		// add data in the form
		
		driver.findElementByName("companyNameArabic").sendKeys("My Test Company");
		driver.findElementByName("companyNameBengali").sendKeys("My Test Company");
		driver.findElementByName("companyNameEnglish").sendKeys("My Test Company");
		driver.findElementByName("companyNameFrench").sendKeys("My Test Company");
		driver.findElementByName("companyNameTagalog").sendKeys("My Test Company");
		driver.findElementByName("companyNameUrdu").sendKeys("My Test Company");
		driver.findElementByName("serviceProvider").sendKeys("My Test Company");
		driver.findElementByName("usecaseId").sendKeys("12");
		driver.findElementByName("friType").sendKeys("My Test Company");
		driver.findElementById("mui-component-select-partialPayment").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			list.get(1).click();
		}
		
		driver.findElementById("mui-component-select-companyTypeId").click();
		list=null;
		list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			list.get(1).click();
		}
		String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
		driver.findElementByName("companyIcon").sendKeys(imagePath);
		
		//set the order number
		int i=1;
		while(i<=1000) {
			String num=String.valueOf(i);
			driver.findElementByName("order").sendKeys(num);
			
		if(driver.findElements( By.xpath("//label[text()='Order']//following-sibling::p") ).size() != 0) {
			
			if(driver.findElementByXPath("//label[text()='Order']//following-sibling::p").getText().matches("Order is already assigned"))
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
		driver.findElementById("mui-component-select-visibilityStatus").click();
		List<WebElement> list2=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list2.size()>0) {
			list2.get(1).click();
		}
			// submit the form
		driver.findElementByXPath("//span[text()='Submit']").click();
		WebDriverWait wait1=new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		test.log(Status.INFO, "Record added successfully");
		driver.close();
	}
	//Delete company
	@Test
	public void DelCompany() throws InterruptedException {
		test = extent.createTest("DeleteCompanyTest", "Testing the deletion of Company");
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Company'])[2]")));
		driver.findElementByXPath("(//span[text()='Company'])[2]").click();
		driver.findElementByXPath("//main/div[2]");
		// locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
		WebElement htmltable = driver.findElement(By.tagName("table"));
		// check if any record appear in the table
		List<WebElement> tableRows = htmltable.findElements(By.xpath("//table/tbody/tr"));
		if (tableRows.size() > 0) {
			// if records are present, check the number of record present using pagination
			// field
			String size = driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
			String oldsize = size.substring(size.indexOf('f') + 1);
			oldsize = oldsize.trim();

			// store the current number of records
			int length = Integer.parseInt(oldsize);
			// perform deletion by clicking the delete button
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[12]/button[2]")).click();
			driver.findElementByXPath("//span[text()='Yes']").click();
			test.log(Status.INFO, "Clicked the delete button");
			Thread.sleep(1500);
			// get the new number of records after deletion using pagination field
			size = driver.findElementByXPath("(//p[@class='MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit'])[2]").getText();
			oldsize = size.substring(size.indexOf('f') + 1);
			oldsize = oldsize.trim();
			// this line will check if record is deleted from the list
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
	//update company
	@Test
	public void UpdateCompany() throws Exception {
		
		test = extent.createTest("UpdateCompanyTest", "Testing the update feature");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Company'])[2]")));
		driver.findElementByXPath("(//span[text()='Company'])[2]").click();
		driver.findElementByXPath("//main/div[2]");
		// locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
		WebElement htmltable = driver.findElement(By.tagName("table"));
		// check if any record appear in the table
		List<WebElement> tableRows = htmltable.findElements(By.xpath("//table/tbody/tr"));
		if (tableRows.size() > 0) {
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[12]/button[1]")).click();

			// update data in the form

			driver.findElementByName("companyNameArabic").sendKeys(Keys.chord(Keys.CONTROL, "a"), "ایامپ");
			driver.findElementByName("companyNameBengali").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementByName("companyNameEnglish").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementByName("companyNameFrench").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementByName("companyNameTagalog").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementByName("companyNameUrdu").sendKeys(Keys.chord(Keys.CONTROL, "a"), " ایامپ اور سانگا");
			driver.findElementByName("serviceProvider").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementByName("usecaseId").sendKeys(Keys.chord(Keys.CONTROL, "a"), "12");
			driver.findElementByName("friType").sendKeys(Keys.chord(Keys.CONTROL, "a"), "My updated Company");
			driver.findElementById("mui-component-select-partialPayment").click();
			driver.findElementByXPath("//*[@id='menu-partialPayment']/div[3]/ul/li[2]").click();
			driver.findElementById("mui-component-select-companyTypeId").click();
			List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				//selecting last element of the list
				list.get(1).click();
			}
			driver.findElementById("mui-component-select-visibilityStatus").click();
			driver.findElementByXPath("//*[@id='menu-visibilityStatus']/div[3]/ul/li[2]").click();
			String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
			driver.findElementByName("companyIcon").sendKeys(imagePath);
			// submit the form
			driver.findElementByXPath("//span[text()='Update']").click();
			Thread.sleep(2000);
		//	WebDriverWait wait2 = new WebDriverWait(driver, 30);
		//	wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
			
		}
		else
		{
			 test.log(Status.INFO, "No data found for update");
			
		}
        driver.close();
	}
}