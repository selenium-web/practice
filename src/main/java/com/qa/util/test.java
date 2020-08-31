package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.testbase.TestBasePrac;

public class test extends TestBasePrac{
	
	public static long pageLoadTimeOut=19;
	public static long implicitWait=19;
	public static String TestDataPath="";
	
	public void switchToFrame()
	{
		driver.switchTo().frame("mainframe");
	}
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getData(String sheetName)
	{
		FileInputStream file = null;
		try
		{
		   file = new FileInputStream(TestDataPath);
		}catch(FileNotFoundException e){
		  e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		int rowNumber = sheet.getLastRowNum();
		int cellNumber = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowNumber][cellNumber];
		for(int i=0;i<rowNumber;i++)
		{
			for(int j=0;j<cellNumber;j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}
		
		return data;
	}

}
