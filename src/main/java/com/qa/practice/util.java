package com.qa.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class util extends base{
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static String  excelSheetPath=" ";
	
	public static Object[][] getData(String sheetName)
	{
		FileInputStream file=null;
		try {
			 file=new FileInputStream(excelSheetPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
	int rowCount=sheet.getLastRowNum();
	int cellCount=sheet.getRow(0).getLastCellNum();
	
	Object[][] obj=new Object[rowCount][cellCount];
	
	for(int i=0;i<rowCount;i++)
	{
		for(int j=0;j<cellCount;j++)
		{
			obj [i][j]=sheet.getRow(i+1).getCell(j).toString();
		}
		
	}
	return obj;

	}
	
	public static String getScreenshotAs(WebDriver driver,String screenshotName)
	{
		String date=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File scrFile=ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/FailedTtestsScreenshot/"+screenshotName+date+".png";
		try {
			FileUtils.copyFile(scrFile, new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}

}
