package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

public class QCFutureDeposit extends QCStore{
	public static String NextDueDate;

	public static void futureDeposit(String SSN,String AppURL) throws Exception
	{
		
			//String FileName= prop.getProperty("QC_Store_NewLoan_file_name");
			
			//ExcelNew TestData = new ExcelNew(System.getProperty("user.dir")+prop.getProperty("QC_Store_NewLoan_Test_data_sheet_path")+FileName+".xls");  		 
				int lastrow=TestData.getLastRow("Future_Deposit");
				String sheetName="Future_Deposit";

				for(int row=2;row<=lastrow;row++)
				{		
					String RegSSN = TestData.getCellData(sheetName,"SSN",row);
					if(SSN.equals(RegSSN))
					{
					
						String PIN = TestData.getCellData(sheetName,"PIN",row);
				       // System.out.println(Password);
				      
				        String ProductID = TestData.getCellData(sheetName,"ProductID",row);
				        String ProductType = TestData.getCellData(sheetName,"ProductType",row);

				        String StoreAge_Two = TestData.getCellData(sheetName,"StoreAge_Two",row);
				   
						       String SSN1 = SSN.substring(0, 3);
						        String SSN2 = SSN.substring(3,5);
						        String SSN3 = SSN.substring(5,9);
						
				       Thread.sleep(4000);
						//test.log(LogStatus.INFO, MarkupHelper.createLabel("Age Store process is initiated", ExtentColor.BLUE));
						//test.log(LogStatus.INFO, "Age Store process is initiated");

				       test.log(LogStatus.INFO, "Future Date process has initiated" );
				       driver.switchTo().defaultContent();	
						
				        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("topFrame")));
						driver.switchTo().frame("topFrame");
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='910000']")));
						 Thread.sleep(4000);
				        driver.findElement(By.cssSelector("li[id='910000']")).click();	
						
