package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_LoginData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String [totalrows][totalcols]; //created for two dimensional array which can store
		
		for(int i=1;i<=totalrows;i++)  //1 //read the data from excel storing in two dimensional array
			
		{
			for(int j=0;j<totalcols;j++)  //0    i is rows j is cols
			
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		
		return logindata; //returning two dimensional array 
			
	}
	
	//As of now we need only 1 data provider method if we need more then we can create
	
	//DataProvider2
	
	//DataProvider3
	
	//DataProvider4
}
