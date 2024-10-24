package op_bill;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EnableBill_dateEditable extends OP_DB_utility {
	private String filepath=System.getProperty("user.dir")+"/src/test/resources/OPBill_Admin.xlsx";
	private WebDriver driver;
	private String scenario;
	private String scenario1;
	private String scenario2;
	private String state;
	private String state1;
	private String state2;
	
	
	@Test(dataProvider="sqlQueries1",priority=1)
	public void bill_date(String driverpath,String Webhislink, String usr, String pas,String emrlink,String emrafilogin,String emrafipass,String emrpass) throws IOException, InterruptedException {
		OP_Excel_utility exl=new OP_Excel_utility(filepath);
		OP_Screenshot_utility scrnshot=new OP_Screenshot_utility();
		int scrnshtnum=rnd.nextInt(1,100);
	
		System.setProperty("webdriver.chrome.driver", driverpath);
		driver=new ChromeDriver();
		driver.get(Webhislink);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		js=(JavascriptExecutor)driver;
		
		driver.findElement(By.id("txtUsrId")).sendKeys(usr);
		driver.findElement(By.id("txtUsrpwd")).sendKeys(pas);
		driver.findElement(By.id("txtLogin")).click();
		Thread.sleep(2000);
		
		List<WebElement> menu=driver.findElements(By.xpath("//div[@class='input-group']//ul//li"));
		 if (!menu.isEmpty() && menu.get(locationindx).isDisplayed()) {
	            menu.get(locationindx).click();
	        }
		try {
			WebElement tab=driver.findElement(By.xpath("(//div[@role='tabpanel'] )[2]"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@role='tabpanel'] )[2]")));
		}catch(NoSuchElementException e) {
			 System.out.println("Exception while waiting for tab panel: " + e.getMessage());
		}
		
		int startIndex = connect.indexOf("//");
	    int semicolonind = connect.indexOf(";", startIndex+1);
	    int	endindex=connect.indexOf(";",semicolonind+1);
	   
	    String DBconnect = connect.substring(startIndex + 2, endindex);
        Thread.sleep(4000);
        WebElement app=driver.findElement(By.id("btnappicon"));
		js.executeScript("arguments[0].click()", app);
		WebElement settings=driver.findElement(By.id("Settings"));
		js.executeScript("arguments[0].click()", settings);
		WebElement adminoptions=driver.findElement(By.xpath("//span[text()='Administrator Options']"));
		js.executeScript("arguments[0].click()", adminoptions);
		Thread.sleep(2000);
		
		WebElement billing=driver.findElement(By.xpath("//label[@id='lblBilling']"));
		js.executeScript("arguments[0].click()", billing);
		Thread.sleep(3000);
		
		WebElement billdate_chk=driver.findElement(By.xpath("//input[@formcontrolname='BillDateEditable_IsEnabled']"));
		boolean billdate_editselected=billdate_chk.isSelected();
		if(billdate_editselected) {
			scenario="Enable Bill Date As Editable";
			state="Checked";
		}else {
			scenario="Enable Bill Date As Editable";
			state="Unchecked";
		}
		
		WebElement hideinsurance_chk=driver.findElement(By.xpath("//input[@formcontrolname='HideInsuranceDetailsfromGrid_IsEnabled']"));
		boolean hideinsurance_isselected=hideinsurance_chk.isSelected();
		if(hideinsurance_isselected) {
			scenario1="Hide Insurance Details from Grid";
			state1="Checked";
		}else {
			scenario1="Hide Insurance Details from Grid";
			state1="Unchecked";
		}
		
		WebElement show_doctroldetails_chk=driver.findElement(By.xpath("//input[@formcontrolname='ShowDoctorDetailsfromGrid_IsEnabled']"));
		boolean show_doctordetails_isselected=show_doctroldetails_chk.isSelected();
		if(show_doctordetails_isselected) {
			scenario2="Show Doctor Details from Grid";
			state2="Checked";
		}else {
			scenario2="Show Doctor Details from Grid";
			state2="Unchecked";
		}

		Thread.sleep(2000);
		WebElement sav=driver.findElement(By.xpath("//span[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView()", sav);
		wait.until(ExpectedConditions.elementToBeClickable(sav));
		sav.click();
		Thread.sleep(5000);
	    WebElement person=driver.findElement(By.xpath("(//i[text()='person'])[1]"));
	    js.executeScript("arguments[0].click()", person);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'backdrop')]")));
	    WebElement logoutButton = driver.findElement(By.xpath("//button[@id='btnLogout']"));
	    js.executeScript("arguments[0].click()",logoutButton);
	    driver.findElement(By.id("txtUsrId")).sendKeys(usr);
		driver.findElement(By.id("txtUsrpwd")).sendKeys(pas);
		driver.findElement(By.id("txtLogin")).click();
		Thread.sleep(2000);
			
			List<WebElement> menu1=driver.findElements(By.xpath("//div[@class='input-group']//ul//li"));
			 if (!menu1.isEmpty() && menu1.get(locationindx).isDisplayed()) {
		            menu1.get(locationindx).click();
		        }
			try {
				WebElement tab=driver.findElement(By.xpath("(//div[@role='tabpanel'] )[2]"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@role='tabpanel'] )[2]")));
			}catch(NoSuchElementException e) {
				 System.out.println("Exception while waiting for tab panel: " + e.getMessage());
			}
			
			Thread.sleep(4000);
		WebElement app1=driver.findElement(By.id("btnappicon"));	
		js.executeScript("arguments[0].click()", app1);
		WebElement frontoffice=driver.findElement(By.id("Front Office"));
		js.executeScript("arguments[0].click()", frontoffice);
		WebElement fobilling=driver.findElement(By.id("Billing"));
		js.executeScript("arguments[0].click()", fobilling);
		WebElement foopbill=driver.findElement(By.id("OP Bill"));
		js.executeScript("arguments[0].click()", foopbill);
		
		WebElement billdate=driver.findElement(By.id("txtDate"));
		wait.until(ExpectedConditions.visibilityOfAllElements(billdate));
		String screenshotPath = OP_Screenshot_utility.takeScreenshot(driver, 1);
	    System.out.println("Screenshot saved at: " + screenshotPath);
	    BufferedImage screenshotImage = OP_Screenshot_utility.getScreenshotAsBufferedImage(driver, 2);
		 
	    String expectedresult;
	    String actualresult;
	    String status;
		
		boolean isEditable = billdate.isEnabled() && !("true".equals(billdate.getAttribute("readonly")));
		
		 if(billdate_editselected) {
			 if(isEditable) {
				 expectedresult="Date should be able to alter";
				 actualresult="Date is able to alter";
				 status="Pass";
			 }
			 else {
				 expectedresult="Date should be able to alter";
				 actualresult="Date is not able to alter";
				 status="Fail";
			 }
		 }else {
			 if (!isEditable) {
			     expectedresult = "Date should not be able to alter";
			     actualresult = "Date is not able to alter";
			     status = "Pass";
			 } else {
			     expectedresult = "Date should not be able to alter";
			     actualresult = "Date is able to alter";
			     status = "Fail";
			 }
		 }
		exl.addResult(scenario, state, expectedresult, actualresult, status, screenshotImage);
		
		String expectedresult1;
		String actualresult1;
		String status1;
		
		try {
		WebElement deductible=driver.findElement(By.xpath("//input[@formcontrolname='BillD_Ded_Amt']"));
		WebElement coinsurance=driver.findElement(By.xpath("//input[@formcontrolname='BillD_Co_Ins_Per']"));
		
		if(hideinsurance_isselected) {
			if(!deductible.isDisplayed() && !coinsurance.isDisplayed()) {
				expectedresult1="Deductible and Co-insurance field should not displayed";
				actualresult1="Deductible and Co-insurance field is not displayed";
				status1="Pass";
			}else {
				expectedresult1="Deductible and Co-insurance field should not displayed";
				actualresult1="Deductible and Co-insurance field is displayed";
				status1="Fail";
			}
		}else {
			if(deductible.isDisplayed() && coinsurance.isDisplayed()) {
				expectedresult1="Deductible and Co-insurance field should displayed";
				actualresult1="Deductible and Co-insurance field is displayed";
				status1="Pass";
			}else {
				expectedresult1="Deductible and Co-insurance field should displayed";
				actualresult1="Deductible and Co-insurance field is not displayed";
				status1="Fail";
			}
		}
		exl.addResult(scenario1, state1, expectedresult1, actualresult1, status1, screenshotImage);
		}catch(Exception e) {
				expectedresult1="Deductible and Co-insurance field should not displayed";
				actualresult1="Deductible and Co-insurance field is not displayed";
				status1="Pass";
				exl.addResult(scenario1, state1, expectedresult1, actualresult1, status1, screenshotImage);
		}
		
		String expectedresult2;
		String actualresult2;
		String status2;
		
		try {
		WebElement grid_docID=driver.findElement(By.xpath("//input[@id='GridAuto0_AutoCode']"));
		WebElement grid_docName=driver.findElement(By.xpath("//input[@id='GridAuto0_AutoName']"));
		
		if(show_doctordetails_isselected) {
			if(grid_docID.isDisplayed() && grid_docName.isDisplayed()) {
				expectedresult2="Doctor ID and Doctor Name should displayed in the grid";
				actualresult2="Doctor ID and Doctor Name is displayed in the grid";
				status2="Pass";
			}else {
				expectedresult2="Doctor ID and Doctor Name should displayed in the grid";
				actualresult2="Doctor ID and Doctor Name is not displayed in the grid";
				status2="Fail";
			}
		}else {
			if(!grid_docID.isDisplayed() && !grid_docName.isDisplayed()) {
				expectedresult2="Doctor ID and Doctor Name should not displayed in the grid";
				actualresult2="Doctor ID and Doctor Name is not displayed in the grid";
				status2="Pass";
			}else {
				expectedresult2="Doctor ID and Doctor Name should not displayed in the grid";
				actualresult2="Doctor ID and Doctor Name is displayed in the grid";
				status2="Fail";
			}
		}
		exl.addResult(scenario2, state2, expectedresult2, actualresult2, status2, screenshotImage);
		}catch(Exception e) {
				expectedresult2="Doctor ID and Doctor Name should not displayed in the grid";
				actualresult2="Doctor ID and Doctor Name is not displayed in the grid";
				status2="Pass";
				exl.addResult(scenario2, state2, expectedresult2, actualresult2, status2, screenshotImage);	
		}
		exl.save(filepath);
	}

}
