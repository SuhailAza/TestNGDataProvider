package util;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class testUtil {
	 
	//Method used to get data from excel files and return data
	public static String[][] getDataFromExcel(String excelPath, String sheetName) {
		
		 String[][] data = null; 
		 
		 
		 try
		 {
		  	   	FileInputStream fis = new FileInputStream(excelPath);
		  	   	XSSFWorkbook wb = new XSSFWorkbook(fis);
		  	   	XSSFSheet sh = wb.getSheet(sheetName);
		  	   	XSSFRow row = sh.getRow(0);
		  	   	//Get number of rows
		  	   	int noOfRows = sh.getPhysicalNumberOfRows();
		  	   	//Get number of columns
		  	   	int noOfCols = row.getLastCellNum();
		  	   	DataFormatter formatter = new DataFormatter();
		  	   	Cell cell;
		  	   	//Loop to get and store data in array
		  	   	data = new String[noOfRows-1][noOfCols];
		  	   	for(int i =1; i<noOfRows;i++){
		  		     for(int j=0;j<noOfCols;j++){
		  		    	   row = sh.getRow(i);
		  		    	   cell= row.getCell(j);
		  		    	   data[i-1][j] = formatter.formatCellValue(cell);
		  	   	 	   }
		  	   	}
		  	  	}catch (Exception e) {
		  	     	   System.out.println("The exception is: " +e.getMessage());
   	           	}
		 //Return data 	  	
		 return data;
		
		
		
	}
	
	
		
				
		
	}

 
