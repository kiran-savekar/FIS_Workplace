package MarginInquiryTabs;

import java.io.IOException;
import java.time.Duration;

//import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageClasses.GetExtentReports;
import TestDriver.Regression;
import exceloperations.excelutility;
import java.util.List;
import java.util.ArrayList;

public class positions extends Regression{

	
	WebDriver driver;
	
	public positions (WebDriver driver)
	{
		this.driver=driver;
	}

	String xlpath = prop.getProperty("Excel_Path");
	excelutility xlutil=new excelutility(xlpath); 
	

	/*String xlpath = ".\\svalue2\\test\\resouvalue2es\\Call_Abetment_Suite.xlsx";
	excelutility xlutil=new excelutility(xlpath); */


	public void combPos(int value2, String Tab,String Action, int row) throws IOException, InterruptedException {
			String sheet = Tab;//Positions
			String table_XPATH = null;
			Regression objRegression;
			objRegression = new Regression();
			//objRegression.basicDetails(sheet,value2); 
		//Thread.sleep(1000);
		//To click on Positions tabs for Margin / Cash Reg class
			
			///html/body/form/div[14]/div[1]/span/font
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
			
			if(Tab.equals("Positions"))
			{
				table_XPATH="/html/body/form/div[14]/div[2]/fieldset/table/tbody/tr[2]/td/div/table/tbody";
				try
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("posLink"))).click();
				}
				catch(Exception e)
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cashPosLink"))).click();
				}
				
			}
			else if(Tab.equals("Combined Equity"))
			{
				table_XPATH="/html/body/form/div[14]/div[2]/fieldset/table/tbody/tr[2]/td/div[2]/table/tbody";
				
				//
				try
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("combineposLink"))).click();
				}
				catch(Exception e)
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("combinecashPosLink"))).click();
				}
				
			}
			
			
			if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[1]/span/font"))).getText().contains(" Complete."))
			{
				try 
				{
					
					
					 
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[14]/div[2]")));	
					
						//Thread.sleep(1000);
																			
						//String table_XPATH=String path;
						
						WebElement comb_pos_table=driver.findElement(By.xpath(table_XPATH));
						int comb_pos_table_rows=comb_pos_table.findElements(By.xpath("tr")).size();
						System.out.println("In "+Tab+", we have total " + comb_pos_table_rows + " rows.");
						if(comb_pos_table_rows==3)
						{
							String err1_updateAB=driver.findElement(By.xpath("/html/body/form/div[14]/div[1]")).getText();
							System.out.println(err1_updateAB);
							GetExtentReports.sendMsg(err1_updateAB);
						}
						else {
								
								//Taking screenshot for reference
								//GetExtentReports.positionImage(driver);
								GetExtentReports.sendMsg("Tab : "+Tab+" \n "+Action+" the "+Tab+" Information.");
					            System.out.println("\n "+Action+" "+Tab+" Information.\n");
					            
					         
					          
					          
					            
					            
					            // Get total number of rows in the table
					            List<WebElement> rows = comb_pos_table.findElements(By.tagName("tr"));
					            //System.out.println(rows);
					            int totalRows = rows.size();
					           
					            	if(Action.equals("Update"))
							            {
							            	
							            	  int active_row=  xlutil.nextRow(sheet);
									            //System.out.println("Next available row in sheet:- "+active_row);
									            objRegression.basicDetails(sheet,active_row); 
									            
							            	 if(value2==1)
									            {
									            	
									                // Write headers for one time
									               
									                    // Get the headers
									                    WebElement headerRow = rows.get(0);
									                    List<WebElement> headers = headerRow.findElements(By.tagName("th"));
									    
									     
									                    // Iterate through each header and write to Excel
									                    int colNum = 4;
									                    for (WebElement header : headers) {
									                        String headerText = header.getText();
									                    
									                        
									                        xlutil.setCellData(sheet, 0, colNum, headerText);
										                   // System.out.println("Header "+colNum+"  "+headerText);
										                    
										                    
									                        colNum++;
									                    }
									            	
									            }
									            
							            	 	/********Iterating until last row in webtable*********/
							            	 
									            // Iterate through each row starting from row 3 to totalRows - 1
									            for (int i = 2; i < totalRows - 1; i++) 
										            {
									            	
												                WebElement Row = rows.get(i);
												                List<WebElement> cells = Row.findElements(By.tagName("td"));
												     
												                // Skip rows with less than 16 columns
												                if (cells.size() < 16) {
												                	String location=cells.get(0).getText();
												                	xlutil.setCellData(sheet, active_row, 4, location);
												                	System.out.println("\tField Name : Location updated with value : " + location);
												                    
												                    continue;
												                }
										                
												                /********Creating a unique key using account no, date, row cells*********/
												               // Create a unique key by concatenating cell values
												                StringBuilder keyBuilder = new StringBuilder();
												                String acc=driver.findElement(By.id("accountId")).getAttribute("value");
												                keyBuilder.append(acc);
											                	String date=driver.findElement(By.id("date")).getAttribute("value");
											                	keyBuilder.append(date);
												                for (WebElement cell : cells) {
												                	
												                	keyBuilder.append(cell.getText());
												                }
												                String key = keyBuilder.toString();
												                //System.out.println("Key created as:- "+key);
												                xlutil.setCellData(sheet, active_row, 3, key);
										                
												                /********Writing webtable data into excel*********/
												                // Extract and write row data to Excel starting from Column 2 (index 1) to Column 16 (index 15)
												                int cellnum = 5;
												                for (int j = 1; j <= 15; j++) 
												                {
												                	
												                	
												                	String header=xlutil.getCellData(sheet, 0, cellnum);
												                	String cellText = cells.get(j).getText();
					
												                    // Set the cell value in the Excel sheet
												                	
												                    xlutil.setCellData(sheet, active_row, cellnum, cellText);
												                    GetExtentReports.updateValue(header, cellText);
												                    System.out.println("\tField Name : \t"+ header + "\t updated with value : " + cellText);
												                    //System.out.println(cellText);
												                    cellnum++;
												                    
												                }
										                active_row++;
										                
										            }
							            	
							            	
							            }
					            
					            else //Verify
					            {

					            	
					            	 /* int active_row=  xlutil.nextRow(sheet);
							            System.out.println("Next available row in sheet:- "+active_row);
							            objRegression.basicDetails(sheet,active_row); 
							            
					            	 if(value2==1)
							            {
							            	
							                // Write headers for one time
							               
							                    // Get the headers
							                    WebElement headerRow = rows.get(0);
							                    List<WebElement> headers = headerRow.findElements(By.tagName("th"));
							    
							     
							                    // Iterate through each header and write to Excel
							                    int colNum = 4;
							                    for (WebElement header : headers) {
							                        String headerText = header.getText();
							                    
							                        
							                        xlutil.setCellData(sheet, 0, colNum, headerText);
								                   // System.out.println("Header "+colNum+"  "+headerText);
								                    
								                    
							                        colNum++;
							                    }
							            	
							            }*/
							            
					            	 	/********Iterating until last row in webtable*********/
					            	 
							            // Iterate through each row starting from row 3 to totalRows - 1
							            for (int i = 2; i < totalRows - 1; i++) 
								            {
							            	
										                WebElement Row = rows.get(i);
										                List<WebElement> cells = Row.findElements(By.tagName("td"));
										     
										                // Skip rows with less than 16 columns
										                if (cells.size() < 16) {
										                	
										                    continue;
										                }
								                
										                /********Creating a unique key using account no, date, row cells*********/
										               // Create a unique key by concatenating cell values
										                StringBuilder keyBuilder = new StringBuilder();
										                String acc=driver.findElement(By.id("accountId")).getAttribute("value");
										                keyBuilder.append(acc);
									                	String date=driver.findElement(By.id("date")).getAttribute("value");
									                	keyBuilder.append(date);
										                for (WebElement cell : cells) {
										                	
										                	keyBuilder.append(cell.getText());
										                }
										                String webtable_key = keyBuilder.toString();
										                //System.out.println("Key created as:- "+webtable_key);
										                //xlutil.setCellData(sheet, active_row, 3, key);
								                
										                /********Writing webtable data into excel*********/
										                // Extract and write row data to Excel starting from Column 2 (index 1) to Column 16 (index 15)
										                int total_excel_rows=xlutil.getRowCount(sheet);
										                for(int excel_Row=1;excel_Row<=total_excel_rows;excel_Row++)
										                {
										                	String excel_key=xlutil.getCellData(sheet, excel_Row, 3);
										                	if(webtable_key.equals(excel_key))
										                	{
										                		//int verifyRow = excel_Row;
										                		 int cellnum = 5;
													                for (int j = 1; j <= 15; j++) //Taking elements from 2-16
													                {
													                	
													                	
													                	String header=xlutil.getCellData(sheet, 0, cellnum);
													                	String excel_value = xlutil.getCellData(sheet, excel_Row, cellnum);
													                	String web_value = cells.get(j).getText();
													                	System.out.println("Key found at index:- "+excel_Row);
													                	GetExtentReports.verifyValue(header, excel_value, web_value);
													                	
						
													                    //GetExtentReports.updateValue(header, cellText);
													                    //System.out.println("\tField Name : \t"+ header + "\t updated with value : " + cellText);
													                    //System.out.println(cellText);
													                    cellnum++;
													                    
													                }
										                	}
										                }
										                
												               
								                
								                
								            }
					            	
					            }
					           
					           
							
									GetExtentReports.sendMsg(Action+" "+Tab+" Successful.");
									System.out.println("\n "+Action+" "+Tab+" Successful.\n");
										
							}//Else{} for retriving position values if exists
				
						}//try block updatePos
				catch(Exception e)
				{
						System.out.println("Error -->> "+ e);
				}
			}
			else
			{
		        try 
		        {    
	                String err1_Pos = driver.findElement(By.xpath("/html/body/form/div[14]/div")).getText();
	                System.err.println(err1_Pos);
	                GetExtentReports.sendMsg(err1_Pos);
	            } 
		        catch (Exception e) 
		        {
		            String err2_Pos = driver.findElement(By.xpath("//*[@id=\"advEdit\"]")).getText();
		            System.out.println(err2_Pos);
		            GetExtentReports.failMsg(err2_Pos, driver);
	            }
				
			}
		
		
		
		
		
	}//updatePos()


}
