package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path =".\\testData\\LoginData.xlsx"; //grabbing Excel file from testData folder
		
		ExcelUtility xlUtils =new ExcelUtility(path);//object created for ExcelUtility class
		
		int totalRows=xlUtils.getRowCount("Sheet1"); //get total rowcount 
		int totalCols=xlUtils.getCellCount("Sheet1", 1); //get total colcount
		
		String loginData[][]=new String[totalRows][totalCols]; //creating String object to store cell data from Excel file
		
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalCols;j++)
			{
				loginData[i-1][j]=xlUtils.getCellData("Sheet1", i, j); //storing cell data into string
			}
		}
		
		return loginData; // returing the two dimentional data array
		
		
	}
	
}
