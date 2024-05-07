package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ScreenObjects {
	
	 public static WebDriver driver;
	 public static JavascriptExecutor js;
	 public static FileInputStream ip;

	public static final String CONFIG_FILE_PATH = "config.properties";
	public static final String EXCEL_FILES_PATH = ".//src/test/resources/";
	public static final String BASE_FOLDER_PATH = ".//Logs/";
	public static Properties config = loadConfigProperties(CONFIG_FILE_PATH);
	public static Properties prop;



	By userID = By.id("userId");
	By password = By.id("password");
	By product = By.id("product");
	By signin = By.xpath("/html/body/app-root/div/ruf-app-canvas/div/div/login/div/form/div[7]/button/span[1]");
	By megamenu = By.xpath("//mat-icon[@class='mat-icon notranslate fisfont fis-icon-menu mat-icon-no-color']");
	By setupTab = By.xpath("//ruf-sidemenu-item[contains(text(), \"SETUP\")]");
	By actionsTab = By.xpath("//ruf-sidemenu-item[contains(text(), \"ACTIONS\")]");
	By Tab = By.xpath("//ruf-sidemenu-item[contains(text(),'"+megamenu+"')]");
	//By.xpath("(//div[@class = \"ruf-menu-label-trigger mat-menu-trigger ng-star-inserted\"])[1]");
//"<div aria-haspopup=\"true\" class=\"ruf-menu-label-trigger mat-menu-trigger ng-star-inserted\"></div>"
	By userProfile = By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/app-header-menubar/ruf-menubar/div/a[2]/ruf-menu-label/div/div");
	By signOut = By.xpath("//span[text() = \"Sign Out\"]");
	By Ok = By.xpath("//button[text()=\"Ok\"]");
	
	public static By product_name= By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/ruf-banner-brand/h1");
	public static 	By user_profile= By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/app-header-menubar/ruf-menubar/div/a[2]/ruf-menu-label/div/div");
	public static By user_profile_1=By.xpath("/html/body/div[17]/div[2]/div/div/div/button[1]/span");
	public static By user_profile_final=By.xpath("/html/body/app-root/div/ruf-app-canvas/div/div[2]/profile/div/div/div/div/span");
	public static By close_user_profile = By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-banner-row/ruf-navbar/ruf-toolbar/div/ruf-toolbar-row/ruf-dynamic-menubar/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]/div[1]/ruf-menu-label/div/ruf-labeled-icon/span/span[2]/button/span/mat-icon");
	public static By close_build = By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-banner-row/ruf-navbar/ruf-toolbar/div/ruf-toolbar-row/ruf-dynamic-menubar/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]/div[1]/ruf-menu-label/div/ruf-labeled-icon/span/span[2]/button/span/mat-icon");
	public static By help = By.xpath("/html/body/app-root/div/ruf-banner/header/ruf-toolbar/div/ruf-toolbar-row/app-header-menubar/ruf-menubar/div/a[3]/ruf-menu-label/div/div");
	public static By about_fis=By.xpath("/html/body/div[17]/div[2]/div/div/div/button[2]/span");
	public static By build= By.xpath("/html/body/app-root/div/ruf-app-canvas/app-about/div/div/div/div/span");
	
	By marinq = By.xpath("//ruf-sitemap/div/div[1]/ul/li[15]/a");




	@SuppressWarnings("static-access")
	public ScreenObjects (WebDriver driver)
	{
		this.driver=driver;
	}
	
	
    public static void setZoomLevel(WebDriver driver, double zoomLevel) {
    	((JavascriptExecutor) driver).executeScript("document.body.style.zoom='" + zoomLevel + "'");
    }
    
    public static String createExecutionFolder() {
        // Create folder name based on suite_name , current timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh mm a");
        String folderName = config.getProperty("Regression_Suite")+ dateFormat.format(new Date());
 
        // Create folder under the base folder path
        
        
        
        String fullPath = BASE_FOLDER_PATH + folderName;
        File folder = new File(fullPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
 
        return folder.getAbsolutePath();
    }

    public static Properties loadConfigProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
 
    public static void saveConfigProperties(Properties properties, String filePath) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static String chooseExcelFile() {
        File[] excelFiles = new File(EXCEL_FILES_PATH).listFiles((dir, name) -> name.endsWith(".xlsx"));
        
        if (excelFiles != null && excelFiles.length > 0) {
            String[] fileNames = new String[excelFiles.length];
            for (int i = 0; i < excelFiles.length; i++) {
                fileNames[i] = excelFiles[i].getName();
            }
 
            return (String) JOptionPane.showInputDialog(null,
                    "Select an Excel file:", "Excel File Selection",
                    JOptionPane.QUESTION_MESSAGE, null,
                    fileNames, null);
        } else {
            JOptionPane.showMessageDialog(null, "No Excel files found in the specified directory.");
            return null;
        }
    }
	
    public static void showPrompt(String message, int duration) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
       // final JOptionPane messagePane = new JOptionPane();
        final JDialog dialog = pane.createDialog(null, "Prompt");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
 
        Timer timer = new Timer(duration, e -> {
            dialog.dispose(); // Close the dialog
        });
        timer.setRepeats(false);
        timer.start();
 
        dialog.setVisible(true);
    }
	
	public void signin() 
	{

	       
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(signin));
		
	}
	

	public void clickOnMegaMenu()
	{
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(megamenu));
	}
	
	public void clickOnTab(String menu)
	{
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(Tab));
	}
	
	public static  void baseInfo()throws InterruptedException
	{
		
		
		js=(JavascriptExecutor)driver;
		
		String product_scm=driver.findElement(product_name).getText();
		//Thread.sleep(1500);
		System.out.println("Product :-"+product_scm);
		js.executeScript("arguments[0].click()", driver.findElement(user_profile));
		Thread.sleep(1500);
		js.executeScript("arguments[0].click()", driver.findElement(user_profile_1));
		Thread.sleep(1500);
		String qa_user=driver.findElement(user_profile_final).getText();
		//Thread.sleep(1500);
		System.out.println("User :-"+qa_user);
		js.executeScript("arguments[0].click()", driver.findElement(close_user_profile));
		Thread.sleep(8000);
		js.executeScript("arguments[0].click()", driver.findElement(help));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click()", driver.findElement(about_fis));
		Thread.sleep(5000);
		String build_number=driver.findElement(build).getText();
		Thread.sleep(1500);
		System.out.println("Build :-"+build_number);
		js.executeScript("arguments[0].click()", driver.findElement(close_build));
	    
		
		
		
		GetExtentReports.initializeExtentReport(product_scm,qa_user,build_number);
	}
	

	public void clickOnSetupTab()
	{
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(setupTab));
	}
	
	public void clickOnActionTab()
	{
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(actionsTab));
	}

	public void MarginInquiry() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", driver.findElement(megamenu));
		Thread.sleep(1500);
		js.executeScript("arguments[0].click()", driver.findElement(actionsTab));
		Thread.sleep(1500);
		js.executeScript("arguments[0].click()", driver.findElement(marinq));
		Thread.sleep(1500);

		 

	}
	
	

	public void logout()
	{
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(userProfile));
		js.executeScript("arguments[0].click()", driver.findElement(signOut));
		js.executeScript("arguments[0].click()", driver.findElement(Ok));
		
	}
}