						test.log(LogStatus.PASS, "Clicked on Loan Transactions");
						Thread.sleep(4000);
						
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
						for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
						}
					    driver.switchTo().defaultContent();
					    driver.switchTo().frame("mainFrame");
					    driver.switchTo().frame("main");
					    
					    
					  // String loan_nbr= driver.findElement(locator(prop.getProperty("csr_loan_nbr"))).getText();
					  // test.log(LogStatus.PASS, "Loan Number is" + loan_nbr);
					    driver.findElement(By.name("button")).click();
						test.log(LogStatus.PASS, "Clicked on Go button under search results");
						// driver.findElement(By.name("button")).click();
						
					for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
							}				    
						 driver.switchTo().defaultContent();
						    driver.switchTo().frame("mainFrame");
						    driver.switchTo().frame("main");
						   // driver.findElement(By.name("button")).click();
						    
						  if(ProductID.equals("PDL"))
							 {
						    driver.findElement(By.xpath(" /html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
						    test.log(LogStatus.PASS, "Clicked on Go button under Loans section");
						    }
						    if(ProductID.equals("TLP"))
							 {
						    	driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[13]/input")).click();
							 }
						    
						    Thread.sleep(5000);
						    if(ProductID.equals("LOC"))
							 {
						    	///html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]	
						    	 driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/table/tbody/tr/td/table/tbody/tr[4]/td[11]/input[1]")).click();
							 }
						   
					
					  String loan_nbr= driver.findElement(locator(prop.getProperty("csr_loan_nbr"))).getText();
					 // loan_nbr="10234213";

					   test.log(LogStatus.PASS, "Loan Number is " + loan_nbr);
						 NextDueDate = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr/td/form[1]/table[2]/tbody/tr[5]/td/table/tbody/tr/td/table/tbody/tr[2]/td[4]")).getText();
						   test.log(LogStatus.PASS, "Next Due Date is " + NextDueDate);

					
					   driver.get(prop.getProperty("login_page"));  
					   Thread.sleep(10000);
						driver.switchTo().defaultContent();				
						driver.switchTo().frame("topFrame");
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
					       
				        driver.findElement(locator(prop.getProperty("borrower_tab"))).click();	
				        Thread.sleep(2000);
						//driver.findElement(By.xpath("//*[contains(text(),'Borrower')]")).click();			
						test.log(LogStatus.PASS, "Clicked on Borrower");
						try
						{
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");			 
						//wait.until(ExpectedConditions.visibilityOfElementLocated(locator(prop.getProperty("future_date_courtesy_calls"))));
						driver.findElement(locator(prop.getProperty("future_date_courtesy_calls"))).click();
						test.log(LogStatus.PASS, "Clicked on Courtesy Calls link");			
						}
						catch(Exception e)
						{
							driver.get(prop.getProperty("login_page"));  	
							driver.switchTo().defaultContent();				
							driver.switchTo().frame("topFrame");
							wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='900000']")));
						       
					        driver.findElement(locator(prop.getProperty("borrower_tab"))).click();	
					        Thread.sleep(2000);
					        driver.switchTo().defaultContent();
							driver.switchTo().frame("mainFrame");			 
							//wait.until(ExpectedConditions.visibilityOfElementLocated(locator(prop.getProperty("future_date_courtesy_calls"))));
							driver.findElement(locator(prop.getProperty("future_date_courtesy_calls"))).click();
							test.log(LogStatus.PASS, "Clicked on Courtesy Calls link");			
						}
						Thread.sleep(3000);
						for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
							}
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");	
						driver.switchTo().frame("main");		
						driver.findElement(locator(prop.getProperty("future_date_product"))).sendKeys(ProductType);
						test.log(LogStatus.PASS, "Product type is selected as " +ProductType);	
						
						
						driver.findElement(By.xpath("//input[@name='requestBean.collectionType' and @value='8']")).click();
						test.log(LogStatus.PASS, "Clicked on Customer search radiobutton");
						Thread.sleep(500);
						//driver.findElement(By.xpath("//select[@name='customerSearch' and @id='ach12']/option[6]")).click();
						//driver.findElement(By.xpath("//select[@name='customerSearch' and @id='ach12']/option[6]")).sendKeys("Loan Nbr");
						Select oSelect = new Select(driver.findElement(By.xpath("//select[@name='customerSearch' and @id='ach12']")));
						oSelect.selectByVisibleText("Loan Nbr");
						test.log(LogStatus.PASS, "Loan Nbr selected from List");
						Thread.sleep(500);
						driver.findElement(By.name("requestBean.loanCode")).sendKeys(loan_nbr);
						test.log(LogStatus.PASS, "Loan Nbr entered is :" + loan_nbr);
						Thread.sleep(500);
						
						
						driver.findElement(locator(prop.getProperty("future_date_search_btn"))).click();
						test.log(LogStatus.PASS, "Clicked on Search button" );
						String mainwindow=driver.getWindowHandle();
						//form[@name='olCollectionsForm']/table/tbody/tr/td/table[3]/tbody/tr[6]/td[2] 
						Thread.sleep(3000);
						int rowsize=driver.findElements(By.xpath("//form[@name='olCollectionsForm']/table/tbody/tr/td/table[3]/tbody/tr")).size();
						for(int j=3;j<=rowsize;j++)
						{
							String loan=driver.findElement(By.xpath("//form[@name='olCollectionsForm']/table/tbody/tr/td/table[3]/tbody/tr["+j+"]/td[2]")).getText();
							if(loan.equalsIgnoreCase(loan_nbr))
							{
								Thread.sleep(3000);
								driver.findElement(By.xpath("//form[@name='olCollectionsForm']/table/tbody/tr/td/table[3]/tbody/tr["+j+"]/td[15]")).click();
								test.log(LogStatus.PASS, "Clicked on Go button under search results" );
								break;
								
							}
						}
						 int IAgeStore=Integer.parseInt(StoreAge_Two);
						 DateFormat  df=new SimpleDateFormat("MM/dd/yyyy");
							Date DDueDateminus1 = df.parse(NextDueDate);
							Calendar cal = Calendar.getInstance();
							 cal.setTime(DDueDateminus1);
							 cal.add(Calendar.DATE, IAgeStore);
							 Date DDueDate1= cal.getTime();
							 NextDueDate =df.format(DDueDate1);
							 Thread.sleep(3000);
							 driver.findElement(locator(prop.getProperty("future_date_future_deposit_btn"))).click();
						        test.log(LogStatus.PASS, "Clicked on Future Date Deposit button ");	 
						for(String winHandle : driver.getWindowHandles()){
							if(!mainwindow.equalsIgnoreCase(winHandle))
							{
						    driver.switchTo().window(winHandle);
							String Due_Date[] =NextDueDate.split("/");
					        String Due_Date1 = Due_Date[0];
					        String Due_Date2 = Due_Date[1];
					        String Due_Date3 = Due_Date[2];
					        driver.findElement(locator(prop.getProperty("future_date_month_field"))).sendKeys(Due_Date1);
					        test.log(LogStatus.PASS, "Month is entered: "+Due_Date1);
							driver.findElement(locator(prop.getProperty("future_date_day_field"))).sendKeys(Due_Date2);
							test.log(LogStatus.PASS, "Date is entered: "+Due_Date2);
							driver.findElement(locator(prop.getProperty("future_date_year_field"))).sendKeys(Due_Date3);
							test.log(LogStatus.PASS, "Year is entered: "+Due_Date3);
							Thread.sleep(2000);
							driver.findElement(locator(prop.getProperty("future_date_submit_btn"))).click();	
							test.log(LogStatus.PASS, "Submit button is clicked");
							Thread.sleep(3000);
							driver.findElement(locator(prop.getProperty("future_date_close_btn"))).click();	
							test.log(LogStatus.PASS, "Close button is clicked");
							 test.log(LogStatus.PASS, "Future Date Completed Successfully ");
							 test.log(LogStatus.PASS, "**********************************************");
							break;

							}
						
							
						
						 
					}
						driver.switchTo().window(mainwindow);
						
						driver.switchTo().defaultContent();
						driver.switchTo().frame("mainFrame");	
						driver.switchTo().frame("main");	
						/*driver.findElement(locator(prop.getProperty("future_date_main_submit_btn"))).click();	
						test.log(LogStatus.PASS, "Submit button is clicked in main window");
						try { 
						    Alert alert = driver.switchTo().alert();
						    alert.accept();
							test.log(LogStatus.PASS, "Alert accepted");
											
							
							}
							catch (NoAlertPresentException e) {
							    //do what you normally would if you didn't have the alert.
							}*/
						Thread.sleep(3000);
						driver.close();
						break;
				}
				
}
		

	}
}



