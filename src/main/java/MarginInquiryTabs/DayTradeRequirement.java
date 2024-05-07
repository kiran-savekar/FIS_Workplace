package MarginInquiryTabs;

import PageClasses.GetExtentReports;
import TestDriver.Regression;
import exceloperations.excelutility;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DayTradeRequirement extends Regression{

	private WebDriver driver;
	
	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 

	public DayTradeRequirement (WebDriver driver) 
	{
		this.driver=driver;
		
	}


	public void DTRequirement(int value2, String Action) throws IOException, InterruptedException {
		
		
		String sheet = "DayTradeRequirements";//DayTradeRequirements
        Regression objRegression;
		objRegression = new Regression();
		objRegression.basicDetails(sheet,value2); 
		
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));//50 Seconds
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(5));//5 Seconds
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dayTradeLink"))).click();; //Clicking on DT Tab
		
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]/table[2]/tbody"))); //Wait till the DT Main Table is visible
		
		try 
        
        {
			
    	
            int current_row = value2;//Storing active row value which is coming from Regression
            
            System.out.println("\n Updating the Day Trade Requirements Information.\n");
            GetExtentReports.sendMsg("Tab : Day Trade Requirements \n Updating the Day Trade Requirements Information.");
           


            //System.out.println("We have received row count as " + current_row);

            //Main DT Table
            WebElement DT_Main_Table = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/table[2]/tbody"));
            int DT_Main_Table_rows = DT_Main_Table.findElements(By.xpath("tr")).size();
            System.out.println("Total DT Table rows are:-" + DT_Main_Table_rows);
            int  col=3;//Starting from 3 column in DT sheet
            
            //Updating Main DT Table data into Excel
            for (int tr = 1; tr <= DT_Main_Table_rows; tr++) //Looping row wise until last row as DT_Main_Table_rows
            {
            	
            	for (int cl=2;cl<=4;cl+=2) //Only taking values at index 2,4 
            		
            	{
            		if(Action.equals("Update"))
            		{
            			String header = xlutil.getCellData(sheet, 0, col);
            			String dt_element_found = DT_Main_Table.findElement(By.xpath("tr[" + tr + "]/td["+cl+"]")).getText();
                    	xlutil.setCellData(sheet, current_row, col, dt_element_found);
                    	GetExtentReports.updateValue(header, dt_element_found);
                    	System.out.println("\tField Name : \t"+ header + "\t updated with value : " + dt_element_found);
						
            		}
            		else
            		{
            			String verifyField_Name = xlutil.getCellData("DayTradeRequirements", 0, col);
            			
            			String currentField_value = DT_Main_Table.findElement(By.xpath("tr[" + tr + "]/td["+cl+"]")).getText();
                    	
            			String verifyExcelValue= xlutil.getCellData("DayTradeRequirements", current_row, col);
                    	
            			GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue, currentField_value);
						
            		}
            			
            		col++;
            	}
            }
            //Top DT Table data is updated now
            //Clicking on View Day Trade Activity button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("DayTradeActivity"))).click();
            
         
        	 //Waiting for DT Req. bottom table
               WebElement DT_Bottom_Table = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[4]/div[2]/table/tbody")));
               int DT_Bottom_Table_rows = DT_Bottom_Table.findElements(By.xpath("tr")).size();
               //System.out.println("Bottom DT Table has " +DT_Bottom_Table_rows+" rows.");
               
             //Updating DT Bottom Table data into Excel
               for(int tr=3;tr<=DT_Bottom_Table_rows;tr++)//Looping row wise until last row as DT_Bottom_Table_rows
               {
            	   for(int cl=1;cl<=16;cl++) //Taking values from index 1 to 16
            	   {
            		   if(Action.equals("Update"))
            		   {
            			   String header = xlutil.getCellData(sheet, 0, cl+10);//Added 10 because top 8 elements already added to excel
                  			
                		   String bottom_DT_element = DT_Bottom_Table.findElement(By.xpath("tr["+tr+"]/td["+cl+"]")).getText();
                		   xlutil.setCellData(sheet, current_row, col, bottom_DT_element);
                       	GetExtentReports.updateValue(header, bottom_DT_element);
                       	//System.out.println("DT Element at [" +left_elements+ "] [" +i +"] is "+dt_element_found);
                       	System.out.println("\tField Name : \t"+ header + "\t updated with value : " + bottom_DT_element);
                       	
            		   }
            		   else
            		   {

	            		   String verifyField_Name = xlutil.getCellData("DayTradeRequirements", 0, cl+10);//Added 10 because top 8 elements already added to excel
	           			
	            		   String currentField_value = DT_Bottom_Table.findElement(By.xpath("tr["+tr+"]/td["+cl+"]")).getText();
	            		   
	            		   String verifyExcelValue= xlutil.getCellData("DayTradeRequirements", current_row, col);
	            		   
	            		   GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue, currentField_value);
	                   	
            		   }
            		   
            		   col++;
            	   }
            	   
               }
               
            GetExtentReports.sendMsg("Updated Day Trade Requirements.");
            System.out.println("\n Updated the Day Trade Requirements Information.\n");

        } catch (Exception e) {
        	
        	try 
        	{
                String err1_DTReq = driver.findElement(By.xpath("/html/body/form/div[14]/div[4]/span/font")).getText();
                GetExtentReports.sendMsg(err1_DTReq);
                System.err.println(err1_DTReq);
            } catch (Exception no) {
            	
	            String err2_DTReq = driver.findElement(By.xpath("//*[@id=\"advEdit\"]")).getText();
	            GetExtentReports.failMsg(err2_DTReq, driver);
	            System.err.println(err2_DTReq);
            }
          
        }
    }
}
