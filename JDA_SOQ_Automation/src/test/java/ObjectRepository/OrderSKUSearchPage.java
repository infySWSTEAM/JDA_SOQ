package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Functions.ExcelFile;

public class OrderSKUSearchPage {
	WebDriver driver = null;
	ExcelFile exfile = new ExcelFile();
	WebDriverWait wait = null;

	By displaytable = By.xpath("//*[@id='A5']");

	By OrderSkuarrow = By.xpath("//*[@src='/jda/common/css/theme-platform/images/ux/16x16/go_normal_16.png']");
	
	By Orderdetails = By.xpath("//*[@id='MainHeader_OrderDetails']");
	
	By Related = By.xpath("//*[@id=\"MainHeader_DynamicNavigation\"]/div/div/span");
	
	
	
	public OrderSKUSearchPage(WebDriver driver)
	{

		this.driver = driver;

	}
	
	public void Arrowclick() {
		driver.findElement(OrderSkuarrow).click();
		}
	
	public void Orderdetails() {
		driver.findElement(Orderdetails).click();
		}
	
	public void Related() {
		driver.findElement(Related).click();
		}
}
