package exceloperations;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class excelutility {

	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	CellStyle style;
	String path = null;
	
	public excelutility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rc=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rc;
	}
	
	public int getCellCount(String sheetName, int rc) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rc);
		int cc=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cc;
	}
	
	public String createRow(String sheetName, int rc) throws IOException
	{
		//System.out.println("Inside the GetCellData Function");
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		sheet.createRow(rc);
		return null;
	}
	
	
	public int nextRow(String sheetName) {
        int nextRow = 1;
        
        try {
    		fi=new FileInputStream(path);
    		workbook=new XSSFWorkbook(fi);
    		sheet=workbook.getSheet(sheetName);
    		
    		  int lastRowNum = sheet.getLastRowNum();
    		  //i <= lastRowNum
              for (int i = 1; ; i++) {
                  Row row = sheet.getRow(i);
                  if (row == null) {
                      nextRow = i;
                      break;
                  }
              }
              
              if (nextRow == 0) {
                  nextRow = lastRowNum + 1;
              }
            
           /* for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                boolean isEmptyRow=true;
                if(row!=null)
                {
                	for(int j=0;j<row.getLastCellNum();j++)
                	{
                		if (cell != null || cell.getCellType() != CellType.BLANK && !cell.toString().isEmpty()) {
                			
                			isEmptyRow=false;
                			break;
                            
                        }
                	}
                	
                	
                }
                if(isEmptyRow)
                {
                	nextRow = i;
                    break;
                }
                
            }*/
            
            fi.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return nextRow;
    }
	
	public String getCellData(String sheetName, int rc, int cc) throws IOException
	{
		//System.out.println("Inside the GetCellData Function");
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rc);
		cell=row.getCell(cc);
		
		
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try 
		{
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
		
	}
	
	public String setCellData(String sheetName, int rc, int cc, String data) throws IOException
	{
		//System.out.println("Inside the setCellData Function");
		fi =new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		//System.out.println("Opened excel file available at "+path);
		
		if(workbook.getSheetIndex(sheetName)==-1)	// Check if sheet is available
			{
			//System.out.println("Sheet not present, creating sheet "+sheetName);
			workbook.createSheet(sheetName);		// if not, then create new sheet
			System.out.println("Sheet created as "+sheetName);
			}
			
		else 
		{	
			sheet=workbook.getSheet(sheetName);
		
			//System.out.println("Sheet is present as "+sheetName);
		}
		
		if(sheet.getRow(rc)==null)					// Check if row exists,
			sheet.createRow(rc);					//if not, then creates new row
		row=sheet.getRow(rc);
		
		
		cell=row.createCell(cc);
		cell.setCellValue(data);
		//System.out.println("Row ->" + rc + ", Column ->"+ cc);
		//System.out.println("Value entered in "+sheetName);

		fo= new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		return data;
		
	}
	
	
	

}
