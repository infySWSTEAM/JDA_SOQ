
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
import org.openqa.selenium.support.ui.WebDriverWait;

import Functions.ExcelFile;


public class SupplementalOrderPage {



WebDriver driver = null;
ExcelFile exfile = new ExcelFile();
WebDriverWait wait = null;	
String InputItem;
String InputSupplier;
String Enteredby;
By Dropdown = By.xpath("//*[@id='directory']//span[@title='SupplementalOrder for Demand Planning']");
By inputItem = By.xpath("//*[@id='PopupAppDiv']/form/table[2]/tbody/tr[18]/td[3]/input");
By enteredBy = By.xpath("//*[@id=\"PopupAppDiv\"]/form/table[2]/tbody/tr[8]/td[3]/input");
By inputSupplier = By.xpath("//*//*[@id='PopupAppDiv']/form/table[2]/tbody/tr[6]/td[3]/input");
By Done =By.xpath("//span[@id='PromptScreenPopup_doneBtnOn']/div/a/div/div[@class='j-button-fill']/span[text()='Done']");
By Clear =By.xpath("//*[@id='PromptScreenPopup_clearOn']/div/div/span");
By relatedPages =By.xpath("//*[@id='FEGRID_7_2_1_1_hdrmain_DynamicNavigation']//span[text()='Related Pages']");
By orderSKUSearchPage =By.xpath("//*[@id='links']//*[text()='OrderSKU Search/Details']");
By displaytable = By.xpath("//*[@id='FEGRID_7_2_1_15']");
   
public SupplementalOrderPage(WebDriver driver){

this.driver = driver;

}

public void dropdownclick() {
driver.findElement(Dropdown).click();
}
public void setInputItem(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
InputItem = exfile.readExcel(filePa, fileNa, SheetNa, row, 2);
driver.findElement(inputItem).sendKeys(InputItem);

}
public void setInputSupplier(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	InputSupplier = exfile.readExcel(filePa, fileNa, SheetNa, row, 1);
driver.findElement(inputSupplier).sendKeys(InputSupplier);
}

public void setEnteredby(String filePa, String fileNa,String SheetNa,int row,int col) throws IOException{
	Enteredby = exfile.readExcel(filePa, fileNa, SheetNa, row, 8);
	driver.findElement(enteredBy).sendKeys(Enteredby);
}

public void clickdone() {
driver.findElement(Done).click();
}

public void relatedPagesClick() {
driver.findElement(relatedPages).click();

}
public void orderSKUSearchPage() {
driver.findElement(orderSKUSearchPage).click();

}

public void clickclear() {
driver.findElement(Clear).click();
}


}
