package MarginInquiryTabs;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageClasses.GetExtentReports;
import PageClasses.Requirement_Strategies;
import TestDriver.Regression;
import exceloperations.excelutility;

public class Requirements extends Regression{
	WebDriver driver;
	
	public Requirements (WebDriver driver)
	{
		this.driver=driver;
	}

	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 
	
	
	public void Requirement(int value2, String Which_Tab, String Action) throws IOException {
		String sheet =Which_Tab;
		
		Regression objRegression;
		objRegression = new Regression();
		objRegression.basicDetails(sheet,value2); 
		
		//
		// Complete.
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
			
		try
		{
			switch(Which_Tab) 
				{
				case "SMA Activity":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("initialLink"))).click();
					break;
					
				case "Maintenance Requirement":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintLink"))).click();
					break;
					
				case "House Requirement":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("houseLink"))).click();
					break;
			
				}
		}
		catch (Exception e)
		{
			
			System.out.println("The tab "+Which_Tab+" is not available on screen.");
		}
		
			
			if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div/span/font"))).getText().contains(" Complete."))
			{
				try 
				{
					/*
					 * 
					 * SMA Activity	
					 *  //*[@id="initialLink"]
					 * Detect:-//*[@id="smaDataTable"]
					 * Table:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[1]
					 * Requirement:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[2]
					 * 
					 * Table:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[4]/td[1]
					 * Release:-/html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[4]/td[3]
					 */
					
					
					
					/*
					 * //*[@id="maintLink"]
					 * Maintainance Requirement	
					 * Detect:- //*[@id="maintDataTable"]
					 * Table:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[13]/td[1]
					 * Requirement:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[13]/td[2]
					 * 
					 */
					
					/*
					 * //*[@id="houseLink"]
					 * House Requirement	
					 * Detect:- //*[@id="houseDataTable"]
					 * Table:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[13]/td[1]
					 * Requirement:- /html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[13]/td[2]
					 * 
					 */
					
					
					
					
					//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/table/tbody/tr[1]/td/div/table/tbody/tr/th[1]"))).getText().equals("Position");
					
					
					WebElement ReqWebTable= wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody")));	
					int Req_Table_rows = ReqWebTable.findElements(By.xpath("tr")).size();
					
					GetExtentReports.sendMsg("Tab : "+Which_Tab+" "+ Action+" Started.");
		            System.out.println("\n"+Action+" "+Which_Tab+" Started.\n");
		
		
		            int current_row = value2;
		          /*  String sheet =Which_Tab;
		
					Regression objRegression;
					objRegression = new Regression();
					objRegression.basicDetails(sheet,value2); */
		
		            for(int tr=1;tr<=Req_Table_rows-1;tr++)
					{
		            	///html/body/form/div[14]/table/tbody/tr[2]/td/div/table/tbody/tr[5]/td[1]
		            	String Header_Element =ReqWebTable.findElement(By.xpath("tr["+tr+"]/td[1]")).getText();
		            	//System.out.println("ELement Found :- "+Header_Element);
		            	
		            	Requirement_Strategies strategies = new Requirement_Strategies();
						strategies.Elemento(Action,Which_Tab,Header_Element, ReqWebTable, tr, current_row);
		            	
		            	
					}
		            GetExtentReports.sendMsg(Action+" "+Which_Tab+" Successful.");
		            System.out.println("\n"+Action+" "+Which_Tab+" Successful.\n");
					
					
				}
				catch (Exception e)
				{
					System.out.println("Error 1 -->> "+e);
				}
			}
			else
			{
				
				try 
		        {    
	                String err1_Req = driver.findElement(By.xpath("/html/body/form/div[14]/div")).getText();
	                GetExtentReports.sendMsg(err1_Req);
	                System.err.println(err1_Req);
	            } 
				catch (Exception e) 
				{
		            String err2_Req = driver.findElement(By.xpath("//*[@id=\"advEdit\"]")).getText();
		            GetExtentReports.failMsg(err2_Req, driver);
		            System.out.println(err2_Req);
	            }
			}
					
		
		
		
	}



	
   
	
	
	

}
