package com.modules.ewp;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.login.LoginScenarios;

public class EwpSuperType {
	ChromeDriver driver;
	/*
	@Test
	public void AddSuperType() {
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open super type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Super Type']")));
		driver.findElementByXPath("//span[text()='EWP User Super Type']").click();
		// click on add button
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//main/div[1]/div[2]/button")));
		driver.findElementByXPath("//main/div[1]/div[2]/button").click();
		// add data in the form
		driver.findElementByName("superTypeName").sendKeys("Test Super Type");
		driver.findElementByName("description").sendKeys("This description is for my test super ewp profile type");
		driver.findElementByXPath("//form/div/div[3]/button[1]").click();
		
		driver.close();
	}
	
	//delete super type
	@Test
	public void DelSType() throws Throwable {
		// login to the portal
		
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open super type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Super Type']")));
		driver.findElementByXPath("//span[text()='EWP User Super Type']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
	    WebElement htmltable=driver.findElement(By.tagName("table"));
	    //check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			//if records are present, check the number of record present using pagination field
			String size=driver.findElementByXPath("//main/div[2]/div/div/p[2]").getText();
			String oldsize=size.substring(size.indexOf('f')+1);
			oldsize=oldsize.trim();
					//store the current number of records
			int length=Integer.parseInt(oldsize);
			//perform deletion by clicking the delete button
		tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[3]/button[2]")).click();
		Thread.sleep(400);
		driver.findElementByXPath("/html/body/div[2]/div[3]/div/div[3]/button[2]").click();
		Thread.sleep(500);
		//get the new number of records after deletion using pagination field
		size=driver.findElementByXPath("//main/div[2]/div/div/p[2]").getText();
				oldsize=size.substring(size.indexOf('f')+1);
				oldsize=oldsize.trim();
				//this line will check if record is deleted from the list
				if(length>Integer.parseInt(oldsize))
					Reporter.log("Deletion successful");
				else
					Reporter.log("Deletion Failed");
		
		}
		else
		{
			Reporter.log("No data exists");
			
		}
		
		driver.close();
		
		
	}
	*/
	//update super type
	@Test
	public void UpdateSType() {
		// login to the portal
		LoginScenarios.LandConfigTest();
		driver = LoginScenarios.driver;
		// click on ewp module
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP Profiles']")));
		driver.findElementByXPath("//span[text()='EWP Profiles']").click();
		// open super type page
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='EWP User Super Type']")));
		driver.findElementByXPath("//span[text()='EWP User Super Type']").click();
		driver.findElementByXPath("//main/div[2]");
		//locate the table on page
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));
		WebElement htmltable=driver.findElement(By.tagName("table"));
		//check if any record appear in the table
		List<WebElement> tableRows =  htmltable.findElements(By.xpath("//table/tbody/tr"));
		if(tableRows.size()>0) {
			tableRows.get(0).findElement(By.xpath("//table/tbody/tr[1]/td[3]/button[1]")).click();
			//driver.findElementByName("superTypeName").sendKeys(Keys.chord(Keys.CONTROL,"a"),"Updated Super Type");
			driver.findElementByName("description").sendKeys(Keys.chord(Keys.CONTROL,"a"),"This description is updated for my test super ewp profile type");
			driver.findElementByXPath("//span[text()='Update']").click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]/button[1]"))));
			
		}
		else
		{
			Reporter.log("No data exists");
			
		}
		
		driver.close();
	}

}
