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

public class AppPromotion extends ExtentReportBaseClass{
	 ChromeDriver driver;
	 
		@Test
		public void WholeBase() throws Exception {
			  test = extent.createTest("AddWholeBase", "Testing the addition Whole Base Promotion");
			// login to the portal

			LoginScenarios.LandConfigTest();
			driver = LoginScenarios.driver;
			// click on App promotion module
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='App Promotions']")));
			driver.findElementByXPath("//span[text()='App Promotions']").click();
			//click add button
			driver.findElementByXPath("//span[text()='Add New']").click();
			//add data
			driver.findElementById("mui-component-select-usecase").click();
			List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				
				list.get(1).click();
			}
			driver.findElementByName("description").sendKeys("Test decription");
			driver.findElementByName("redirectTo").sendKeys("https://www.google.com");
			driver.findElementByXPath("//form/div/div[4]/div");
			driver.findElementByName("expiry").click();
			
			//driver.findElementByCssSelector("button[class='MuiButtonBase-root MuiIconButton-root']").click();
			driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root']").click();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']")));
			driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']").click();
			WebDriverWait wait2=new WebDriverWait(driver,20);
			wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']")));
			driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']").click();
			driver.findElementByName("category").sendKeys("Test Category");
			list=null;
			driver.findElementById("mui-component-select-type").click();
			list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			Thread.sleep(500);
			if (list.size()>0) {
				for(int i=0; i<list.size(); i++) {
				if(list.get(i).getText().equalsIgnoreCase("Whole Base")) {
						list.get(i).click();
				break;
				}
				}
			}
			String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
			driver.findElementByName("imageUrl").sendKeys(imagePath);
			String videoPath = System.getProperty("user.dir") + "/src/test/resources/test.mp4";
			driver.findElementByName("videoUrl").sendKeys(videoPath);
			
			driver.findElementByXPath("//span[text()='Submit']").click();
			/*WebDriverWait wait1=new WebDriverWait(driver,30);
			wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));*/
			Thread.sleep(2000);
			test.log(Status.INFO, "Record added successfully");
			driver.close();
		}
		
		//targeted msisdns
		@Test
		public void Targeted() throws Exception {
		test = extent.createTest("AddTargeted", "Testing the addition Targeted Promotion");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on App promotion module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='App Promotions']")));
		driver.findElementByXPath("//span[text()='App Promotions']").click();
		//click add button
		driver.findElementByXPath("//span[text()='Add New']").click();
		driver.findElementById("mui-component-select-usecase").click();
		List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		if (list.size()>0) {
		
			list.get(1).click();
		}
		driver.findElementByName("description").sendKeys("Test decription");
		driver.findElementByName("redirectTo").sendKeys("https://www.google.com");
		driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root']").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']")));
		driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']").click();
		WebDriverWait wait2=new WebDriverWait(driver,20);
		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']")));
		driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']").click();
		driver.findElementByName("category").sendKeys("Test Category");
		list=null;
		driver.findElementById("mui-component-select-type").click();
		list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
		Thread.sleep(500);
		if (list.size()>0) {
			for(int i=0; i<list.size(); i++) {
			if(list.get(i).getText().equalsIgnoreCase("Targeted Msisdns")) {
					list.get(i).click();
			break;
			}
			}
		}
		String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
		driver.findElementByName("imageUrl").sendKeys(imagePath);
		String videoPath = System.getProperty("user.dir") + "/src/test/resources/test.mp4";
		driver.findElementByName("videoUrl").sendKeys(videoPath);
		String csvPath = System.getProperty("user.dir") + "/src/test/resources/SampleCSVFile.csv";
		driver.findElementByName("csvFile").sendKeys(csvPath);
		driver.findElementByXPath("//span[text()='Submit']").click();
		Thread.sleep(2000);
		/*WebDriverWait wait1=new WebDriverWait(driver,40);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
		test.log(Status.INFO, "Record added successfully");*/
		driver.close();
		
}
		
		//Incomplete profiles
		
		@Test
		public void Incomplete() throws Exception {
			test = extent.createTest("AddIncompleteTest", "Testing the addition Incomplete Type Promotion");
			// login to the portal
			LoginScenarios.LandConfigTest();
			driver = LoginScenarios.driver;
			// click on App promotion module
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='App Promotions']")));
			driver.findElementByXPath("//span[text()='App Promotions']").click();
			//click add button
			driver.findElementByXPath("//span[text()='Add New']").click();
			driver.findElementById("mui-component-select-usecase").click();
			List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			if (list.size()>0) {
				//selecting last element of the list
				list.get(1).click();
			}
			driver.findElementByName("description").sendKeys("Test decription");
			driver.findElementByName("redirectTo").sendKeys("https://www.google.com");
			driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root']").click();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']")));
			driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']").click();
			WebDriverWait wait2=new WebDriverWait(driver,20);
			wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']")));
			driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']").click();
			driver.findElementByName("category").sendKeys("Test Category");
			list=null;
			driver.findElementById("mui-component-select-type").click();
			list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
			Thread.sleep(500);
			if (list.size()>0) {
				for(int i=0; i<list.size(); i++) {
					if(list.get(i).getText().equalsIgnoreCase("Incomplete Profiles")) {
						list.get(i).click();
						break;
					}
				}
			}
			String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
			driver.findElementByName("imageUrl").sendKeys(imagePath);
			String videoPath = System.getProperty("user.dir") + "/src/test/resources/test.mp4";
			driver.findElementByName("videoUrl").sendKeys(videoPath);
			//submit form
			driver.findElementByXPath("//span[text()='Submit']").click();
			Thread.sleep(2000);
			/*WebDriverWait wait1=new WebDriverWait(driver,40);
			wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));*/
			test.log(Status.INFO, "Record added successfully");
			driver.close();

		}
    	
    //delete app promotion
		@Test
	public void DelPromotion() throws Exception {
		test = extent.createTest("DeletePromotion", "Testing Delete Promotion Usecase");
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on App promotion module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='App Promotions']")));
		driver.findElementByXPath("//span[text()='App Promotions']").click();
		//locate the table on page
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
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[9]/button[2]")).click();
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
	
	//update promotion
		@Test
	public void UpdatePromotion() throws Exception {
			test = extent.createTest("UpdatePromotion", "Testing Update Promotion Usecase");
			
		// login to the portal
			LoginScenarios.LandConfigTest();
			driver = LoginScenarios.driver;
			// click on App promotion module
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='App Promotions']")));
			driver.findElementByXPath("//span[text()='App Promotions']").click();
			//locate the table on page
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
			WebElement htmltable = driver.findElement(By.tagName("table"));
			// check if any record appear in the table
			List<WebElement> tableRows = htmltable.findElements(By.xpath("//table/tbody/tr"));
			if (tableRows.size() > 0) {
				tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[9]/button[1]")).click();	
				driver.findElementById("mui-component-select-usecase").click();
				List<WebElement> list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
				Thread.sleep(500);
				if (list.size()>0) {
					//selecting last element of the list
					list.get(1).click();
				}
				driver.findElementByName("description").sendKeys(Keys.chord(Keys.CONTROL,"a"),"Updated Test decription");
				driver.findElementByName("redirectTo").sendKeys(Keys.chord(Keys.CONTROL,"a"),"https://www.update.com");
				driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root']").click();
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']")));
				driver.findElementByXPath("(//span[@class='MuiIconButton-label'])[5]").click();
				WebDriverWait wait2=new WebDriverWait(driver,20);
				wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']")));
				driver.findElementByXPath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and text()='15']").click();
				driver.findElementByName("category").sendKeys(Keys.chord(Keys.CONTROL,"a"),"Updated Test Category");
				list=null;
				driver.findElementById("mui-component-select-type").click();
				list=driver.findElementsByXPath("//ul[contains(@class, 'MuiList-root MuiMenu-list MuiList-padding')]//li");
				Thread.sleep(500);
				if (list.size()>0) {
					for(int i=0; i<list.size(); i++) {
						if(list.get(i).getText().equalsIgnoreCase("Incomplete Profiles")) {
							list.get(i).click();
							break;
						}
					}
				}
				String imagePath = System.getProperty("user.dir") + "/src/test/resources/test.png";
				driver.findElementByName("imageUrl").sendKeys(imagePath);
				String videoPath = System.getProperty("user.dir") + "/src/test/resources/test.mp4";
				driver.findElementByName("videoUrl").sendKeys(videoPath);
				//submit form
				//submit the form
				driver.findElementByXPath("//span[text()='Update']").click();
				WebDriverWait wait3 = new WebDriverWait(driver, 30);
				wait3.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Add New']")));
				test.log(Status.INFO, "Record Updated successfully");
			}
			else
			{
				 test.log(Status.INFO, "No data found for update");
				
			}
	        driver.close();
			

				
	}	
		
}
