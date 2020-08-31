package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.testbase.TestBasePrac;

public class TestUtil extends TestBasePrac{
	
	public static long PageLoadTimeOut=19;
	public static long ImplicitlyWait=19;
	
	public static String TestDataFilePath="C:\\Maven\\Automation_Learning\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCrmTestData.xlsx";
	public static Workbook workbook;
	public static Sheet sheet;
	
	
    public static Object[][] getTestData(String sheetName)
    {
    	FileInputStream file=null;
    	try {
    	 file = new FileInputStream(TestDataFilePath);
    	} catch(FileNotFoundException e) {
    		e.printStackTrace();
    }
      
    	try {
    		workbook=WorkbookFactory.create(file);
    	}catch(IOException e) {
    		
    		e.printStackTrace();
    	}
    	
    	sheet=workbook.getSheet(sheetName);
    	long rowNumber=sheet.getLastRowNum();
    	long cellNumber = sheet.getRow(0).getLastCellNum();
    System.out.println("Number of row in sheet " +sheetName + "  "+ sheet.getLastRowNum());
   	System.out.println("Number of columns in sheet  "+sheetName+ "   "+ sheet.getRow(0).getLastCellNum());
    	
   	Object[][] Testdata = null;
   	 Testdata = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
   	
   //	int i=0
   	for(int i=0;i<rowNumber;i++)
   	{
   		for(int j=0;j<cellNumber;j++)
   		{
   			Testdata[i][j]=sheet.getRow(i+1).getCell(j).toString();
   			
   		}
   	}
   	    return Testdata;
    }
    
    
    
}
