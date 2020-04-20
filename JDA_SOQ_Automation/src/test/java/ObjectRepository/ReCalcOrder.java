package ObjectRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Functions.ExcelFile;

public class ReCalcOrder {

	WebDriver driver = null;
	ExcelFile exfile = new ExcelFile();
	WebDriverWait wait = null;
	String InputCovDur;
	String CreateDate;
	By ForceOpt = By.xpath("//*[@id=\"PopupAppDiv\"]/form/table/tbody/tr[3]/td[2]/input");
	By BuildTo = By.xpath("//*[@id=\"PopupAppDiv\"]/form/table/tbody/tr[4]/td[1]/input");
	By CovDur = By.xpath("//*[@id=\"PopupAppDiv\"]/form/table/tbody/tr[4]/td[2]/input");
	By OrderplaceDate = By.xpath("//*[@id='orderPlaceDate']");
	By Done= By.xpath("//*[@id=\"OrderOptRecalcOrder_doneOn_ButtonDiv\"]");
				
	public ReCalcOrder (WebDriver driver){

		this.driver = driver;

		}
	
	public void EnableForceopt()
	{
		driver.findElement(ForceOpt).click();
	}
	public void EnableBuildTo()
	{
		driver.findElement(BuildTo).click();
	}
	public void InputCovDur()
	{
		driver.findElement(CovDur).clear();
		driver.findElement(CovDur).sendKeys("365D");
	}
	public void Orderplacedate(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
		CreateDate = exfile.readExcel(filePa, fileNa, SheetNa, row, 17);
		driver.findElement(OrderplaceDate).clear();
		driver.findElement(OrderplaceDate).sendKeys(CreateDate);
	}
	
	public void ClickDone()
	{
		driver.findElement(Done).click();
	}
	
}
