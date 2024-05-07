package PageClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;






public class GetExtentReports {
	public static WebDriver driver;
	public static ExtentSparkReporter spark;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test;
	public static final String CONFIG_FILE_PATH = "config.properties";
	
	@SuppressWarnings("static-access")
	public GetExtentReports (WebDriver driver)
	{
		this.driver=driver;
	}
	
    public static Properties config = ScreenObjects.loadConfigProperties(CONFIG_FILE_PATH);
   // public static String getSuite = config.getProperty("Regression_Suite");
    public static void initializeExtentReport(String Product,String User, String Build)
    
    
    {
    	

    	System.out.println("Initialized Extent Report");
    	
    	String dateTime= new SimpleDateFormat("dd MMM hh mm a").format(new Date()); // //
    	spark=new ExtentSparkReporter(config.getProperty("Logs_Destination")+"/"+config.getProperty("Regression_Suite")+"_Regression_Suite_"+dateTime+".html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY,ViewName.DEVICE,ViewName.LOG}).apply();
    	spark.config().setDocumentTitle(config.getProperty("Regression_Suite")+" Automation Report");
    	spark.config().setReportName(config.getProperty("Regression_Suite"));
    	spark.config().setTheme(Theme.DARK);
    
    	//htmlReporter.config().setDocumentTitle("<img src='path_to_your_logo.png' alt='Logo' style='width:100px;height:100px;'>");
    	extent.attachReporter(spark);
    	

    	extent.setSystemInfo("Product", "FIS : Stream "+Product);
    	extent.setSystemInfo("Regression Suite", config.getProperty("Regression_Suite"));
    	try 
    	{
    		extent.setSystemInfo("Web URL", driver.getCurrentUrl());
    	}
    	catch(Exception e)
    	{
    		
    	}
    	extent.setSystemInfo("QA Assigned", User);
    	extent.setSystemInfo("Release Version", Build);
    
    }

    public static void createTest(String acc, String date , String Desc, String SFPCM) {
    	test=extent.createTest("Account: "+ acc + " Date: " +date,Desc).assignCategory(SFPCM);
    	//test.assignCategory(SFPCM);
    	System.out.println("Created Test");
    	
    }
    
    public static void ignoredTest(int row) {
    	test=extent.createTest("Ignored the case at: "+ row);
    	test.info("The case has been ignored.");
    	System.out.println("Ignore the Test Case at " + row + " as indicator is OFF.");
    	
    }
    
    public static void sendMsg(String info) {
    	//extent.getTest(testName).log(Status.INFO, info);
    	
    	test.info(info);

    	//System.out.println("Logged info");
    	//test.info("<table border='1'><tr><th>Information </th></tr><tr><th>"+ info+" </th></tr></table>");
    }

    public static void updateValue(String field,String value) {
    	
        test.log(Status.PASS, "\t Field Name : \t"+ field + "\t updated with value : " + value);
        System.out.print("\t Updated value 🔜");
        
    }
    
  public static void updateStatus(String field,String status) {
    	
        test.log(Status.INFO, "\t Tab : \t"+ field + "\t Status : " + status);
        
        //System.out.print("\t Updated value 🔜");
        
    }
  
  
  public static void verifyStatus(String field,String baseStatus, String webStatus) {
  	try
  	{
	    	//Assert.assertEquals(baseValue, webValue);
  		if(baseStatus.equals(webStatus))
  		{
  			test.log(Status.PASS, "\t Tab : \t"+ field + "\t Expected Status : " + baseStatus + "\t Actual Status : "+webStatus );
  			System.out.print("\t Verified Value 🔜");
  			System.out.println("\t Tab : \t"+ field + "\t Expected Status : " + baseStatus + "\t Actual Status : "+webStatus);
  		}
  		else
  		{
  			//System.out.println("Values mismatch. ");
      		test.log(Status.FAIL, "\t Tab : \t"+ field + "\t Expected Status : " + baseStatus + "\t Actual Status : "+webStatus );
      		System.err.print("\t Mismatched Value 🔜");
      		System.err.println("\t Tab  : \t"+ field + "\t Expected Status : " + baseStatus + "\t Actual Status : "+webStatus);
      		//System.out.println("\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue);
  		}
	        
	        
  	}
  	catch(Exception e)
  	{
  		System.out.println("Values mismatch. "+e);
  		//test.log(Status.FAIL, "\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue );
  	}
      
  }
    
    public static void verifyValue(String field,String baseValue, String webValue) {
    	try
    	{
	    	//Assert.assertEquals(baseValue, webValue);
    		if(baseValue.equals(webValue))
    		{
    			test.log(Status.PASS, "\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue );
    			System.out.print("\t Verified Value 🔜");
    			System.out.println("\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue);
    		}
    		else
    		{
    			//System.out.println("Values mismatch. ");
        		test.log(Status.FAIL, "\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue );
        		System.err.print("\t Mismatched Value 🔜");
        		System.err.println("\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue);
        		//System.out.println("\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue);
    		}
	        
	        
    	}
    	catch(Exception e)
    	{
    		System.out.println("Values mismatch. "+e);
    		//test.log(Status.FAIL, "\t Field name : \t"+ field + "\t Expected Value : " + baseValue + "\t Actual Value : "+webValue );
    	}
        
    }
    
    public static void failMsg(String error, WebDriver driver) throws IOException {
    	//test.log(Status.FAIL, " ⚠️ " +error);	
        String sspath=GetExtentReports.getScreenshot(driver, error);
        //test.addScreenCaptureFromPath(sspath, error);
        test.log(Status.FAIL,"<font color='red'>"+  " ⚠️ " +error+"</font>").addScreenCaptureFromPath(sspath, error);
        System.out.println(" Failed Message");
    }
    
    public static void positionImage(WebDriver driver) throws IOException {
        String sspath=GetExtentReports.getScreenshot(driver, "Position Tab");
        test.addScreenCaptureFromPath(sspath);
        
        
    }
    
   
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
    	
    	String dateName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);

    	String destination = config.getProperty("Logs_Destination")+"/"+ screenshotName + dateName + ".png";
    	File finalDestination = new File(destination);
    	FileUtils.copyFile(source, finalDestination);
		return destination;
    } 

    
    public static void closeExtentReport() {
    	System.out.println("Closed extent Reports");
        extent.flush();
    }
}
