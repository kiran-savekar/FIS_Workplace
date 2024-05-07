package TestDriver;

import MarginInquiryTabs.DayTradeRequirement;
import MarginInquiryTabs.Requirements;
import MarginInquiryTabs.positions;
import MarginInquiryTabs.updateAccountBalances;
import PageClasses.GetExtentReports;
import PageClasses.ScreenObjects;
import VerifyMarginInq.verifyAccountBalances;
import com.aventstack.extentreports.Status;
import exceloperations.excelutility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;


public class Regression {
	public static WebDriver driver;
	public static FileInputStream ip,np;
	public static Properties prop;

	public static final String CONFIG_FILE_PATH = "config.properties";
	public static final String EXCEL_FILES_PATH = ".//src/test/resources/";
	public static final String BASE_FOLDER_PATH = ".//Logs/";

	
	public void basicDetails(String sheet, int value2) throws IOException 
	{
		String xlpath = prop.getProperty("Excel_Path");
		excelutility xlutil=new excelutility(xlpath); 
		 String acc=driver.findElement(By.id("accountId")).getAttribute("value");
		 xlutil.setCellData(sheet, value2, 0, acc);
		 //GetExtentReports.updateValue("Account Alias", acc);
		 //System.out.println("\t Field Name : \tAccount Alias \t updated with value : " + acc);
		 
		 String date=driver.findElement(By.id("date")).getAttribute("value");
		 xlutil.setCellData(sheet, value2, 1, date);
		 //GetExtentReports.updateValue("Date", date);
		// System.out.println("\t Field Name : \tDate \t updated with value : " + date);
		 
		 
		 WebElement regclass=driver.findElement(By.id("accountRegClassId"));
		 Select select = new Select(regclass);
		 
		 String selectedRegClass = select.getFirstSelectedOption().getText();
		 
		 xlutil.setCellData(sheet, value2, 2, selectedRegClass);
		 //GetExtentReports.updateValue("Account Reg Class", selectedRegClass);
		 //System.out.println("\t Field Name : \tAccount Reg Class \t updated with value : " + selectedRegClass);
		 
		//System.out.println("Account Number:-"+ acc+  " Date:-" +date+ " Account_Reg_Class:-"+selectedRegClass);

	}

	@BeforeTest
	public void Setup( ) throws IOException, InterruptedException {
		
		try {
			
	    	//System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			// Load user credentials from config.properties
	        Properties config = ScreenObjects.loadConfigProperties(CONFIG_FILE_PATH);
	 
	        config.remove("Excel_Path");
	        config.remove("Regression_Suite");
	        config.remove("User");
	        config.remove("Logs_Destination");
	        // Ask user to choose a userID
	        String selectedUser = (String) JOptionPane.showInputDialog(null,
	                "Select a user:", "User Selection",
	                JOptionPane.QUESTION_MESSAGE, null,
	                config.keySet().toArray(), null);
	        
	        config.setProperty("User", selectedUser);
	        ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);
	  	  
		     // Get the current password
		             String currentPassword = config.getProperty(selectedUser);
		             JOptionPane.showMessageDialog(null, "Current Password for " + selectedUser + ": " + currentPassword);
		      
		             // Ask if the user wants to change the password
		             int option = JOptionPane.showConfirmDialog(null, "Do you want to change the password?",
		                     "Password Change Confirmation", JOptionPane.YES_NO_OPTION);
		      
		             // If user wants to change the password
		             if (option == JOptionPane.YES_OPTION) {
		                 // Ask for a new password
		                 String newPassword = JOptionPane.showInputDialog("Enter new password:");
		      
		                 // Update password if a new one is provided
		                 if (newPassword != null && !newPassword.isEmpty()) {
		                	 config.setProperty(selectedUser, newPassword);
		                	 ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);
		                     JOptionPane.showMessageDialog(null, "Password updated successfully!");
		                 }
		             }
		             String Password = config.getProperty(selectedUser);
		        

		             
		    	     // Get suite names from config
		 	        String suitesString = config.getProperty("Regression_Names");
		 	        String[] suites = suitesString.split(",");
		 	        // Add "Add New" option to the array of suite names
		 	        String[] suitesWithAddNew = Arrays.copyOf(suites, suites.length + 1);
		 	        suitesWithAddNew[suites.length] = "Add New";
		 	         
