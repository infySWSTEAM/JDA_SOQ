package DriverScript;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Functions.DataBase;
import Functions.DififoReportSetup;
import Functions.ExcelFile;
import ObjectRepository.AddSOQOrder;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrderDetails;
import ObjectRepository.ReCalcOrder;
import ObjectRepository.SupplementalOrderPage;


public class AddSoq extends DififoReportSetup{

	WebDriver driver = null;
	ExcelFile exf = null;
	String url;
	String testScenarioFilePath;
	String testCaseFileName;
	String testCaseSheetName;
	String testCaseFileNameSOQ;
	String testCaseSheetNameSOQ;
	String testcasename;
	String testdatasheet;
	String query;
	XSSFWorkbook workbook  = null;
	String orderId;
	
	public void Screenshot(String Filename) throws IOException
	{
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		report.addImage(file, Filename);
	}
	
	@BeforeTest
	public void beforeTest() throws SQLException, Exception {
		System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		exf = new ExcelFile();
		InputStream envPropInput = new FileInputStream("./Environment\\Environment.properties");
		Properties envProp = new Properties();
		envProp.load(envPropInput);
		url= envProp.getProperty("URL");
		int row=1;
		testScenarioFilePath = envProp.getProperty("testScenarioFilePath");
		testCaseFileName = envProp.getProperty("testCaseFileName");
		testCaseSheetName = envProp.getProperty("testCaseSheetName");
		driver.get(url);
		report.log("Launching JDA UI");
		Thread.sleep(10000);
		Screenshot("JDA UI Launched");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		LoginPage lp= new LoginPage(driver);
		Thread.sleep(1000);
		report.log("Inputting the Username and Password");
		lp.setUsername(testScenarioFilePath,testCaseFileName,testCaseSheetName,row,0);
		Thread.sleep(1000);
		lp.setPassword(testScenarioFilePath,testCaseFileName,testCaseSheetName,row,1);
		Thread.sleep(2000);
		Screenshot("Login Entered");
		lp.clickSubmit();
		report.log("User logged in successfully");
		Thread.sleep(2000);
		report.log("User is viewing JDA Homepage now");
		Screenshot("HomePage");
		Thread.sleep(5000);
	}
	
	
	@Test(priority=0)
	public void SOQOrderCreation() throws SQLException, Exception{
		
		exf = new ExcelFile();
		InputStream envPropInput = new FileInputStream("./Environment\\Environment.properties");
		Properties envProp = new Properties();
		envProp.load(envPropInput);
		testScenarioFilePath = envProp.getProperty("testScenarioFilePath");
		testCaseFileName = envProp.getProperty("testCaseFileName");
		testCaseSheetName = envProp.getProperty("testdatasheet");
		int rowMax = exf.getTotalRowColumn(testScenarioFilePath,testCaseFileName,testCaseSheetName);
		for (int i=1;i<=rowMax;i++)
		{
		HomePage hp= new HomePage(driver);
		hp.setsearchname(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,0);
		report.log("Searching for Supplementary order creation UI by inputting the page name");
		report.log("Identifying from the Dropdown list");
		Screenshot("Inputting the search name");
		SupplementalOrderPage sop = new SupplementalOrderPage(driver);
		AddSOQOrder ASO = new AddSOQOrder(driver);
		OrderDetails od= new OrderDetails(driver);
		
		sop.dropdownclick();
		Thread.sleep(10000);
		driver.switchTo().frame("appFrame");
		sop.clickclear();
	    driver.switchTo().frame("PromptScreenPopupFrame");
	    sop.setInputSupplier(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,1);
	    sop.setEnteredby(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,8);
	    sop.setInputItem(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,2);
	    report.log("Inputting the Item and Supplier details");
		Screenshot("Setting up search criteria");
	    driver.switchTo().defaultContent();
		driver.switchTo().frame("appFrame");
		sop.clickdone();
		report.log("Search Results from UI");
		Thread.sleep(10000);
		Screenshot("Displaying Existing Search results");
		AddSOQ(i);
		Thread.sleep(5000);
	    driver.switchTo().defaultContent();
	    report.log("Refreshing the search again");
		driver.switchTo().frame("appFrame");
		ASO.clickgo();
		Thread.sleep(5000);
		sop.clickdone();
		Thread.sleep(8000);
		Screenshot("Refreshed Search results");
		tableValidation(i);
		
		Thread.sleep(8000);
		driver.switchTo().defaultContent();
		driver.switchTo().activeElement();
		driver.switchTo().frame("appFrame");
		orderOptimization(i);
		Thread.sleep(8000);
		report.log("Navigating to Order Details tab");
		od.OrderDetail();
		Thread.sleep(8000);
		Screenshot("Navigating to Order Details tab");
		od.RecalcOrder();
		Thread.sleep(3000);
		report.log("Recalculate orders");
		Screenshot("Recalculate orders success");
		driver.switchTo().frame("OrderOptRecalcOrderIframe");
		reCalculateOrder(i);
		Thread.sleep(8000);
		driver.switchTo().frame("appFrame");
		report.log("Navigating to Order Notes");
		od.OrderNotes();
		Screenshot("User is at Order Notes");
		Thread.sleep(8000);
		report.log("Selecting TMS output communication");
		od.setOPcomm(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,18);
		Screenshot("TMS output communication set up completed");
		od.clickdone();
		Thread.sleep(3000);
		od.ApproveOrder();
		Thread.sleep(15000);
		report.log("Orders Approved");
		zoomOut();
		Thread.sleep(5000);
		Screenshot("Approved Order");
		zoomRelease();
		Thread.sleep(5000);
		orderId(i);
		driver.switchTo().defaultContent();
		JDALogout();
		dbQuery(i);
		}
		}

