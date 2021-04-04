package com.lp.qa.testdata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	
public static String ME_excel_path="C:\\Users\\sys\\eclipse-workspace\\LPAutomationtest\\src\\main\\java\\com\\lp\\qa\\testdata\\ManualEntryPaymentTestData.xlsx";
public static String CP_excel_path="C:\\Users\\sys\\eclipse-workspace\\LPAutomationtest\\src\\\\main\\java\\com\\lp\\qa\\testdata\\ContactLessPatient.xlsx";
public static String PR_excel_path="C:\\Users\\sys\\eclipse-workspace\\LPAutomationtest\\src\\main\\java\\com\\lp\\qa\\testdata\\PatientResonsiblity.xlsx";
public static String RB_excel_path="C:\\Users\\sys\\eclipse-workspace\\LPAutomationtest\\src\\main\\java\\com\\lp\\qa\\testdata\\RecurringBilling.xlsx";

	static int rowcount;
	static Row row;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static FileInputStream fis;
	static FileOutputStream fos;
	public static Object[][] data;
	
public static Object[][] getManaulEntryPaymentData() throws IOException {
		
		try {
			
			fis = new FileInputStream(ME_excel_path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			rowcount = sheet.getLastRowNum();
			System.out.println("Row Count:" + ((rowcount)+1));
			
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				
				for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
					
		
					Cell cell=sheet.getRow(i+1).getCell(j);
			
					switch (cell.getCellType())               
					{  
					case STRING:    //field that represents string cell type  CELL_TYPE_STRING
					data[i][j]=cell.getStringCellValue();		
					System.out.println("String: "+data[i][j] + "\t"); 
					break;  
					
					case NUMERIC:    //field that represents number cell type  
					data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
					System.out.println("Numeric:"+data[i][j] + "\t");
					break;  
					
					case BLANK:
					data[i][j]=cell.getStringCellValue();
					System.out.println("Blank:"+data[i][j] + "\t"); 
                    break;  
					
					default:  
					
					} 
				
				}
			
			}
			
		}catch(Exception e) {
			
		}
		return data;

	}
public static Object[][] getContactLessPaymentData() throws IOException {
	
	try {
		
		fis = new FileInputStream(CP_excel_path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		rowcount = sheet.getLastRowNum();
		System.out.println("Row Count:" + ((rowcount)+1));
		
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			
			for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
				
	
				Cell cell=sheet.getRow(i+1).getCell(j);
		
				switch (cell.getCellType())               
				{  
				case STRING:    //field that represents string cell type  CELL_TYPE_STRING
				data[i][j]=cell.getStringCellValue();		
				System.out.println("String: "+data[i][j] + "\t"); 
				break;  
				
				case NUMERIC:    //field that represents number cell type  
				data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
				System.out.println("Numeric:"+data[i][j] + "\t");
				break;  
				
				case BLANK:
				data[i][j]=cell.getStringCellValue();
				System.out.println("Blank:"+data[i][j] + "\t"); 
                break;  
				
				default:  
				
				} 
			
			}
		
		}
		
	}catch(Exception e) {
		
	}
	return data;

}
public static Object[][] getPatintResponsPaymentData(String SheetName) throws IOException {
	
	try {
		
		fis = new FileInputStream(PR_excel_path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(SheetName);
		rowcount = sheet.getLastRowNum();
		System.out.println("Row Count:" + ((rowcount)+1));
		
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			
			for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
				
	
				Cell cell=sheet.getRow(i+1).getCell(j);
		
				switch (cell.getCellType())               
				{  
				case STRING:    //field that represents string cell type  CELL_TYPE_STRING
				data[i][j]=cell.getStringCellValue();		
				System.out.println("String: "+data[i][j] + "\t"); 
				break;  
				
				case NUMERIC:    //field that represents number cell type  
				data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
				System.out.println("Numeric:"+data[i][j] + "\t");
				break;  
				
				case BLANK:
				data[i][j]=cell.getStringCellValue();
				System.out.println("Blank:"+data[i][j] + "\t"); 
                break;  
				
				default:  
				
				} 
			
			}
		
		}
		
	}catch(Exception e) {
		
	}
	return data;

}
public static Object[][] getRecBillingCardEntryByPatientData(String SheetName) throws IOException {
	
	try {
		
		fis = new FileInputStream(RB_excel_path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(SheetName);
		rowcount = sheet.getLastRowNum();
		System.out.println("Row Count:" + ((rowcount)+1));
		
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			
			for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
				
	
				Cell cell=sheet.getRow(i+1).getCell(j);
		
				switch (cell.getCellType())               
				{  
				case STRING:    //field that represents string cell type  CELL_TYPE_STRING
				data[i][j]=cell.getStringCellValue();		
				System.out.println("String: "+data[i][j] + "\t"); 
				break;  
				
				case NUMERIC:    //field that represents number cell type  
				data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
				System.out.println("Numeric:"+data[i][j] + "\t");
				break;  
				
				case BLANK:
				data[i][j]=cell.getStringCellValue();
				System.out.println("Blank:"+data[i][j] + "\t"); 
                break;  
				
				default:  
				
				} 
			
			}
		
		}
		
	}catch(Exception e) {
		
	}
	return data;

}
public static Object[][] getRecBillingCardEntryByProviderData(String SheetName) throws IOException {
	
	try {
		
		fis = new FileInputStream(RB_excel_path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(SheetName);
		rowcount = sheet.getLastRowNum();
		System.out.println("Row Count:" + ((rowcount)+1));
		
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			
			for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
				
	
				Cell cell=sheet.getRow(i+1).getCell(j);
		
				switch (cell.getCellType())               
				{  
				case STRING:    //field that represents string cell type  CELL_TYPE_STRING
				data[i][j]=cell.getStringCellValue();		
				System.out.println("String: "+data[i][j] + "\t"); 
				break;  
				
				case NUMERIC:    //field that represents number cell type  
				data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
				System.out.println("Numeric:"+data[i][j] + "\t");
				break;  
				
				case BLANK:
				data[i][j]=cell.getStringCellValue();
				System.out.println("Blank:"+data[i][j] + "\t"); 
                break;  
				
				default:  
				
				} 
			
			}
		
		}
		
	}catch(Exception e) {
		
	}
	return data;

}

}
