

///************************************************************************************************************************
//Author           : SGWS JDA Team 
//Last Modified by : Anushya Karunakaran
//Last Modified on : 13-Feb-2020
//Summary 		 : SQL Validations for SS Classification Rejection scenarios
//
//************************************************************************************************************************/

package ObjectRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Functions.ExcelFile;



public class AddSOQOrder {

WebDriver driver = null;
ExcelFile exfile = new ExcelFile();
WebDriverWait wait = null;	
String ItemIdentifed;
String Location;
String NeedQuantity;
String SupplierOrderCode;
String SupplierOrderType;
String SupplierEnteredBy;
String SupplierRequestor;
String ArriveDate;
String HoldOutRelease;
String CreateDate;
String GroupType;
String OrdStatus;
String SupplierOrderId;
By add = By.xpath("//*[@id='FEGRID_7_2_1_1_Add_ButtonDiv']//span[text()='Add']");
By item = By.xpath("//*[@name='SUPPORDERSKU_ITEM']");
By loc =By.xpath("//*[@name='SUPPORDERSKU_LOC']");
By needQuantity =By.xpath("//*[@name='SUPPORDERSKU_NEEDQTY']");
By suppOrderCode =By.xpath("//*[@name='SUPPORDERSKU_U_SUPP_ORDER_CODE']");
By suppOrderType =By.xpath("//*[@name='SUPPORDERSKU_TYPE']");
By suppEnteredBy =By.xpath("//*[@name='SUPPORDERSKU_U_ENTERED_BY']");
By suppRequestor =By.xpath("//*[@name='SUPPORDERSKU_U_REQUESTOR']");
By arriveDate =By.xpath("//*[@name='SUPPORDERSKU_NEEDARRIVDATE']");
By createDate =By.xpath("//*[@name='SUPPORDERSKU_U_UPLOAD_DT']");
By holdOutRelease =By.xpath("//*[@name='SUPPORDERSKU_HOLDOUTRELEASESTART']");
By Done = By.xpath("//*[@id='null_ButtonDiv']//span[text()='Done']");
By grptype = By.xpath("//*[@name='SUPPORDERSKU_GROUPTYPE']");
By status =By.xpath("//*[@name='SUPPORDERSKU_STATUS']");
By go = By.xpath("//*[@id='searchGoBtn']/img");
By suppId = By.xpath("//*[@name='SUPPORDERSKU_SUPPORDERID']");


 
public AddSOQOrder(WebDriver driver){

this.driver = driver;

}

public void addButton() {
driver.findElement(add).click();
}

public void setItem(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	ItemIdentifed = exfile.readExcel(filePa, fileNa, SheetNa, row, 2);
driver.findElement(item).sendKeys(ItemIdentifed);
}

public void setLoc(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	Location = exfile.readExcel(filePa, fileNa, SheetNa, row, 3);
driver.findElement(loc).sendKeys(Location);
}

public void setNeedQuanityt(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	NeedQuantity = exfile.readExcel(filePa, fileNa, SheetNa, row, 4);
	driver.findElement(needQuantity).clear();
driver.findElement(needQuantity).sendKeys(NeedQuantity);

}

public void setCreateDate(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	CreateDate = exfile.readExcel(filePa, fileNa, SheetNa, row, 5);
driver.findElement(createDate).sendKeys(CreateDate);
}

public void setSupplierOrderCode(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	SupplierOrderCode = exfile.readExcel(filePa, fileNa, SheetNa, row, 6);
driver.findElement(suppOrderCode).sendKeys(SupplierOrderCode);
}

public void setSupplierOrderType(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	SupplierOrderType = exfile.readExcel(filePa, fileNa, SheetNa, row, 7);
	Select orderType =new Select(driver.findElement(suppOrderType));
	orderType.selectByVisibleText(SupplierOrderType);
//driver.findElement(suppOrderType).sendKeys(SupplierOrderType);
}

public void setSupplierEnteredBy(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	SupplierEnteredBy = exfile.readExcel(filePa, fileNa, SheetNa, row, 8);
driver.findElement(suppEnteredBy).sendKeys(SupplierEnteredBy);
}

public void setSupplierRequestor(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	SupplierRequestor = exfile.readExcel(filePa, fileNa, SheetNa, row, 9);
driver.findElement(suppRequestor).sendKeys(SupplierRequestor);
}

public void setArriveDate(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	ArriveDate = exfile.readExcel(filePa, fileNa, SheetNa, row, 10);
driver.findElement(arriveDate).sendKeys(ArriveDate);
}



public void setHoldOutRelease(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	HoldOutRelease = exfile.readExcel(filePa, fileNa, SheetNa, row, 11);
driver.findElement(holdOutRelease).sendKeys(HoldOutRelease);
}

public void setGroupType(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	GroupType = exfile.readExcel(filePa, fileNa, SheetNa, row, 12);
	Select GpType =new Select(driver.findElement(grptype));
	GpType.selectByVisibleText(GroupType);
}
public void setStatus(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	OrdStatus = exfile.readExcel(filePa, fileNa, SheetNa, row, 13);
	Select ordStat =new Select(driver.findElement(status));
	ordStat.selectByVisibleText(OrdStatus);
}
public void suppOrderId(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	SupplierOrderId = exfile.readExcel(filePa, fileNa, SheetNa, row, 14);
driver.findElement(suppId).sendKeys(SupplierOrderId);
}

public void clickgo() {
driver.findElement(go).click();
}

public void clickdone() {
driver.findElement(Done).click();
}


}

