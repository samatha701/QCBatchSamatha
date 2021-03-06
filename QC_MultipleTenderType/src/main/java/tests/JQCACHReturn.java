package tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.relevantcodes.extentreports.LogStatus;

public class JQCACHReturn extends QCStore {

	public static void ACHReturn(String SSN,String AppURL) throws InterruptedException
	{

		int lastrow=TestData.getLastRow("NSFPosting");
		String sheetName="NSFPosting";

		for(int row=2;row<=lastrow+1;row++)
		{		
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			String Return_For_Reason = TestData.getCellData(sheetName,"ReasonForReturn",row);

			if(SSN.equals(RegSSN))
			{

				//String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				String SSN1 = SSN.substring(0, 3);
				String SSN2 = SSN.substring(3,5);
				String SSN3 = SSN.substring(5,9);


				Thread.sleep(4000);
				test.log(LogStatus.INFO, "ACH Return from CSR has initiated");
				driver.switchTo().frame("bottom");
				String Str_date=driver.findElement(By.xpath("/html/body/blink/table/tbody/tr/td[4]")).getText();
				test.log(LogStatus.PASS, ""+Str_date);

				driver.switchTo().defaultContent();	

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
				driver.switchTo().frame("topFrame");
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
				driver.findElement(By.cssSelector("li[id='910000']")).click();	

				test.log(LogStatus.PASS, "Clicked on Loan Transactions");
				Thread.sleep(3000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				driver.findElement(By.cssSelector("li[id='911101']")).click();			
				test.log(LogStatus.PASS, "Clicked on Transactions");		
				driver.switchTo().frame("main");		
				driver.findElement(By.name("ssn1")).sendKeys(SSN1);
				test.log(LogStatus.PASS, "SSN1 is entered: "+SSN1);
				driver.findElement(By.name("ssn2")).sendKeys(SSN2);
				test.log(LogStatus.PASS, "SSN2 is entered: "+SSN2);
				driver.findElement(By.name("ssn3")).sendKeys(SSN3);
				test.log(LogStatus.PASS, "SSN3 is entered: "+SSN3);
				driver.findElement(By.name("submit1")).click();
				test.log(LogStatus.PASS, "Click on submit Button");		

				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");

				driver.findElement(By.name("button")).click();
				test.log(LogStatus.PASS, "Clicked on Go button under search results");



				driver.findElement(By.xpath("//input[@value='Go' and @type='button']")).click();
				test.log(LogStatus.PASS, "Clicked on Go button under Loans section");

				Thread.sleep(3000);
				String loan_nbr= driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form[1]/table[2]/tbody/tr[5]/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]")).getText();
				test.log(LogStatus.PASS, "Loan Number is" + loan_nbr);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");

				Thread.sleep(2000);

				Actions action = new Actions(driver);  
				WebElement collateral=driver.findElement(locator(Jprop.getProperty("ACH")));
				action.moveToElement(collateral).build().perform();
				test.log(LogStatus.PASS, " Mouse hover on ACH menu");
				Thread.sleep(2000);
				WebElement pdl=driver.findElement(locator(Jprop.getProperty("deposit_ach_pdl")));
				action.moveToElement(pdl).build().perform();
				test.log(LogStatus.PASS, " Mouse hover on payday loan menu");
				Thread.sleep(3000);
				driver.findElement(locator(Jprop.getProperty("ACH_Return"))).click();
				test.log(LogStatus.PASS, " click on clear menu");
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("mainFrame");
				driver.switchTo().frame("main");
				driver.findElement(locator(Jprop.getProperty("deposite_menu_loannbr"))).sendKeys(loan_nbr);
				driver.findElement(locator(Jprop.getProperty("ACH_submit"))).click();
				test.log(LogStatus.PASS, "Clicked on submit button");
				driver.findElement(locator(Jprop.getProperty("deposit_check_box"))).click();
				driver.findElement(locator(Jprop.getProperty("ACH_Return_reason"))).sendKeys(Return_For_Reason);
				driver.findElement(locator(Jprop.getProperty("finish_ach_deposit"))).click();
				test.log(LogStatus.PASS, "Clicked on  ACH Return button");
				if(driver.findElement(locator(Jprop.getProperty("ACH_Return_success"))).isDisplayed()){
					test.log(LogStatus.PASS, " ACH Return menu from CSR is successfull");
					test.log(LogStatus.PASS, "********************************************** ");
				}
				//break;
			}


		}


	}

}