		 	        // Ask user to select a suite or add a new one
		 	        String selectedSuite = (String) JOptionPane.showInputDialog(null,
		 	                "Select a suite name or Add New:", "Suite Name Selection",
		 	                JOptionPane.QUESTION_MESSAGE, null,
		 	                suitesWithAddNew, suites[0]);
		 	    
		 	           // If user selects "Add New", prompt for a new suite name
		 	           if (selectedSuite.equals("Add New")) {
		 	               String newSuite = JOptionPane.showInputDialog(null,
		 	                       "Enter the new suite name:", "New Suite",
		 	                       JOptionPane.QUESTION_MESSAGE);
		 	               // Append the new suite name to the existing list
		 	               if (newSuite != null && !newSuite.isEmpty()) {
		 	                   suitesString += "," + newSuite;
		 	                   config.setProperty("Regression_Names", suitesString);
		 	                   config.setProperty("Regression_Suite", newSuite);
		 	                   ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);
		 	                   JOptionPane.showMessageDialog(null, "New suite name has been added successfully.");
		 	               }
		 	           } else {
		 	               // Update config.properties with selected suite
		 	               config.setProperty("Regression_Suite", selectedSuite);
		 	               ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);
		 	               //System.out.println("Selected Suite: " + selectedSuite);
		 	               JOptionPane.showMessageDialog(null, "Suite name has been updated successfully.");
		 	           }
		 	           
		 	           
	        // Ask user to choose an Excel file
	        String selectedExcelFile = ScreenObjects.chooseExcelFile();
	        
	        // Update config.properties with selected Excel file
	        config.setProperty("Excel_Path", EXCEL_FILES_PATH.concat(selectedExcelFile));
	        ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);

	       
	     // Create a folder for this execution
	        String executionFolderPath = ScreenObjects.createExecutionFolder();
	        config.setProperty("Logs_Destination", executionFolderPath);
	        ScreenObjects.saveConfigProperties(config, CONFIG_FILE_PATH);
	        
	        
	        
	        JOptionPane.showMessageDialog(null, "Configuration File has been updated successfully.");
	        
	        
			WebDriverManager.chromedriver().setup();	//Auto fetch chromedriver dependencies, libraries
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeOptions);
			

			
			//Retrieving config.propertis file
			prop = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");	
			prop.load(ip);
		
			
			driver.get("https://localhost:17070/");
	
			driver.manage().window().maximize(); //Maximize window
			
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(70)); //Object created for Wait till 70 Seconds
			 


			 //Waiting for Login FOrm to display
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div/ruf-app-canvas/div/div/login/div/form")));

			

			
				//Pulled User ID from config.properties
				WebElement userID = driver.findElement(By.id("userId")); //Finding UserID 
				System.out.println("User_id found");
				userID.sendKeys(selectedUser);
				
		
				WebElement Pass = driver.findElement(By.id("password")); //Find Password
				System.out.println("Password found");
				Pass.sendKeys(Password);
				
				
				Thread.sleep(3000);
				
				WebElement productType = driver.findElement(By.id("product"));
	            Select productTypeSCM = new Select(productType);
	            productTypeSCM.selectByIndex(0);
				
	            
	            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("product"),"FIS Credit Monitor"));
				

				ScreenObjects objScreenObjects;
				objScreenObjects = new ScreenObjects(driver);
				objScreenObjects.signin(); //SignIn from ScreenObjects.java
				
				
		}//Setup try block
		catch(Exception e)
		{
			System.err.println("🚧 Bad Request. Error:AUTOREG100 🚧");
		}
	}

	
	
	@Test(priority=1)
	public void MarginInquiry() throws InterruptedException, IOException {
		
		try {
			
			//Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(70)); //Object created for Wait till 70 Seconds
   	 

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("scm-dropdown"))).getText().contains("Today's");
        
		
		
		ScreenObjects.baseInfo();
        
      /*wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/app-header-menubar/ruf-menubar/div/a[2]/ruf-menu-label/div/div"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div[2]/div/div/div/button[1]"))).click();
        String qa_user=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div/ruf-app-canvas/div/div[2]/profile/div/div/div/div/span"))).getText();
        System.out.println("USer: "+qa_user);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/app-header-menubar/ruf-menubar/div/a[3]/ruf-menu-label/div/div"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div[2]/div/div/div/button[2]"))).click();
       
        String build_number= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div/ruf-app-canvas/app-about/div/div/div/div/span"))).getText();
        System.out.println("Build: "+build_number);
        String product=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/ruf-banner-brand/h1"))).getText();
        System.out.println("Product: "+product);
        
        
		    
        GetExtentReports.initializeExtentReport(product,qa_user,build_number);*/
        
       //System.out.println("Kiki");
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath()));
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    //System.out.println("\n Started regression on "+ timestamp); 
		//Thread.sleep(3000);
        
        ScreenObjects objScreenObjects;
		objScreenObjects = new ScreenObjects(driver);
		objScreenObjects.MarginInquiry();
		
		String xlpath = prop.getProperty("Excel_Path");
		excelutility xlutil=new excelutility(xlpath); 
		System.out.println(xlpath);

		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)//Window Handle For Loop
			{
				if(!windowHandle.equals(parentWindow)) //Window Handle If 
					{
						
						int ccc = xlutil.getRowCount("Baseline");
						System.out.println("\nTotal accounts:- "+ccc);
						
						int baselineCols=xlutil.getCellCount("Baseline", ccc);
						System.out.println("\nTotal columns:- "+baselineCols);
					
						driver.switchTo().window(windowHandle).manage().window().maximize();
						System.out.println("\n ***** Screen :: Margin Inquiry ***** \n");
						//Thread.sleep(3000);
						
							try {
								//ScreenObjects.setZoomLevel(driver, 0.67);
			
								//<!--Performing operation for new window i.e. Margin Inquiry-->
								for (int i=1;i<=ccc;i++)//Excel Row For Loop
										{
									ScreenObjects.showPrompt("Account "+i+" out of "+ccc , 2000); // Prompt for 2 seconds
									
									
							   
							        
							        
											for (int j=0;j<=baselineCols;j++) //Excel Column For Loop
												{
													String val = xlutil.getCellData("Baseline", i, j);
													
													//Thread.sleep(1000);
													if(val.equals("0"))
													{
														break;
													}
												
															switch(j) {
														
															case 3: //Account Number
																																		        
																//Thread.sleep(1000);
																WebElement account_alias= driver.findElement(By.id("accountId"));
																System.out.print("Retriving account "+val+" details ");
																account_alias.sendKeys(val);
																break;
															
															case 4:	//Date
																//Thread.sleep(1000);
																driver.findElement(By.id("date")).clear();
																//Thread.sleep(1000);
																/*WebElement acc_date=driver.findElement(By.id("date")).clear();*/
																System.out.print("  on date "+val+"\n");
																driver.findElement(By.id("date")).sendKeys(val);
																break;
																
															case 5:	//Reg Class
																//Thread.sleep(5000);
																WebElement regclas = driver.findElement(By.id("accountRegClassId"));//To select from dropdown
																Select acregcls = new Select(regclas);	//Create a dropdown select object
																acregcls.selectByVisibleText(val);	//Select a option from dropdown
																//Thread.sleep(2000);
																
																//driver.findElement(By.id("RETRIEVE")).click(); 
																wait.until(ExpectedConditions.elementToBeClickable(By.id("RETRIEVE"))).click();
																//Thread.sleep(2000);
																
																String account_id=driver.findElement(By.xpath("//*[@id=\"accountId\"]")).getAttribute("value");
																String eoddate=driver.findElement(By.xpath("//*[@id=\"date\"]")).getAttribute("value");
																String sfpcm_tag=xlutil.getCellData("Baseline", i, 1);
																String short_desc = xlutil.getCellData("Baseline", i, 2);
																GetExtentReports.createTest(account_id, eoddate,short_desc,sfpcm_tag);
																 
																break;
															
															case 6: //Update/Verify Account Balances Tab
																//Thread.sleep(5000);
																
																
																
																	updateAccountBalances objUAB;
																	objUAB = new updateAccountBalances(driver);
																	
																	
																	verifyAccountBalances objVAB;
																	objVAB = new verifyAccountBalances(driver);
																	
																			try {
																				if(val.equals("X"))
																				{
																					GetExtentReports.test.info("Skipped -->> Tab : Account Balances");
																					//GetExtentReports.test.log(Status.SKIP, "Action not preformed");
																					
																					break;
																				}
																				else if(val.equals("U"))
																				{
																					
																					System.out.println("\n\n\n\t\t Tab :: Account Balances");
																					
																					//Thread.sleep(2000);
																					objUAB.updateAB(i);
																					/*i=value;
																					System.out.println(value);
																					updateAccountBalances objUAB = new updateAccountBalances();
																					objUAB.updateAB(i);*/
																					String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[1]")).getText();
																					xlutil.setCellData("Status", i, j-2, status);
																			        GetExtentReports.updateStatus("Account Balances", status);
																					
																				}
																					
																					
																				else if(val.equals("V"))
																				{
																					System.out.println("\n\n\n\t\t Tab :: Account Balances");
																					//Thread.sleep(2000);
																					objVAB.verifyAB(i);
																					
																					String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[1]")).getText();
																			        String excelStatus=xlutil.getCellData("Status", i, j-2);
																			        GetExtentReports.verifyStatus("Account Balances", excelStatus,status);
																				}
																				
																			
																			}//Update/Verify Account Balances Tab Try block
																			catch(Exception e)
																			{
																				System.err.println(e);
																			}
																			
																	break;
																
																case 7: case 12: //Update/Verify Positions Tab
																	//Thread.sleep(5000);
																	
																	positions objPos;
																	objPos = new positions(driver);	
																	
																	String Pos_Comb_Action_Tab;
																	int row;
																	
																	if(j==7)
																	{
																		Pos_Comb_Action_Tab="Positions";
																		row=4;
																	}
																	else
																	{
																		Pos_Comb_Action_Tab="Combined Equity";
																		row=3;
																	}
																	
																															
																	
																	try {
																		if(val.equals("X"))
																		{
																			//System.out.println("Action = X ");
																			GetExtentReports.test.info("Skipped -->> Tab : Positions");
																			//GetExtentReports.test.log(Status.SKIP, "Action not preformed");
																			break;
																		}
																		else if(val.equals("U"))
																		{
																			String Action="Update";
																			System.out.println("\n\n\n\t\t Tab :: "+Pos_Comb_Action_Tab);
																			//Thread.sleep(2000);
																			objPos.combPos(i, Pos_Comb_Action_Tab,Action,row);
																			
																			String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[1]")).getText();
																			xlutil.setCellData("Status", i, j-2, status);
																	        GetExtentReports.updateStatus(Pos_Comb_Action_Tab, status);
																		}
																			
																			
																		else if(val.equals("V"))
																		{
																			String Action="Verify";
																			System.out.println("\n\n\n\t\t Tab :: "+Pos_Comb_Action_Tab);
																			//Thread.sleep(2000);
																			objPos.combPos(i, Pos_Comb_Action_Tab,Action,row);
																			
																			String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[1]")).getText();
																	        String excelStatus=xlutil.getCellData("Status", i, j-2);
																	        GetExtentReports.verifyStatus(Pos_Comb_Action_Tab, excelStatus,status);
																		}
																	
																	
																	
																	}
																	catch(Exception e)
																	{
																		System.err.println(e);
																	}
																	
																	break;
																case 8: //Update/Verify Day Trade Req Tab
																	//Thread.sleep(5000);
																	DayTradeRequirement objDTReq;
																	objDTReq = new DayTradeRequirement(driver);															
																	//System.out.println("Inside Case 6");
																	
																	/*verifyDayTradeReq objVDTReq;
																	objVDTReq = new verifyDayTradeReq(driver);*/
																			try {
																				if(val.equals("X"))
																				{
																					//System.out.println("Action = X ");
																					GetExtentReports.test.info("Skipped -->> Tab : Day Trade Requirements");
																					//GetExtentReports.test.log(Status.SKIP, "Action not preformed");
																					break;
																				}
																				else if(val.equals("U"))
																				{
									
		
																					WebElement DT_Tab=driver.findElement(By.id("dayTradeLink"));
																					if(DT_Tab!=null)
																					{String Action="Update";
																						//System.out.println("Inside updating the DT Req");
																						System.out.println("\n\n\t\t Tab :: Day Trade Requirements");
																						Thread.sleep(2000);
																						objDTReq.DTRequirement(i,Action);
																						
																						try {
		
																							
																								String status = driver.findElement(By.xpath("/html/body/form/div[14]/div[4]/div[2]/table/tbody/tr[1]/td/font")).getText();
																								
																								xlutil.setCellData("Status", i, j-2, status);
																						        GetExtentReports.updateStatus("Day Trade Requirements", status);
																							
																						}
																						catch (Exception e)
																						{
																							String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[4]/span/font")).getText();
																							//System.out.println("When DT Req is not available"+status);
																							xlutil.setCellData("Status", i, j-2, status);
																					        GetExtentReports.updateStatus("Day Trade Requirements", status);
																						}
																						
																						
																						
																					}
																					
																				}
																					
																					
																				else if(val.equals("V"))
																				{
																					System.out.println("\n\n\n\t\t Tab :: Day Trade Requirements");
																					//Thread.sleep(2000);
																					//objUDTReq.updateDTReq(i);
																					
																					WebElement DT_Tab=driver.findElement(By.id("dayTradeLink"));
																					if(DT_Tab!=null)
																					{
																						String Action="Verify";
																						//System.out.println("Inside verifying the DT Req");
																						//System.out.println("\n\n\t\t Tab :: Day Trade Requirements");
																						Thread.sleep(2000);
																						objDTReq.DTRequirement(i,Action);
																						
																		
																						try {
		
																							
																							String status = driver.findElement(By.xpath("/html/body/form/div[14]/div[4]/div[2]/table/tbody/tr[1]/td/font")).getText();
																							 String excelStatus=xlutil.getCellData("Status", i, j-2);
																						        GetExtentReports.verifyStatus("DayTradeRequirements", excelStatus,status);
																							}
																						catch (Exception e)
																						{
																							String status=driver.findElement(By.xpath("/html/body/form/div[14]/div[4]/span/font")).getText();
																							System.out.println("When DT Req is not available"+status);
																							String excelStatus=xlutil.getCellData("Status", i, j-2);
																					        GetExtentReports.verifyStatus("DayTradeRequirements", excelStatus,status);
																						}
																				       
																					}
																				
																				}
																			}
																			
																			catch(Exception e)
																			{
																				System.err.println("Tab :: Day Trade Requirements not present");
																				GetExtentReports.test.log(Status.WARNING, "Tab :: Day Trade Requirements not present ");
																				System.out.println(e);
																			}
																	
																	break;
																	
																	
																	
																case 9: case 10: case 11:  //Update/Verify Day Trade Req Tab
																	//Thread.sleep(5000);
																	Requirements objReq;
																	objReq = new Requirements(driver);	
																	
																	//String Tab=null;
																	
																		
																	
																	try {
																		String Req_Action_Tab;
																		if(j == 9)
																		{
																			Req_Action_Tab="SMA Activity";
																		}
																		else if(j==10)
																		{
																			Req_Action_Tab="Maintenance Requirement";
																		}
																		else
																		{
																			Req_Action_Tab="House Requirement";
																		}
																		
																		if(val.equals("X"))
																		{
																			//System.out.println("Action = X ");
																			GetExtentReports.test.info("Skipped -->> Tab : "+Req_Action_Tab);
																			//GetExtentReports.test.log(Status.SKIP, "Action not preformed");
																			break;
																		}
																		else if(val.equals("U"))
																		{
							
																			String Action="Update";
																			//WebElement SMA_Tab=driver.findElement(By.id("initialLink"));
																			if(Req_Action_Tab!=null)
																			{
																				//System.out.println("Inside updating the "+Action_Tab);
																				System.out.println("\n\n\t\t Tab :: "+Req_Action_Tab);
																				Thread.sleep(2000);
																				objReq.Requirement(i,Req_Action_Tab,Action);
																				
																				String status=driver.findElement(By.xpath("/html/body/form/div[14]/div")).getText();
																				xlutil.setCellData("Status", i, j-2, status);
																		        GetExtentReports.updateStatus(Req_Action_Tab, status);
																		        
																		        
																			}
																			
																		}
																			
																			
																		else if(val.equals("V"))
																		{
																			//System.out.println("\n\n\n\t\t Tab :: SMA Activity");
																			//Thread.sleep(2000);
																			//objUDTReq.updateDTReq(i);
																			String Action="Verify";
																			//WebElement SMA_Tab=driver.findElement(By.id("initialLink"));
																			if(Req_Action_Tab!=null)
																			{
																				//System.out.println("Inside verifying the "+Action_Tab);
																				System.out.println("\n\n\t\t Tab :: "+Req_Action_Tab);
																				Thread.sleep(2000);
																				objReq.Requirement(i,Req_Action_Tab,Action);
																				
																				String status=driver.findElement(By.xpath("/html/body/form/div[14]/div")).getText();
																		        String excelStatus=xlutil.getCellData("Status", i, j-2);
																		        GetExtentReports.verifyStatus(Req_Action_Tab, excelStatus,status);
																			}
																		
																		}
																	
																	
																	
																	}
																	catch(Exception e)
													
																	{
																		System.err.println("Tab not present");
																		GetExtentReports.test.log(Status.WARNING, "Tab not present");
																	
																		System.out.println(e);
																	}
																	
																	break;
																	
																
																
															}//Sheet=Basline Switch case
													
													} //Excel Column For Loop
											String sheet = "Status";//Status Tab
											Regression objRegression;
											objRegression = new Regression();
											objRegression.basicDetails(sheet,i);
												
												
												//Clicking on Clear button to clear Account, Date, Reg Class values for new entry 
												driver.findElement(By.xpath("/html/body/form/div[6]/div/table/tbody/tr[3]/td[5]/span[3]/input")).click();
												//Thread.sleep(2000);
												
										}//Excel Row For Loop
								
									}//Newly opened margin inquiry perform actions try block 	
								catch(Exception e)
								{
									System.out.println("Here's the root cause :-");
									e.printStackTrace();
									System.err.println("🚧 Bad Request. Error:AUTOREG102 🚧");
									
								}
						//Closing Margin Inquiry Screen [Child Window]
						driver.close(); 
						Thread.sleep(1000);
						//After closing child window, access the Main Screen
						driver.switchTo().window(parentWindow);
						
						
					}//Window Handle If
				
			}//Window Handle For Loop
		}//Margin Inquiry Opening procedure try block
		catch(Exception e)
		{
			System.out.println(e);
			System.err.println("🚧 Bad Request. Error:AUTOREG101 🚧");
		}
	}//MarginInquiry()

	

 
    
    
//ITestResult result
	@AfterTest
	public void teardown() throws InterruptedException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("\n Regression ended on "+ timestamp); 
		
		
		Thread.sleep(1000);
		ScreenObjects objScreenObjects;
		objScreenObjects = new ScreenObjects(driver);
		objScreenObjects.logout();
		System.err.println("Logged Out");
		Thread.sleep(1000);
		System.out.println("Browser Closed....");
		driver.quit();
		
		/*if(result.getStatus() == ITestResult.SKIP)
		{
			GetExtentReports.test.pass("Test Skipped Intentionally");
		}*/
		
		GetExtentReports.closeExtentReport();
		ScreenObjects.showPrompt("Regression successful......\n Have a Good Day :) " , 2000);
		//JOptionPane.showMessageDialog(null, "Regression successful!");
		
	}
}

	


	

	





