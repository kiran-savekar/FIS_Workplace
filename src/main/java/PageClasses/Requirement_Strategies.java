package PageClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import TestDriver.Regression;
import exceloperations.excelutility;


public class Requirement_Strategies extends Regression{
	

	
	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 
	
	public  void Elemento(String Action,String Tab,String verifyFoundElement, WebElement WebTable,int active_row, int current_row )
	{
		if (isValueToCheck(verifyFoundElement)) {
   		 try {
           		if(Action.equals("Update")&&verifyFoundElement.contains("Requirement"))
           		{
           			updateTableValues(Action,Tab,verifyFoundElement, WebTable, active_row, current_row,2);
           		}
           		else if (Action.equals("Update")&&verifyFoundElement.contains("Release"))
           		{
           			updateTableValues(Action,Tab,verifyFoundElement, WebTable, active_row, current_row,3);
           		}
           		else if(Action.equals("Verify")&&verifyFoundElement.contains("Requirement"))
           		{
           			updateTableValues(Action,Tab,verifyFoundElement, WebTable, active_row, current_row,2);
           		}
           		else if(Action.equals("Verify")&&verifyFoundElement.contains("Release"))
           		{
           			updateTableValues(Action,Tab,verifyFoundElement, WebTable, active_row, current_row,3);
           		}
          
           	
           } catch (Exception e) {
           	
           	System.out.println(e);
               System.err.println(" 🚧 Bad Request. Error:AUTOREG107 🚧 ");
           }
       }
	}
	
