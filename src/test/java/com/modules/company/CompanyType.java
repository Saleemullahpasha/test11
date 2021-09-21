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

public class CompanyType extends ExtentReportBaseClass {
	ChromeDriver driver;
	

	@Test
	public void AddCompanyType() {
		test = extent.createTest("AddCompanyTypeTest", "Testing the addition of Company Type");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company Type']")));
		driver.findElementByXPath("//span[text()='Company Type']").click();
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		driver.findElementByXPath("//span[text()='Add New']").click();
		// add data in the form
		driver.findElementById("mui-component-select-companyCategoryId").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			//selecting last element of the list
			list.get((list.size()-1)).click();
		}
		
		driver.findElementByName("companyTypeNameEnglish").sendKeys("My Test Type");
		driver.findElementByName("companyTypeNameUrdu").sendKeys("ایامپ اور سانگا");
		driver.findElementByName("companyTypeNameFrench").sendKeys("My Test Type");
		driver.findElementByName("companyTypeNameArabic").sendKeys("ايفامب و سانجا");
		driver.findElementByName("companyTypeNameBengali").sendKeys("My Test Type");
		driver.findElementByName("companyTypeNameTagalog").sendKeys("My Test Type");
		driver.findElementByName("companyTypeNamePashto").sendKeys("ایامپ اور سانگا");
		String imagePath = System.getProperty("user.dir") + "/src/test/resources/Test.png";
		driver.findElementByName("icon").sendKeys(imagePath);
		driver.findElementById("mui-component-select-visibilityStatus").click();
		list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
			//selecting last element of the list
			list.get(1).click();
		}
		
		//set order number
		int i=1;
		while(i<=1000) {
			String num=String.valueOf(i);
			driver.findElementByName("order").sendKeys(num);
		if(driver.findElementByXPath("//label[text()='Order']//following-sibling::p").isDisplayed()) {
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
		//submit the form

		driver.findElementByXPath("//span[text()='Submit']").click();
		WebDriverWait wait1=new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New]")));
		test.log(Status.INFO, "Record added successfully");
		driver.close();

	}
	
	@Test
	public void DelCompType() throws InterruptedException {
		test = extent.createTest("DeleteCompanyCategoryTest", "Testing the deletion of Category");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company Type']")));
		driver.findElementByXPath("//span[text()='Company Type']").click();
		
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
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[14]/div/button[2]")).click();
		driver.findElementByXPath("//span[text()='Yes']").click();
		 test.log(Status.INFO, "Clicked the delete button");
		 Thread.sleep(1500);

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
	
	//update company type
	@Test
	public void UpdateComType() {
		test = extent.createTest("UpdateCategoryTest", "Testing Update Feature");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver=LoginScenarios.driver;
		// click on company module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company']")));
		driver.findElementByXPath("//span[text()='Company']").click();
		// open type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Company Type']")));
		driver.findElementByXPath("//span[text()='Company Type']").click();

		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
		WebElement htmltable=driver.findElement(By.tagName("table"));
		//check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[14]/div/button[1]")).click();
			driver.findElementById("mui-component-select-companyCategoryId").click();
			List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				//selecting last element of the list
				list.get(1).click();
			}
		
			
			driver.findElementByName("companyTypeNameEnglish").sendKeys(Keys.chord(Keys.CONTROL, "a"),"Updated Type");
			driver.findElementByName("companyTypeNameUrdu").sendKeys(Keys.chord(Keys.CONTROL, "a"),"ایامپ اور سانگا");
			driver.findElementByName("companyTypeNameFrench").sendKeys(Keys.chord(Keys.CONTROL, "a"),"Updated Type");
			driver.findElementByName("companyTypeNameArabic").sendKeys(Keys.chord(Keys.CONTROL, "a"),"ایامپ اور سانگا");
			driver.findElementByName("companyTypeNameBengali").sendKeys(Keys.chord(Keys.CONTROL, "a"),"Updated Type");
			driver.findElementByName("companyTypeNameTagalog").sendKeys(Keys.chord(Keys.CONTROL, "a"),"Updated Type");
			driver.findElementByName("companyTypeNamePashto").sendKeys(Keys.chord(Keys.CONTROL, "a"),"Updated Type");
			String imagePath = System.getProperty("user.dir") + "/src/test/resources/Test.png";
			driver.findElementByName("icon").sendKeys(imagePath);
			list=null;
			driver.findElementById("mui-component-select-visibilityStatus").click();
			list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				//selecting last element of the list
				list.get(1).click();
			}
			//submit the form
			test.log(Status.INFO, "Form Submitted with updated data");
			//submit the form
			driver.findElementByXPath("//span[text()='Update']").click();
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
			test.log(Status.INFO, "Record Updated successfully");
		}
		else
		{
			 test.log(Status.INFO, "No data found for update");
			
		}
    driver.close();
			

					
				
		
	}
	

}
