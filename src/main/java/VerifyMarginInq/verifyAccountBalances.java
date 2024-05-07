package VerifyMarginInq;

import PageClasses.GetExtentReports;
import TestDriver.Regression;
import exceloperations.excelutility;
import org.openqa.selenium.*;
//import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class verifyAccountBalances extends Regression{

	private WebDriver driver;

	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 

	/*String xlpath = ".\\src\\test\\resources\\Call_Abetment_Suite.xlsx";
	excelutility xlutil=new excelutility(xlpath); */

    public verifyAccountBalances(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyAB(int value2) throws IOException, InterruptedException {
    	
    	
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
			if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[1]/font"))).getText().contains(" Complete."))
			{
		        try 
		        {
		        	 
		        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]")));
		
		        	 
				        
		        	//Thread.sleep(5000);
		        	//System.out.println(xlpath);
		            int current_row = value2;
		            //System.out.println("Current row coming from Baseline:- " + value2);
		            //System.out.println("We have received row count as " + current_row);
		            
		            System.out.println("\n Verifying the Account type Information.\n");
		            GetExtentReports.sendMsg("Tab : Account Balances \n Verifying the Account type Information.");
		            
		            Thread.sleep(3000);
		            
		           /* verifyTopFields("//*[@id=\"acctInfo_RepID\"]", "Rep ID", current_row, 3);
		           	verifyTopFields("//*[@id=\"acctInfo_officeID\"]", "Office or Division ID", current_row, 4);
		            verifyTopFields("//*[@id=\"lvCount\"]", "Liquidation Count", current_row, 5);
		            verifyTopFields("//*[@id=\"gfvCount\"]", "Good Faith Count", current_row, 6);*/
		            	        
		            verifyTopFields("acctInfo_RepID","Rep ID", current_row, 3);
		            verifyTopFields("acctInfo_officeID", "Office or Division ID", current_row, 4);
		            verifyTopFields("lvCount", "Liquidation Count", current_row, 5);
		            verifyTopFields("gfvCount", "Good Faith Count", current_row, 6);
			        
			        //Thread.sleep(1000);
		            //Left Side Table
		            WebElement left_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[1]/fieldset/table/tbody"));
		            int left_table_rows = left_table.findElements(By.xpath("tr")).size();
		            
		            //Thread.sleep(2000);
		            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[1]/fieldset/table")));
		            for (int active_row = 4; active_row <= left_table_rows; active_row++) {
		            	String element_found = left_table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
		            	//System.err.println("Inside left table");
		            	if(element_found.equals("    "))
		            	{
		            		//System.out.println("No more value.");
		            		break;
		            	}
		            	else 
		            	{
		            		verifyAccountTypeBalances(left_table, active_row, current_row);
		            	}
		            	//verifyAccountTypeBalances(left_table, active_row, current_row);
		            }
		            
		            
		            
		            //Thread.sleep(2000);
		            //Right Side Table
		            WebElement right_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[2]/fieldset/table/tbody"));
		            int right_table_rows = right_table.findElements(By.xpath("tr")).size();
		            
		            //Thread.sleep(2000);
		            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]/table/tbody/tr/td[2]/fieldset/table")));
		            for (int active_row = 2; active_row <= right_table_rows; active_row++) {
		            	String element_found = right_table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
		            	//System.err.println("Inside right table");
		            	if(element_found.equals("    "))
		            	{
		            		System.out.println("No more value.");
		            		break;
		            	}
		            	else 
		            	{
		            		verifyAccountTypeBalances(right_table, active_row, current_row);
		            	}
		            	
		            	//verifyAccountTypeBalances(right_table, active_row, current_row);
		            }
		           
		            
		            //Thread.sleep(2000);
		            //Bottom Side Table
		            WebElement bottom_table = driver.findElement(By.xpath("/html/body/form/div[14]/div[3]/fieldset/table/tbody"));
		            int bottom_table_rows = bottom_table.findElements(By.xpath("tr")).size();
		            //System.out.println("Bottom Table contains "+bottom_table_rows + " rows.");
		          
		           // Thread.sleep(2000);
		            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[3]/fieldset/table")));
		            for (int active_row = 2; active_row <= bottom_table_rows; active_row++) {
		            	//System.err.println("Inside bottom table");
		            	verifyAccountTypeBalances(bottom_table, active_row, current_row);
		            }
		            
		            
		            
		            GetExtentReports.sendMsg("Verified Account Balances.");
		            System.out.println("\n Verified the Account Balances Information.\n");
		
		        } 
		        catch (Exception e) 
		        {
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

    private void verifyTopFields(String ids, String verifyField_Name, int current_row, int cellOffset) throws InterruptedException, IOException {
        //Thread.sleep(1000);
        String currentField_value = driver.findElement(By.id(ids)).getAttribute("value");
        Thread.sleep(500);
        String verifyTopValue=xlutil.getCellData("Account Balances", current_row, cellOffset);
        //System.out.println("\nExcel Value:- "+verifyTopValue);
        //xlutil.setCellData("Account Balances", current_row, cellOffset, updateField_value);
        
        
        //Assert.assertEquals(verifyTopValue, currentField_value);
        GetExtentReports.verifyValue(verifyField_Name, verifyTopValue,currentField_value);
        //System.out.println("\t Field Name : \t" + updateField_Name + "\t updated with value : " + currentField_value);
        //System.out.println("\t"+verifyField_Name + " -->> Expected: "+ verifyTopValue + " ::  Actual : " + currentField_value);
    }

    private void verifyAccountTypeBalances(WebElement table, int active_row, int current_row) throws InterruptedException {
        Thread.sleep(500);
       // System.out.println("\n"+active_row+"\t"+current_row);
        String element_found = table.findElement(By.xpath("tr[" + active_row + "]/td[1]")).getText();
       // System.out.println("\n"+element_found);
       //Thread.sleep(500);

        if (isValueToBeVerified(element_found)) {
            try {
            	verifyTableValues(element_found, table, active_row, current_row);
            } catch (Exception e) {
            	System.out.println("\n"+e);
                System.err.println(" 🚧 Bad Request. Error:AUTOREG107 🚧 ");
            }
        }
    }

    private boolean isValueToBeVerified(String verifyFoundElement) throws InterruptedException {
    	//Thread.sleep(1000);
        return  verifyFoundElement.equals("USD") ||
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

    private void verifyTableValues(String element_found, WebElement table, int active_row, int current_row) throws InterruptedException, IOException {
    	//Thread.sleep(1000);
    	switch (element_found) {
            case "USD":
            	//System.out.println("\n This is the case");
            	verifyFinalABvalue(table, active_row, current_row, 3, 8 , 5);
                break;
            case "Net (USD)":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  11);
                break;
            case "Long":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  17);
                break;
            case "Short":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  23);
                break;
            case "Long Option":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  29);
                break;
            case "Short Option":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  35);
                break;

            case "Long Future":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  41);
                break;

            case "Short Future":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  47);
                break;

            case "Equity By Account Type":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  53);
                break;

            case "Net Liquidating Equity":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  59);
                break;

            case "Money Market BL":
            	verifyFinalABvalue(table, active_row, current_row, 2, 7,  65);
                break;

            case "Initial Requirements":
            	//System.out.println("Initial Requirements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  71);
                break;
            case "Initial Excess":
            	//System.out.println("Initial Excess");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  72);
                break;
                
            case "Maintenance Requirements":
            	//System.out.println("Maintenance Requirements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  73);
                break;
                
            case "Maintenance Excess/Deficit":
            	//System.out.println("Maintenance Excess/Deficit");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  74);
                break;
                
            case "House Requirements":
            	//System.out.println("House Requirements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  75);
                break;
                
            case "House Excess/Deficit":
            	//System.out.println("House Excess/Deficit");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  76);
                break;
                
            case "Day Trade Requirements":
            	//System.out.println("Day Trade Requirements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  77);
                break;
                
            case "Day Trade Excess":
            	//System.out.println("Day Trade Excess");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  78);
                break;
                
            case "Margin TD Equity":
            	//System.out.println("Margin TD Equity");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  79);
                break;
                
            case "Percent Equity":
            	//System.out.println("Percent Equity");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  80);
                break;
                
            case "Margin TD Net Liq Equity":
            	//System.out.println("Margin TD Net Liq Equity");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  81);
                break;
                
            case "TD AWNLE":
            	//System.out.println("TD AWNLE");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  82);
                break;
                
            case "SMA":
            	//System.out.println("SMA");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  83);
                break;
                
            case "Day Trade Buying Power":
            	//System.out.println("Day Trade Buying Power");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  84);
                break;
                
            case "Overnight Buying Power":
            	//System.out.println("Overnight Buying Power");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  85);
                break;
                
            case "Available Cash":
            	//System.out.println("Available Cash");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  86);
                break;
                
            case "Pending Disbursements":
            	//System.out.println("Pending Disbursements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  87);
                break;
                
            case "Option Requirements":
            	//System.out.println("Option Requirements");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  88);
                break;
                
            case "Pended Proceeds":
            	//System.out.println("Pended Proceeds");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  89);
                break;
                
            case "Money Reduction Amount":
            	//System.out.println("Money Reduction Amount");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  90);
                break;
                
            case "Usable SD Balance":
            	//System.out.println("Usable SD Balance");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  91);
                break;
                
                //Open Maintenance Calls
            case "Open Maintenance Calls":
            	//System.out.println("Open Maintenance Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 10,  94);
                break;
                
                //Open House Calls
                
            case "Open House Calls":
            	//System.out.println("Open House Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 10,  99);
                break;
                
                
                //Open Initial Calls
            case "Open Initial Calls":
            	//System.out.println("Open Initial Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 10,  104);
                break;
                
                //Open Day Trade Calls
                
            case "Open Day Trade Calls":
            	//System.out.println("Open Day Trade Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 10,  109);
                break;
                
                
                //Intraday House Calls
                
            case "Intraday House Calls":
            	//System.out.println("Intraday House Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 2,  114);
                break;
              
                //Open Cash Calls
                
            case "Open Cash Calls":
            	//System.out.println("Open Cash Calls");
            	verifyFinalABvalue(table, active_row, current_row, 2, 10,  119);
                break;
                
             
                
                
        }
    }

    private void verifyFinalABvalue(WebElement table, int active_row, int current_row, int startColumn,int endColumn, int fromExcelCol) throws InterruptedException, IOException {
    	//Thread.sleep(1000);
    	for (int cl = startColumn; cl <= endColumn; cl++) {
    		
    		
    		//System.out.println("\n Active_row = "+active_row+ " and Cl = "+cl +"  From Excel Col " + fromExcelCol);
    		//Thread.sleep(1000);
    		if(startColumn==3)
    		{
    			
    			String verifyField_Name = xlutil.getCellData("Account Balances", 0, cl-1 + fromExcelCol);
                //Thread.sleep(1000);
                String currentField_value = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                //System.out.println("\nWeb Value = "+ currentField_value );//+ " at row " + active_row + " at column " + cl);
                
                //String tbval = valueAB.getText();
                //Thread.sleep(1000);
               String  verifyExcelValue=xlutil.getCellData("Account Balances", current_row, cl-1 + fromExcelCol);
               //System.out.println("\nExcel Value = "+ verifyExcelValue);
                //GetExtentReports.updateValue(verifyField_Name, valueAB);
               //Assert.assertEquals(verifyExcelValue, currentField_value);
               GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue,currentField_value);
               
               // System.out.println("\t"+verifyField_Name + " -->> Expected: "+ verifyExcelValue + " ::  Actual : " + currentField_value);
                
               /* String verifyTopValue=xlutil.getCellData("Account Balances", current_row, fromExcelCol);
                System.out.println("Excel Value = "+verifyTopValue + " at row " + active_row + " at column " + cl);
                //xlutil.setCellData("Account Balances", current_row, cellOffset, updateField_value);
                
                
                Assert.assertEquals(verifyTopValue, currentField_value);
                GetExtentReports.verifyValue(verifyField_Name, verifyTopValue,currentField_value);
                //System.out.println("\t Field Name : \t" + updateField_Name + "\t updated with value : " + currentField_value);
                System.out.println("\t"+verifyField_Name + " -->> Expected: "+ verifyTopValue + " ::  Actual : " + currentField_value);*/

    			
    		}
    		
    		else if (fromExcelCol>=92)
    		{
    			
                //Thread.sleep(1000);
                if(cl%2==0)
                {
                	String verifyField_Name = xlutil.getCellData("Account Balances", 0, fromExcelCol);
                	String currentField_value = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                    //System.out.println("\n Web Value = "+ currentField_value);
                	//String tbval = valueAB.getText();
                    //Thread.sleep(1000);
                    /*xlutil.setCellData("Account Balances", current_row, cl-1 + fromExcelCol, valueAB);
                    GetExtentReports.updateValue(currentField_value, valueAB);
                    System.out.println("\n\t Field Name : \t" + currentField_value + "\t updated with value : " + valueAB);*/
                    
                    String  verifyExcelValue=xlutil.getCellData("Account Balances", current_row, fromExcelCol);
                   // System.out.println("\nExcel Value = "+ verifyExcelValue);
                    //GetExtentReports.updateValue(verifyField_Name, valueAB);
                   //Assert.assertEquals(verifyExcelValue, currentField_value);
                   GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue,currentField_value);
                   
                   // System.out.println("\t"+verifyField_Name + " -->> Expected: "+ verifyExcelValue + " ::  Actual : " + currentField_value);
                    fromExcelCol++;
                } 
    			
    		}
    		
    		else
    		{
    			//Thread.sleep(1000);
    			String verifyField_Name = xlutil.getCellData("Account Balances", 0, cl + fromExcelCol);
                
                String currentField_value = table.findElement(By.xpath("tr[" + active_row + "]/td[" + cl + "]")).getText();
                //System.out.println("\n Web Value = "+ currentField_value);
                //String tbval = valueAB.getText();
                //Thread.sleep(1000);
               /* xlutil.setCellData("Account Balances", current_row, cl + fromExcelCol, valueAB);
                GetExtentReports.updateValue(excelHeader, valueAB);
                System.out.println("\n\t Field Name : \t" + excelHeader + "\t updated with value : " + valueAB);*/
                
                String  verifyExcelValue=xlutil.getCellData("Account Balances", current_row, cl + fromExcelCol);
               // System.out.println("\nExcel Value = "+ verifyExcelValue);
                //GetExtentReports.updateValue(verifyField_Name, valueAB);
               //Assert.assertEquals(verifyExcelValue, currentField_value);
               GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue,currentField_value);
               
                //System.out.println("\t"+verifyField_Name + " -->> Expected: "+ verifyExcelValue + " ::  Actual : " + currentField_value);
                
    			
    		}
    		
        }
    }

}