	public  void updateTableValues(String Action,String Tab,String element_found, WebElement table, int active_row, int current_row, int index) throws InterruptedException, IOException {
        switch (element_found) {
       
            
            case"ADR ORD Hedge Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,3); break;
            case"ADR ORD Hedge Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,4); break;
            case"Call adjustment Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,5); break;
            case"Call adjustment Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,6); break;
            case"Cash Secured Put Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,7); break;
            case"Cash Secured Put Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,8); break;
            case"Collar Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,9); break;
            case"Collar Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,10); break;
            case"Conversion Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,11); break;
            case"Conversion Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,12); break;
            case"Covered Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,13); break;
            case"Covered Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,14); break;
            case"Credit Box Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,15); break;
            case"Credit Box Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,16); break;
            case"Credit Butterfly Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,17); break;
            case"Credit Butterfly Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,18); break;
            case"Credit Iron Butterfly Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,19); break;
            case"Credit Iron Butterfly Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,20); break;
            case"Credit Iron Condor Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,21); break;
            case"Credit Iron Condor Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,22); break;
            case"Day Trading Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,23); break;
            case"Day Trading Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,24); break;
            case"Debit Box Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,25); break;
            case"Debit Box Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,26); break;
            case"Debit Butterfly Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,27); break;
            case"Debit Butterfly Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,28); break;
            case"Debit Condor Spread Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,29); break;
            case"Debit Condor Spread Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,30); break;
            case"Excess applied Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,31); break;
            case"Excess applied Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,32); break;
            case"Excess used Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,33); break;
            case"Excess used Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,34); break;
            case"House Minimum Equity Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,35); break;
            case"House Minimum Equity Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,36); break;
            case"Long Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,37); break;
            case"Long Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,38); break;
            case"Maintenance Minimum Equity Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,39); break;
            case"Maintenance Minimum Equity Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,40); break;
            case"MPSL Spread Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,41); break;
            case"MPSL Spread Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,42); break;
            case"Naked Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,43); break;
            case"Naked Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,44); break;
            case"Negative liquidation value addon Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,45); break;
            case"Negative liquidation value addon Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,46); break;
            case"Round House call Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,47); break;
            case"Round House call Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,48); break;
            case"Short Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,49); break;
            case"Short Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,50); break;
            case"Short vs Box Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,51); break;
            case"Short vs Box Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,52); break;
            case"Short vs Convertible Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,53); break;
            case"Short vs Convertible Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,54); break;
            case"SMA adjust for excess Equity Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,55); break;
            case"SMA adjust for excess Equity Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,56); break;
            case"SMA adjustment Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,57); break;
            case"SMA adjustment Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,58); break;
            case"SMA credit Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,59); break;
            case"SMA credit Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,60); break;
            case"SMA debit Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,61); break;
            case"SMA debit Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,62); break;
            case"Spread Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,63); break;
            case"Spread Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,64); break;
            case"Straddle Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,65); break;
            case"Straddle Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,66); break;
            case"Synthetic Call Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,67); break;
            case"Synthetic Call Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,68); break;
            case"Synthetic Put Release": FinalReqvalue(Action,Tab,table, active_row, current_row, index,69); break;
            case"Synthetic Put Requirement": FinalReqvalue(Action,Tab,table, active_row, current_row, index,70); break;

                
        }
    }
	
	
        
        
	private  void FinalReqvalue(String Action,String Tab,WebElement table, int active_row, int current_row, int index,int xlcol) throws InterruptedException, IOException {
        if(Action.equals("Update"))
        {
        	String verifyField_Name = xlutil.getCellData(Tab, 0, xlcol);
   			
  		   String currentField_value = table.findElement(By.xpath("tr["+active_row+"]/td["+index+"]")).getText();
  		   
  		   xlutil.setCellData(Tab, current_row, xlcol,currentField_value);
  		   
  		   
  		   GetExtentReports.updateValue(verifyField_Name,  currentField_value);
        	
            System.out.println("\tField Name : \t"+ verifyField_Name + "\t updated with value : " + currentField_value );
        }
        else
        {
        	String verifyField_Name = xlutil.getCellData(Tab, 0, xlcol);
   			
 		   String currentField_value = table.findElement(By.xpath("tr["+active_row+"]/td["+index+"]")).getText();
 		   
 		   String verifyExcelValue= xlutil.getCellData(Tab, current_row, xlcol);
 		   
 		   GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue, currentField_value);
        	
        }
       	
	
	
		}
	
	
	

	
	private static boolean isValueToCheck(String verifyFoundElement) {
        return  
        		verifyFoundElement.equals("ADR ORD Hedge Requirement") ||
        		verifyFoundElement.equals("Call adjustment Requirement") ||
        		verifyFoundElement.equals("Cash Secured Put Requirement") ||
        		verifyFoundElement.equals("Collar Requirement") ||
        		verifyFoundElement.equals("Conversion Requirement") ||
        		verifyFoundElement.equals("Covered Requirement") ||
        		verifyFoundElement.equals("Credit Box Requirement") ||
        		verifyFoundElement.equals("Credit Butterfly Requirement") ||
        		verifyFoundElement.equals("Credit Iron Butterfly Requirement") ||
        		verifyFoundElement.equals("Credit Iron Condor Requirement") ||
        		verifyFoundElement.equals("Day Trading Requirement") ||
        		verifyFoundElement.equals("Debit Box Requirement") ||
        		verifyFoundElement.equals("Debit Butterfly Requirement") ||
        		verifyFoundElement.equals("Debit Condor Spread Requirement") ||
        		verifyFoundElement.equals("Excess applied Requirement") ||
        		verifyFoundElement.equals("Excess used Requirement") ||
        		verifyFoundElement.equals("House Minimum Equity Requirement") ||
        		verifyFoundElement.equals("Long Requirement") ||
        		verifyFoundElement.equals("MPSL Spread Requirement") ||
        		verifyFoundElement.equals("Maintenance Minimum Equity Requirement") ||
        		verifyFoundElement.equals("Naked Requirement") ||
        		verifyFoundElement.equals("Negative liquidation value addon Requirement") ||
        		verifyFoundElement.equals("Round House call Requirement") ||
        		verifyFoundElement.equals("SMA adjust for excess Equity Requirement") ||
        		verifyFoundElement.equals("SMA adjustment Requirement") ||
        		verifyFoundElement.equals("SMA credit Requirement") ||
        		verifyFoundElement.equals("SMA debit Requirement") ||
        		verifyFoundElement.equals("Short Requirement") ||
        		verifyFoundElement.equals("Short vs Box Requirement") ||
        		verifyFoundElement.equals("Short vs Convertible Requirement") ||
        		verifyFoundElement.equals("Spread Requirement") ||
        		verifyFoundElement.equals("Straddle Requirement") ||
        		verifyFoundElement.equals("Synthetic Call Requirement") ||
        		verifyFoundElement.equals("Synthetic Put Requirement") ||
        		verifyFoundElement.equals("ADR ORD Hedge Release") ||
        		verifyFoundElement.equals("Call adjustment Release") ||
        		verifyFoundElement.equals("Cash Secured Put Release") ||
        		verifyFoundElement.equals("Collar Release") ||
        		verifyFoundElement.equals("Conversion Release") ||
        		verifyFoundElement.equals("Covered Release") ||
        		verifyFoundElement.equals("Credit Box Release") ||
        		verifyFoundElement.equals("Credit Butterfly Release") ||
        		verifyFoundElement.equals("Credit Iron Butterfly Release") ||
        		verifyFoundElement.equals("Credit Iron Condor Release") ||
        		verifyFoundElement.equals("Day Trading Release") ||
        		verifyFoundElement.equals("Debit Box Release") ||
        		verifyFoundElement.equals("Debit Butterfly Release") ||
        		verifyFoundElement.equals("Debit Condor Spread Release") ||
        		verifyFoundElement.equals("Excess applied Release") ||
        		verifyFoundElement.equals("Excess used Release") ||
        		verifyFoundElement.equals("House Minimum Equity Release") ||
        		verifyFoundElement.equals("Long Release") ||
        		verifyFoundElement.equals("MPSL Spread Release") ||
        		verifyFoundElement.equals("Maintenance Minimum Equity Release") ||
        		verifyFoundElement.equals("Naked Release") ||
        		verifyFoundElement.equals("Negative liquidation value addon Release") ||
        		verifyFoundElement.equals("Round House call Release") ||
        		verifyFoundElement.equals("SMA adjust for excess Equity Release") ||
        		verifyFoundElement.equals("SMA adjustment Release") ||
        		verifyFoundElement.equals("SMA credit Release") ||
        		verifyFoundElement.equals("SMA debit Release") ||
        		verifyFoundElement.equals("Short Release") ||
        		verifyFoundElement.equals("Short vs Box Release") ||
        		verifyFoundElement.equals("Short vs Convertible Release") ||
        		verifyFoundElement.equals("Spread Release") ||
        		verifyFoundElement.equals("Straddle Release") ||
        		verifyFoundElement.equals("Synthetic Call Release") ||
        		verifyFoundElement.equals("Synthetic Put Release");

        
        }

}
