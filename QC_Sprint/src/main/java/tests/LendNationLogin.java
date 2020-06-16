package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
public class LendNationLogin extends QCStore
{
	public static void login(String SSN,String App_URL) throws Exception
{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver, 5000);
		int lastrow=TestData.getLastRow("Reg");
		String sheetName="Reg";
		
		WebDriverWait wait = new WebDriverWait(driver, 35000);
		for(int row=2;row<=lastrow;row++)
		{		
			String RegSSN = TestData.getCellData(sheetName,"SSN",row);
			System.out.println("SSN number  "+ RegSSN);

			if(SSN.equals(RegSSN))//---->
			{	
			String LendNation_URL =	prop.getProperty("Lend_URL");
			//String state = TestData.getCellData(sheetName,"State",row);
			String email1 = TestData.getCellData(sheetName,"Email",row);
			String email2 = TestData.getCellData(sheetName,"EmailExt",row);
			String email=email1.concat(email2);
			String password = TestData.getCellData(sheetName,"Password",row);
			 
			driver.get(LendNation_URL);
			test.log(LogStatus.PASS, "Opened Lend Natino URL  "+LendNation_URL);
		    
			driver.findElement(By.className("logInMenu")).click();
			test.log(LogStatus.PASS, "clicked on Login profile");
		    driver.findElement(By.name("Username")).sendKeys(email);
		    test.log(LogStatus.PASS, "Username entered as "+email);
		    driver.findElement(By.name("Password")).sendKeys(password);
		    test.log(LogStatus.PASS, "Password entered as "+password);
		    driver.findElement(By.id("loginbtn")).click();	
		    test.log(LogStatus.PASS, "clicked on Login button");
		    Thread.sleep(10000);
		    }
		}
	}
}


	