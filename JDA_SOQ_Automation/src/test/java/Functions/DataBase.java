///************************************************************************************************************************
//		Author           : SGWS JDA Team 
//		Last Modified by : Anushya Karunakaran
//		Last Modified on : 13-Feb-2020
//		Summary 		 : SQL Validations for SS Classification Rejection scenarios
//
//************************************************************************************************************************/

package Functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataBase extends DififoReportSetup {
	
	XSSFWorkbook workbook  = null;
	String testDataFilePath;
	String connectionURL;
	String userName;
	String pass;
	public void dbopen() throws IOException, SQLException {
		
	InputStream input = new FileInputStream("./Environment\\Environment.properties");
	Properties prop = new Properties();
	prop.load(input);
	testDataFilePath = prop.getProperty("testDataFilePath")+"\\"+prop.getProperty("testCaseFileName");
	connectionURL=prop.getProperty("ConnectionURL");
	userName=prop.getProperty("DBUserName");
	pass= prop.getProperty("DBPass");
	
	File  file = new File(testDataFilePath);
	FileInputStream inputfile = new FileInputStream(file);
	 workbook = new XSSFWorkbook(inputfile);
	 		}	
	
	public void vehicleLoadLine(String Query, int i) throws SQLException, IOException
	{
		dbopen();
		Connection con=DriverManager.getConnection(connectionURL,userName,pass);
		Statement stmt=con.createStatement();
		report.log("DbValidation");
        report.log(Query);
        System.out.println(Query);
		int col =20;
		ResultSet resSet = stmt.executeQuery(Query);
		Sheet sheet1 = workbook.getSheet("TestData");
		while (resSet.next())
		{		
			Row row = sheet1.getRow(i);
			row.createCell((short) col++).setCellValue(resSet.getString("LOADID"));
		
		}
		FileOutputStream fileOut = new FileOutputStream(testDataFilePath);
		workbook.write(fileOut);
		con.close();
	}
	
	
}