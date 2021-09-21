package com.extent.report;

import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBaseClass {
	public static ExtentReports extent;
	public static ExtentSparkReporter spark ;
	public static ExtentTest test;
	@BeforeSuite
	public void SetUp() {
		// start reporters
		spark = new ExtentSparkReporter("Spark.html");
		// create ExtentReports and attach reporter(s)
	    extent = new ExtentReports();
		extent.attachReporter(spark);
		
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
	    if(result.getStatus() == ITestResult.FAILURE)
	    {
	        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	        test.fail(result.getThrowable());
	    }
	    else if(result.getStatus() == ITestResult.SUCCESS)
	    {
	        test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	    }
	    else
	    {
	        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	        test.skip(result.getThrowable());
	    }
	}

	@AfterMethod
	@AfterSuite
	public void tearDown(){
	    extent.flush();
	    
	   }
	}


