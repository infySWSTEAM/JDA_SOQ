package ObjectRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Functions.ExcelFile;

public class OrderDetails {
	
	WebDriver driver = null;
	ExcelFile exfile = new ExcelFile();
	WebDriverWait wait = null;
	
	By ApproveOrder = By.xpath("//*[@id=\"MainHeader_Approve_ButtonDiv\"]");
	By AppCreaPO = By.xpath("//*[@id='MainHeader_AcceptAndAdd_ButtonDiv']/div");
	By CreatePO = By.xpath("//*[@id=\"MainHeader_Add\"]");
	By RecalcOrder = By.xpath("//*[@id=\"MainHeader_Calculate_ButtonDiv\"]");
	By OrdLoadSum = By.xpath("//*[@id=\"F_0_0\"]");
	By OrdDetailPage = By.xpath("//*[@id=\"MainHeader_OrderDetails_ButtonDiv\"]");
	By OrdNotes = By.xpath("//div[@class='j-tab']/div/span");
	By OPcommunication = By.xpath("//*[@class='controltext'][@name='U_OUTPUT_COMMUNICATION']");
	By Done = By.xpath("//*[@id='null_ButtonDiv']//span[text()='Done']");
	public OrderDetails(WebDriver driver){

		this.driver = driver;

		}
	
	public void ApproveOrder()
	{
		driver.findElement(ApproveOrder).click();
	}
	
	public void AppCreaPO() 
	{
		driver.findElement(AppCreaPO).click();
	}
	
	public void CreatePO()
	{
		driver.findElement(CreatePO).click();
	}
	
	public void RecalcOrder() 
	{
		driver.findElement(RecalcOrder).click();
	}
	
	public void OrderDetail() 
	{
		driver.findElement(OrdDetailPage).click();
	}
	
	public void OrderNotes() 
	{
		driver.findElement(OrdNotes).click();
		
	}
	
	public void OrdLoadSum() 
	{
		String LoadSummary = driver.findElement(OrdLoadSum).getText();
		
		if(LoadSummary== "") {
			
		}
	}
	
	public void setOPcomm(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
		String Outputvalue = exfile.readExcel(filePa, fileNa, SheetNa, row, 18);
		driver.findElement(OPcommunication).sendKeys(Outputvalue);
	}
	
	public void clickdone() {
		driver.findElement(Done).click();
		}
	
		
}
