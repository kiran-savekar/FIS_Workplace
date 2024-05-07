package MarginInquiryTabs;

import PageClasses.GetExtentReports;
import TestDriver.Regression;
import exceloperations.excelutility;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class updateAccountBalances extends Regression{
	

	private WebDriver driver;
	
	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 

	/*String xlpath = ".\\src\\test\\resources\\Call_Abetment_Suite.xlsx";
	excelutility xlutil=new excelutility(xlpath); */
	

	
	public updateAccountBalances (WebDriver driver) 
	{
		this.driver=driver;
		
	}


	public void updateAB(int value2) throws IOException, InterruptedException {
        
		String sheet = "Account Balances";
		Regression objRegression;
		objRegression = new Regression();
		objRegression.basicDetails(sheet,value2); 
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		//Thread.sleep(2000);
		if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[1]/font"))).getText().contains(" Complete."))
		{
			try 
	        
	        {
	    		
				 
				 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]")));
				 
				 /*
				  * 
				  * 
				  * String acc=driver.findElement(By.id("accountId")).getAttribute("value");
				 xlutil.setCellData(sheet, value2, 0, acc);
				 String date=driver.findElement(By.id("date")).getAttribute("value");
				 xlutil.setCellData(sheet, value2, 1, date);
				 WebElement regclass=driver.findElement(By.id("accountRegClassId"));
				 Select select = new Select(regclass);
				 
				 String selectedRegClass = select.getFirstSelectedOption().getText();
				 
				 xlutil.setCellData(sheet, value2, 2, selectedRegClass);

				System.out.println("Account Number:-"+ acc+  " Date:-" +date+ " Account_Reg_Class:-"+selectedRegClass);
				
				*
				*
				*
				*
				*/

				 
				 //System.out.println("Account Number:-"+ acc+  " Date:-" +date+ " Account_Reg_Class:-"+regclass);
					

					
					
	            int current_row = value2;
	            
	            System.out.println("Current row coming from Baseline:- " + value2);
	            System.out.println("\n Updating the Account type Information.\n");
	            GetExtentReports.sendMsg("Tab : Account Balances \n Updating the Account type Information.");

	            
	           // System.out.println("We have received row count as " + current_row);
				
				
				
	            Thread.sleep(1500);
	            
	            updateTopFields(sheet,"acctInfo_RepID","Rep ID", current_row, 3);
	            updateTopFields(sheet,"acctInfo_officeID", "Office or Division ID", current_row, 4);
	            updateTopFields(sheet,"lvCount", "Liquidation Count", current_row, 5);
	            updateTopFields(sheet,"gfvCount", "Good Faith Count", current_row, 6);
	            
	            
	            //Left Side Table
	            WebElement left_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[1]/fieldset/table/tbody"));
	            int left_table_rows = left_table.findElements(By.xpath("tr")).size();
	            
	            for (int active_row = 4; active_row <= left_table_rows; active_row++) {
	            	
	            	String element_found = left_table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
	            	if(element_found.equals("    "))
	            	{
	            		//System.out.println("No more value.");
	            		break;
	            	}
	            	else 
	            	{
	            		updateAccountTypeBalances(sheet,left_table, active_row, current_row);
	            	}
	            	
	            }
	            
	            
	            //Right Side Table
	            WebElement right_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[2]/fieldset/table/tbody"));
	            int right_table_rows = right_table.findElements(By.xpath("tr")).size();
	            
	            for (int active_row = 2; active_row <= right_table_rows; active_row++) {
	            	String element_found = right_table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
	            	if(element_found.equals("    "))
	            	{
	            		System.out.println("No more value.");
	            		break;
	            	}
	            	else 
	            	{
	            		updateAccountTypeBalances(sheet,right_table, active_row, current_row);
	            	}
	            	//updateAccountTypeBalances(right_table, active_row, current_row);
	            }
	            
	            
	            //Bottom Side Table
	            WebElement bottom_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[3]/fieldset/table/tbody"));
	            int bottom_table_rows = bottom_table.findElements(By.xpath("tr")).size();
	            //System.out.println("Bottom Table contains "+bottom_table_rows + " rows.");
	            
	            for (int active_row = 2; active_row <= bottom_table_rows; active_row++) {
	            	updateAccountTypeBalances(sheet,bottom_table, active_row, current_row);
	            }
	            
	            GetExtentReports.sendMsg("Updated Account Balances.");
	            System.out.println("\n Updated the Account type Information.\n");

	        } catch (Exception e) {
	        	
	        	
	        	System.err.println(e);
	          
	        }
		}
		else
		{
			 try 
	            {
	                String err1_updateAB = driver.findElement(By.xpath("/html/body/form/div[14]/div")).getText();
	                GetExtentReports.failMsg(err1_updateAB, driver);
	                System.err.println(err1_updateAB);
	            } 
	            catch (Exception e2) 
	            {
	                String err2_updateAB = driver.findElement(By.xpath("//*[@id=\"advEdit\"]")).getText();
	                GetExtentReports.failMsg(err2_updateAB, driver);
	                System.err.println(err2_updateAB);
	            }
		}
		
		
    }

    private void updateTopFields(String sheet,String ids, String updateField_Name, int current_row, int cellOffset) throws InterruptedException, IOException {
        //Thread.sleep(2000);
        String updateField_value = driver.findElement(By.id(ids)).getAttribute("value");
        //Thread.sleep(1000);
        xlutil.setCellData(sheet, current_row, cellOffset, updateField_value);
        GetExtentReports.updateValue(updateField_Name, updateField_value);
        System.out.println("\t Field Name : \t" + updateField_Name + "\t updated with value : " + updateField_value);
    }

    private void updateAccountTypeBalances(String sheet,WebElement table, int active_row, int current_row) throws InterruptedException {
        //Thread.sleep(1000);
       // System.out.println(active_row+"\t"+current_row);
        String element_found = table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
       //System.out.println(element_found);
       // Thread.sleep(500);

        if (isValueToBeUpdated(element_found)) {
            try {
            	updateTableValues(sheet,element_found, table, active_row, current_row);
            } catch (Exception e) {
            	
            	System.out.println(e);
                System.err.println(" 🚧 Bad Request. Error:AUTOREG107 🚧 ");
            }
        }
    }

    private boolean isValueToBeUpdated(String verifyFoundElement) {
        return  
        		//verifyFoundElement.equals("    ") ||
        		verifyFoundElement.equals("USD") ||
        		verifyFoundElement.equals("Net (USD)") ||
        		verifyFoundElement.equals("Long") ||
        		verifyFoundElement.equals("Short") ||
        		verifyFoundElement.equals("Long Option") ||
        		verifyFoundElement.equals("Short Option") ||
        		verifyFoundElement.equals("Long Future") ||
        		verifyFoundElement.equals("Short Future") ||
        		verifyFoundElement.equals("Equity By Account Type") ||
        		verifyFoundElement.equals("Net Liquidating Equity") ||
        		verifyFoundElement.equals("Money Market BL") ||
        		verifyFoundElement.equals("Initial Requirements") ||
        		verifyFoundElement.equals("Initial Excess") ||
        		verifyFoundElement.equals("Maintenance Requirements") ||
        		verifyFoundElement.equals("Maintenance Excess/Deficit") ||
        		verifyFoundElement.equals("House Requirements") ||
        		verifyFoundElement.equals("House Excess/Deficit") ||
        		verifyFoundElement.equals("Day Trade Requirements") ||
        		verifyFoundElement.equals("Day Trade Excess") ||
        		verifyFoundElement.equals("Margin TD Equity") ||
        		verifyFoundElement.equals("Percent Equity") ||
        		verifyFoundElement.equals("Margin TD Net Liq Equity") ||
        		verifyFoundElement.equals("TD AWNLE") ||
        		verifyFoundElement.equals("SMA") ||
        		verifyFoundElement.equals("Day Trade Buying Power") ||
        		verifyFoundElement.equals("Overnight Buying Power") ||
        		verifyFoundElement.equals("Available Cash") ||
        		verifyFoundElement.equals("Pending Disbursements") ||
        		verifyFoundElement.equals("Usable SD Balance") ||
        		verifyFoundElement.equals("Money Reduction Amount") ||
        		verifyFoundElement.equals("Pended Proceeds") ||
        		verifyFoundElement.equals("Option Requirements") ||
        		verifyFoundElement.equals("Open Maintenance Calls") ||
        		verifyFoundElement.equals("Open House Calls") ||
        		verifyFoundElement.equals("Open Initial Calls") ||
        		verifyFoundElement.equals("Open Day Trade Calls") ||
        		verifyFoundElement.equals("Intraday House Calls")  ||
        		verifyFoundElement.equals("Open Cash Calls");
    }

    private void updateTableValues(String sheet,String element_found, WebElement table, int active_row, int current_row) throws InterruptedException, IOException {
        switch (element_found) {
        
        	/*case "    ":
        		System.out.println("No more value.");
            break;*/
        
        
            case "USD":
            	//System.out.println("This is the case");
            	updateFinalABvalue(sheet,table, active_row, current_row, 3, 8 , 5);
                break;
            case "Net (USD)":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  11);
                break;
            case "Long":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  17);
                break;
            case "Short":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  23);
                break;
            case "Long Option":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  29);
                break;
            case "Short Option":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  35);
                break;

            case "Long Future":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  41);
                break;

            case "Short Future":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  47);
                break;

            case "Equity By Account Type":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  53);
                break;

            case "Net Liquidating Equity":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  59);
                break;

            case "Money Market BL":
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 7,  65);
                break;

            case "Initial Requirements":
            	//System.out.println("Initial Requirements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  71);
                break;
            case "Initial Excess":
            	//System.out.println("Initial Excess");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  72);
                break;
                
            case "Maintenance Requirements":
            	//System.out.println("Maintenance Requirements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  73);
                break;
                
            case "Maintenance Excess/Deficit":
            	//System.out.println("Maintenance Excess/Deficit");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  74);
                break;
                
            case "House Requirements":
            	//System.out.println("House Requirements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  75);
                break;
                
            case "House Excess/Deficit":
            	//System.out.println("House Excess/Deficit");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  76);
                break;
                
            case "Day Trade Requirements":
            	//System.out.println("Day Trade Requirements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  77);
                break;
                
            case "Day Trade Excess":
            	//System.out.println("Day Trade Excess");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  78);
                break;
                
            case "Margin TD Equity":
            	//System.out.println("Margin TD Equity");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  79);
                break;
                
            case "Percent Equity":
            	//System.out.println("Percent Equity");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  80);
                break;
                
            case "Margin TD Net Liq Equity":
            	//System.out.println("Margin TD Net Liq Equity");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  81);
                break;
                
            case "TD AWNLE":
            	//System.out.println("TD AWNLE");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  82);
                break;
                
            case "SMA":
            	//System.out.println("SMA");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  83);
                break;
                
            case "Day Trade Buying Power":
            	//System.out.println("Day Trade Buying Power");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  84);
                break;
                
            case "Overnight Buying Power":
            	//System.out.println("Overnight Buying Power");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  85);
                break;
                
            case "Available Cash":
            	//System.out.println("Available Cash");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  86);
                break;
                
            case "Pending Disbursements":
            	//System.out.println("Pending Disbursements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  87);
                break;
                
            case "Option Requirements":
            	//System.out.println("Option Requirements");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  88);
                break;
                
            case "Pended Proceeds":
            	//System.out.println("Pended Proceeds");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  89);
                break;
                
            case "Money Reduction Amount":
            	//System.out.println("Money Reduction Amount");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  90);
                break;
                
            case "Usable SD Balance":
            	//System.out.println("Usable SD Balance");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  91);
                break;
                
                //Open Maintenance Calls
            case "Open Maintenance Calls":
            	//System.out.println("Open Maintenance Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 10,  94);
                break;
                
                //Open House Calls
                
            case "Open House Calls":
            	//System.out.println("Open House Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 10,  99);
                break;
                
                
                //Open Initial Calls
            case "Open Initial Calls":
            	//System.out.println("Open Initial Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 10,  104);
                break;
                
                //Open Day Trade Calls
                
            case "Open Day Trade Calls":
            	//System.out.println("Open Day Trade Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 10,  109);
                break;
                
                
                //Intraday House Calls
                
            case "Intraday House Calls":
            	//System.out.println("Intraday House Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 2,  114);
                break;
              
                //Open Cash Calls
                
            case "Open Cash Calls":
            	//System.out.println("Open Cash Calls");
            	updateFinalABvalue(sheet,table, active_row, current_row, 2, 10,  115);
                break;
                
             
                
                
        }
    }

    private void updateFinalABvalue(String sheet,WebElement table, int active_row, int current_row, int startColumn,int endColumn, int fromExcelCol) throws InterruptedException, IOException {
     
    	for (int cl = startColumn; cl <= endColumn; cl++) {
    		
    		
    		//System.out.println("Active_row = \t"+active_row+ " and Cl = \t"+cl +"From Excel Col \t" + fromExcelCol);
    		
    		if(startColumn==3)
    		{
    			
    			String excelHeader = xlutil.getCellData(sheet, 0, cl-1 + fromExcelCol);
                //Thread.sleep(1000);
                String valueAB = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                //String tbval = valueAB.getText();
                //Thread.sleep(1000);
                xlutil.setCellData(sheet, current_row, cl-1 + fromExcelCol, valueAB);
                GetExtentReports.updateValue(excelHeader, valueAB);
                System.out.println("\t Field Name : \t" + excelHeader + "\t updated with value : " + valueAB);
    			
    		}
    		else if (fromExcelCol>=92)
    		{
    			

    			//int Excel_calls=fromExcelCol+(cl%2);
    			
                if(cl%2==0)
                {
                	String excelHeader = xlutil.getCellData(sheet, 0, fromExcelCol);
                    //Thread.sleep(1000);
                	String valueAB = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                	//String tbval = valueAB.getText();
                    //Thread.sleep(1000);
                    xlutil.setCellData(sheet, current_row, fromExcelCol, valueAB);
                    GetExtentReports.updateValue(excelHeader, valueAB);
                    System.out.println("\t Field Name : \t" + excelHeader + "\t updated with value : " + valueAB);
                    fromExcelCol++;
                } 
                
    			
    		}
    		else
    		{
    			
    			String excelHeader = xlutil.getCellData(sheet, 0, cl + fromExcelCol);
                //Thread.sleep(1000);
                String valueAB = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                //String tbval = valueAB.getText();
                //Thread.sleep(1000);
                xlutil.setCellData(sheet, current_row, cl + fromExcelCol, valueAB);
                GetExtentReports.updateValue(excelHeader, valueAB);
                System.out.println("\t Field Name : \t" + excelHeader + "\t updated with value : " + valueAB);
    			
    		}
    		
        }
    }
}