		private void reCalculateOrder(int i) throws IOException, InterruptedException, AWTException {
			ReCalcOrder RCO= new ReCalcOrder(driver);
			Thread.sleep(3000);
			RCO.EnableForceopt();
			report.log("Enabling Force Optimization");
			Screenshot("Force optimization turned on");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(By.xpath("//*[@id='orderPlaceDate']"));
			js.executeScript("arguments[0].scrollIntoView();", Element);
			RCO.Orderplacedate(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,17);
			report.log("Orderplacedate Entered");
			Screenshot("Orderplacedate Entered");
			driver.switchTo().defaultContent();
			driver.switchTo().frame("appFrame");
			RCO.ClickDone();
			report.log("Done Clicked. Order Re-calculating");
			driver.switchTo().defaultContent();
			Thread.sleep(30000);
			zoomOut();
			Thread.sleep(5000);
			Screenshot("Order Re-calculated");
			zoomRelease();
			Thread.sleep(5000);
			orderId(i);
	}

		public void tableValidation(int i) throws IOException, SQLException, InterruptedException
		{   
		WebElement table =driver.findElement(By.id("FEGRID_7_2_1_15"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		System.out.println("row size = " +rows.size());
		SupplementalOrderPage sop = new SupplementalOrderPage(driver);
		for (int j=0;j<rows.size();j++)
		{
			List<WebElement> column =rows.get(j).findElements(By.tagName("td"));
					
						String loc=column.get(10).getText();
						String item =column.get(9).getText();
						String arriveDate=column.get(21).getText();
						String suppOrderId=column.get(11).getText();
											
						String mergeddata =item + loc + arriveDate + suppOrderId;
						System.out.println("Mergedata = " + mergeddata);
						
						String Temp = exf.cellMerge(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,15);		
						System.out.println("Excel merge =" + Temp);
						
						if (Temp.equals(mergeddata) == true)
						{
							report.log("Validating our SOQ and selecting it from the list of SOQ displayed");
							driver.findElement(By.id("FEGRID_7_2_1_1_"+(j-1)+"_cCol")).click();
							Thread.sleep(2000);
							Screenshot("SOQ identified");
							Thread.sleep(2000);
							sop.relatedPagesClick();
							report.log("Navigating to ORDERSKU details");
							Screenshot("Navigating to ORDERSKU details");
							Thread.sleep(2000);
							sop.orderSKUSearchPage();
						}
						else {
							System.out.println("No Matching records");
						}
		}
	
	}
		
	public void AddSOQ(int i) throws SQLException, Exception{
		
		exf = new ExcelFile();
		InputStream envPropInput = new FileInputStream("./Environment\\Environment.properties");
		Properties envProp = new Properties();
		envProp.load(envPropInput);
		testScenarioFilePath = envProp.getProperty("testScenarioFilePath");
		testCaseFileName = envProp.getProperty("testCaseFileName");
		testCaseSheetName = envProp.getProperty("testdatasheet");
		AddSOQOrder Add = new AddSOQOrder(driver);
		report.log("Adding a new ITEM/Supplier details for test data row: " + i);
		Add.addButton();
		Thread.sleep(3000);
		Add.setItem(testScenarioFilePath, testCaseFileName, testCaseSheetName,i,2);
		Add.setLoc(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 3);
		Add.setNeedQuanityt(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 4);
		Add.setCreateDate(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 5);
		Add.setSupplierOrderCode(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 6);
		Add.setSupplierOrderType(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 7);
		Add.setSupplierEnteredBy(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 8);
		Add.setSupplierRequestor(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 9);
		Add.setArriveDate(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 10);
		Add.setHoldOutRelease(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 11);
		Add.setGroupType(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 12);
		Add.setStatus(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 13);
		Add.suppOrderId(testScenarioFilePath, testCaseFileName, testCaseSheetName, i, 14);
		report.log("Input the test data for new SOQ creation");
		Screenshot("For test data row: " + i);
		Add.clickdone();
		}
	
	public void orderOptimization(int i) throws IOException, SQLException, InterruptedException
	{   
		WebElement table =driver.findElement(By.id("A5"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		System.out.println("row size = " +rows.size());
		
		for (int j=0;j<rows.size();j++)
		{
			List<WebElement> column =rows.get(j).findElements(By.tagName("td"));
					
						String loc=column.get(6).getText();
						String item =column.get(2).getText();
						//String arriveDate=column.get(29).getText();
						//String suppOrderCode=column.get(23).getText();
											
						String mergeddata =item + loc;
						System.out.println("Mergedata = " + mergeddata);
						
						String Temp = exf.cellMerge(testScenarioFilePath,testCaseFileName,testCaseSheetName,i,16);		
						System.out.println("Excel merge =" + Temp);
												
						if (Temp.equals(mergeddata) == true)
						{
							driver.findElement(By.id("A_"+(j-1)+"_0")).click();
							report.log("Identifying and selecting ORDERSKU");
							Screenshot("Identifying ORDERSKU");
							Thread.sleep(10000);
						}
						else {
							System.out.println("No Matching records");
						}
		}
	}
	
	public void zoomOut() throws AWTException
	{
		Robot robot =new Robot();
		 System.out.println("About to zoom out");
		 for (int z = 0; z < 5; z++) {
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_SUBTRACT);
		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 } 
	}
		
	public void zoomRelease() throws AWTException
	{
		Robot robot =new Robot();
		 System.out.println("Full screen");
		 for (int z = 0; z < 5; z++) {
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_ADD);
		 robot.keyRelease(KeyEvent.VK_SUBTRACT);
		 robot.keyRelease(KeyEvent.VK_ADD);
		 } 
	}
	
	public void orderId(int i) throws IOException
    {
	String text = driver.findElement(By.xpath("//tr[1]/td[2][@class='tableHeaderSubText gridRowCheckbookOff']")).getText(); 
    report.log("Order id is"+ text);
    exf = new ExcelFile();
    InputStream envPropInput = new FileInputStream("./Environment\\Environment.properties");
    Properties envProp = new Properties();
    envProp.load(envPropInput);
    String testDataFilePath = envProp.getProperty("testScenarioFilePath")+"\\"+ envProp.getProperty("testCaseFileName");
    File  file = new File(testDataFilePath);
    FileInputStream inputfile = new FileInputStream(file);
    workbook = new XSSFWorkbook(inputfile);         
    XSSFSheet sheet1 =workbook.getSheet("TestData");
    Row rowhead1 = sheet1.getRow(i);
                    Cell cell = rowhead1.createCell((short) 19);
                    cell.setCellValue(text);
    FileOutputStream fileOut = new FileOutputStream(testDataFilePath);
                    workbook.write(fileOut);
    }
	
	public String fetchItemNumber(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException
	{
    	orderId = exf.readExcel(filePa, fileNa, SheetNa, row, 19);
    	return orderId;
	}
	public void dbQuery(int i) throws IOException, SQLException
	{
		InputStream envPropInput = new FileInputStream("./Environment\\Environment.properties");
		Properties envProp = new Properties();
		envProp.load(envPropInput);	
		testScenarioFilePath = envProp.getProperty("testScenarioFilePath");
		testCaseFileName = envProp.getProperty("testCaseFileName");
		testdatasheet = envProp.getProperty("testdatasheet");
		String orderid = fetchItemNumber(testScenarioFilePath,testCaseFileName,testdatasheet,i,19);
		DataBase db= new DataBase();
		InputStream queryPropInput = new FileInputStream("./DB Query\\Query1.properties");
		Properties queryProp = new Properties();
		queryProp.load(queryPropInput);
		String Query = queryProp.getProperty("VEHICLELOADLINE");
		db.vehicleLoadLine(Query + orderid +"'",i);
	
	}
	
	
	public void JDALogout() throws InterruptedException, IOException, SQLException
	{
		LoginPage lp= new LoginPage(driver);
		Thread.sleep(2000);
		lp.useroption();
		Thread.sleep(2000);
		report.log("User trying to check the Logout options");
		Screenshot("User logout options");
		lp.JDAlogout();
		Thread.sleep(2000);
		report.log("User logging out of JDA UI");
		Screenshot("User Logged out");
		driver.quit();
		Thread.sleep(2000);
}
 
	}