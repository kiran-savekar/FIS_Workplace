package MarginInquiryTabs;


import PageClasses.GetExtentReports;
import TestDriver.Regression;
import exceloperations.excelutility;
import java.util.List;
import java.io.*;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CombinePositions extends Regression{
	
	WebDriver driver;
		
	public CombinePositions (WebDriver driver)
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
			objRegression.basicDetails(sheet,value2); 
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
								//GetExtentReports.sendMsg("Tab : Positions");
								//System.out.println("\n Updating the Positions Information.\n");
							
								GetExtentReports.positionImage(driver);
								GetExtentReports.sendMsg("Tab : "+Tab+" \n "+Action+" the "+Tab+" Information.");
					            System.out.println("\n "+Action+" "+Tab+" Information.\n");
					            
					           /* // Iterate through each row
					            for (int i = 0; i < rows.size(); i++) {
					                // Create a new row in the Excel sheet
					                Row row = sheet.createRow(i);
					 
					                // Get all cells of the current row
					                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
					 
					                // Create a unique key by concatenating cell values
					                StringBuilder keyBuilder = new StringBuilder();
					                for (WebElement cell : cells) {
					                    keyBuilder.append(cell.getText());
					                }
					                String key = keyBuilder.toString();
					 
					                // Add the key to the first column of the Excel sheet
					                row.createCell(0).setCellValue(key);
					 
					                // Add cell values to the remaining columns
					                for (int j = 0; j < cells.size(); j++) {
					                    row.createCell(j + 1).setCellValue(cells.get(j).getText());
					                }
					            }*/
								
								for(int tr=row;tr<=comb_pos_table_rows-1;tr++)
								{
									
									
									//System.out.println("Row "+tr + " Col"+ pos_rows); 
									//Thread.sleep(1000);
									//String valnol=pos_table.findElement(By.xpath("tr["+tr+"]/td")).getText();
											try
											{	
												StringBuilder keyBuilder = new StringBuilder();
												for(WebElement cell : comb_pos_table.findElements(By.xpath("tr["+tr+"]/td")))
												{
													
													//String celltext = cell.getText();
													keyBuilder.append(cell.getText());
													
												}
												String key = keyBuilder.toString();
								                System.out.println("Created key as:- " +key);
								                xlutil.setCellData(sheet, value2, 3, key);
												//List<WebElement> cells = comb_pos_table.
								                
								                
								                Thread.sleep(3000);
								                System.out.println(" ");
												for(int cl=2;cl<=16;cl++)
												{
													System.out.println(" Inside Column for loop");
													int x = tr-4;
											        int y = cl;
											        int xlcol = x + (y * 1) + ((x-1) * 14);
													String header = xlutil.getCellData(sheet, 0, cl+1);
													String valnol=comb_pos_table.findElement(By.xpath("tr["+tr+"]/td["+cl+"]")).getText();
													
													//Thread.sleep(1000);
													
													if(Action.equals("Update"))
													{
														xlutil.setCellData(sheet, value2, xlcol, valnol);
												        GetExtentReports.updateValue(header, valnol);
												        System.out.println("\tField Name : \t"+ header + "\t updated with value : " + valnol);
												        
													}
													else 
													{
														String verifyField_Name = xlutil.getCellData(sheet, 0, cl+1);
														
														String  currentField_value=comb_pos_table.findElement(By.xpath("tr["+tr+"]/td["+cl+"]")).getText();
														//System.out.println("Web Value:- "+currentField_value);
														
														//String tbval=tlval.getText();
														//Thread.sleep(3000);
														//System.out.println("\tField Name : \t"+ header + "\t updated with value : " + tbval);
														//xlutil.setCellData("Positions", rc, cl+1, tbval);
														String verifyExcelValue=xlutil.getCellData(sheet, value2, xlcol);
														//System.out.println("Excel value:- "+verifyExcelValue);
														
														GetExtentReports.verifyValue(verifyField_Name, verifyExcelValue, currentField_value);
														//System.out.println("Congrats, value matched.");
													}
													
											        
												}
												
											}//Positions values retriving try block
											catch(Exception e)
											{
												//System.err.println("🚧 Bad Request. Error:AUTOREG109 🚧");
												
											}
										
									}//For loop values retriving
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
	
}//Main updatePositions.java

	
	


	

	





