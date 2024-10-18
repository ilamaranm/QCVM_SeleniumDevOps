package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Paragraph;

public class Excel_DisableApprxTime extends DB_utility  {
	private WebDriver driver;
	private String filepath=System.getProperty("user.dir") + "/src/test/resources/Appmnt_admin.xlsx";
	private String scenario;
	private String scenario1;
	private String scenario2;
	private String state;
	private String state1;
	private String state2;
	
	@Test(dataProvider="sqlQueries1",priority=1)
	public void test(String driverpath,String Webhislink, String usr, String pas,String emrlink,String emrafilogin,String emrafipass,String emrpass) throws InterruptedException, IOException {

		Excel_utility exl=new Excel_utility(filepath);
		Screenshot_utility scrnshot=new Screenshot_utility();
		int scrnshtnum=rnd.nextInt(1,100);
		
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(Webhislink);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait=new WebDriverWait(driver,Duration.ofSeconds(20));
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
		WebElement settings=driver.findElement(By.xpath("//span[text()='Settings']"));
		js.executeScript("arguments[0].click()", settings);
		WebElement adminoptions=driver.findElement(By.xpath("//span[text()='Administrator Options']"));
		js.executeScript("arguments[0].click()", adminoptions);
		Thread.sleep(2000);
		WebElement other=driver.findElement(By.xpath("//label[text()='Other']"));
		js.executeScript("arguments[0].click()", other);
		Thread.sleep(3000);
		WebElement consultation_narration=driver.findElement(By.xpath("//input[@id='chkNAppointmntShowConsNarration']"));
		boolean isconsultation_narrationselected=consultation_narration.isSelected();
		if(consultation_narration.isSelected()) {
			scenario="Show consultant narration";
			state="Checked";
		}
		else {
			scenario="Show consultant narration";
			state="Unchecked";
		}
		
		WebElement disbaleappxtime=driver.findElement(By.xpath("//input[@id='chkdisbleAppxTime']"));
		boolean isdisbaleappxtime_selected=disbaleappxtime.isSelected();
		if(disbaleappxtime.isSelected()) {
			scenario1="Disable Approximate Time in Appointment";
			state1="Checked";
		}else {
			scenario1="Disable Approximate Time in Appointment";
			state1="Unchecked";
		}
		
		WebElement wilayats=driver.findElement(By.xpath("//input[@id='chkDisableWilayatAndGovernorate']"));
		boolean iswilayats_selected=wilayats.isSelected();
		if(wilayats.isSelected()) {
			scenario2="Disable Wilayats and Governorate";
			state2="Checked";
		}
		else {
			scenario2="Disable Wilayats and Governorate";
			state2="Unchecked";
		}
		Thread.sleep(2000);
		WebElement sav=driver.findElement(By.xpath("//span[@id='btnSave']"));
		js.executeScript("arguments[0].scrollIntoView()", sav);
		wait.until(ExpectedConditions.elementToBeClickable(sav));
		sav.click();
		driver.navigate().refresh();
		try {
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		} catch (NoAlertPresentException e) {
		    System.out.println("No alert present after refresh.");
		}
		
		WebElement app1=driver.findElement(By.id("btnappicon"));
		js.executeScript("arguments[0].click()", app1);
		WebElement masters=driver.findElement(By.xpath("(//span[@id='Masters'])[2]"));
		js.executeScript("arguments[0].click()", masters);
		WebElement consultants=driver.findElement(By.id("Consultants"));
		js.executeScript("arguments[0].click()", consultants);
		WebElement consultan_schedule=driver.findElement(By.xpath("//span[@id='Consultant Schedule']"));
		js.executeScript("arguments[0].click()", consultan_schedule);
		Thread.sleep(1000);
		WebElement consul_textbox=driver.findElement(By.xpath("(//input[@id='txtConsultant'])[1]"));
		consul_textbox.sendKeys(consname);
		Thread.sleep(2000);
		List<WebElement> conslit=driver.findElements(By.xpath("//tbody[@id='ScrollableContent']//tr"));
		for(WebElement con: conslit) {
			con.getText().equals(consname);
			Thread.sleep(1000);
			con.click();
		}
		Thread.sleep(2000);
		WebElement todate=driver.findElement(By.xpath("//input[@id='txtToDate']"));
		todate.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		todate.sendKeys(Keys.DELETE);
		LocalDate consdate=LocalDate.now().plusMonths(2);
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String condate=consdate.format(formatter);
		todate.sendKeys(condate);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnLoad']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='btnTime']")).click();	
		Thread.sleep(2000);
		WebElement schedule1=driver.findElement(By.id("chkSheduleI"));
		if (!schedule1.isSelected()){
		schedule1.click();
		}
		WebElement time1=driver.findElement(By.xpath("//input[@id='timfirst1']"));
		time1.click();
		time1.clear();
		time1.sendKeys("8.00");
		WebElement time2=driver.findElement(By.xpath("//input[@id='timfirst2']"));
		time2.click();
		time2.clear();
		time2.sendKeys("21.00");
		Thread.sleep(2000);
		WebElement schedule2=driver.findElement(By.xpath("//input[@id='chkSheduleII']"));
		if(schedule2.isSelected()) {
			schedule2.click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='chkSelectAll']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnok']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnSave']")).click();
		Thread.sleep(2000);
		
		WebElement frontoffice=driver.findElement(By.id("Front Office"));
		js.executeScript("arguments[0].click()", frontoffice);
		WebElement appointments=driver.findElement(By.id("Appointments"));
		js.executeScript("arguments[0].click()", appointments);
		WebElement appointscheduler=driver.findElement(By.id("Appoint.Scheduler"));
		js.executeScript("arguments[0].click()", appointscheduler);
		Thread.sleep(4000);
		WebElement location=driver.findElement(By.xpath("//select[@ID='ddlLocation']"));
		Select sel=new Select(location);
		sel.selectByIndex(locationindx);
		WebElement selectedOption = sel.getFirstSelectedOption();
		String selectedOptionText = selectedOption.getText();
		System.out.println("Selected option text: " + selectedOptionText);
		WebElement spec_search=driver.findElement(By.xpath("(//input[@id='txtSearch'])[1]"));
		spec_search.click();
		spec_search.sendKeys(splname);
		driver.findElement(By.xpath("//label[text()=' "+splname+" ']")).click();
		Thread.sleep(2000);
		
		try {
		    WebElement cons_search = driver.findElement(By.xpath("(//input[@id='txtSearch'])[2]"));
		    wait.until(ExpectedConditions.visibilityOf(cons_search));
		    cons_search.click();
		    cons_search.sendKeys(consname);
		    driver.findElement(By.xpath("//label[text()=' "+consname+" ']")).click();
		} catch (Exception e) {
		    System.out.println("cons_search element is not present in the DOM");
		}
		Thread.sleep(2000);
		
		String screenshotPath = Screenshot_utility.takeScreenshot(driver, 1);
        System.out.println("Screenshot saved at: " + screenshotPath);
        BufferedImage screenshotImage = Screenshot_utility.getScreenshotAsBufferedImage(driver, 2);
        String expectedResult;
        String actualResult;
        String status;
        try {
        	WebElement cons_narrationtext = driver.findElement(By.xpath("//textarea[@formcontrolname='narration']"));
        	boolean consnarrationisdisplayed=cons_narrationtext.isDisplayed();
           
            if (isconsultation_narrationselected) {
                if (consnarrationisdisplayed) {
                    expectedResult = "Consultant narration should be displayed";
                    actualResult = "Consultant Narration is Displayed in Appointment scheduler screen";
                    status = "Pass";
                } else {
                    expectedResult = "Consultant narration should be displayed";
                    actualResult = "Consultant Narration is not Displayed in Appointment scheduler screen";
                    status = "Fail";
                }
            } else {
                if (!consnarrationisdisplayed) {
                    expectedResult = "Consultant narration should not be displayed";
                    actualResult = "Consultant Narration is not Displayed in Appointment scheduler screen";
                    status = "Pass";
                } else {
                    expectedResult = "Consultant narration should not be displayed";
                    actualResult = "Consultant Narration is Displayed in Appointment scheduler screen";
                    status = "Fail";
                }
            }
            exl.addResult(scenario,state,expectedResult, actualResult, status, screenshotImage);
        }catch(Exception e) {
        	 expectedResult = "Consultant narration should not be displayed";
             actualResult = "Consultant Narration is not Displayed in Appointment scheduler screen";
             status = "Pass";
             exl.addResult(scenario,state,expectedResult, actualResult, status, screenshotImage);
        }
       
		WebElement slottime=driver.findElement(By.xpath("//div[text()='"+intervalTime2+"']"));
		Actions act=new Actions(driver);
		act.moveToElement(slottime).build().perform();
		act.contextClick(slottime).build().perform();
		
		WebElement addbutton=driver.findElement(By.xpath("//li[text()='Add']"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='Add']")));
		Thread.sleep(2000);
		addbutton.click();
		
		driver.findElement(By.xpath("(//button[text()=' Show More '])[2]")).click();
		Thread.sleep(2000);
		String screenshotPath1 = Screenshot_utility.takeScreenshot(driver, 3);
        System.out.println("Screenshot saved at: " + screenshotPath1);
        BufferedImage screenshotImage1 = Screenshot_utility.getScreenshotAsBufferedImage(driver, 4);
        try {
		WebElement appxTime=driver.findElement(By.xpath("//input[@formcontrolname='CP_Time']"));
		String expectedResult1;
        String actualResult1;
        String status1;
        boolean isEditable = appxTime.isEnabled() && !("true".equals(appxTime.getAttribute("readonly")));

        if (isdisbaleappxtime_selected) {
        	if(isEditable) {
        		expectedResult1="Approximate time should Disabled and not editable";
        		actualResult1="Approximate time is Editable";
        		status1="Fail";
        	}else {
        		expectedResult1="Approximate time should Disabled and not editable";
        		actualResult1="Approximate time is not Editable";
        		status1="Pass";
        	}
           
        } else {
        	if(!isEditable) {
        		expectedResult1="Approximate time should Enabled and editable";
        		actualResult1="Approximate time is not Editable";
        		status1="Fail";
        	}else {
        		expectedResult1="Approximate time should Enabled and editable";
        		actualResult1="Approximate time is Editable";
        		status1="Pass";
        	}
        }
        exl.addResult(scenario1,state1,expectedResult1, actualResult1, status1, screenshotImage1);

    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
        String expectedResult2;
        String actualResult2;
        String status2;
		try {
			WebElement governorate=driver.findElement(By.xpath("(//input[@id='txtGovernorate'])[1]"));
			boolean isgovernoratedisplay=governorate.isDisplayed();
	
            if(iswilayats_selected) {
            	Assert.assertTrue(!isgovernoratedisplay, "Governorate and wilayates should not display");
            		if(!isgovernoratedisplay) {
            			expectedResult2="Governorate and wilayates should not display";
            			actualResult2="Governorate and wilayates is not displayed";
            			status2="Pass";
            		}else {
            			expectedResult2="Governorate and wilayates should not display";
            			actualResult2="Governorate and wilayates is displayed";
            			status2="Fail";
            		}
            }else {
            	Assert.assertTrue(isgovernoratedisplay, "Governorate and wilayates should display");
            	if(isgovernoratedisplay) {
            		expectedResult2="Governorate and wilayates should display";
        			actualResult2="Governorate and wilayates is displayed";
        			status2="Pass";
            	}else {
            		expectedResult2="Governorate and wilayates should display";
        			actualResult2="Governorate and wilayates is not displayed";
        			status2="Fail";
            	}
            }
            exl.addResult(scenario2,state2,expectedResult2, actualResult2, status2, screenshotImage1);
		} catch (Exception e) {
			expectedResult2="Governorate and wilayates should not display";
			actualResult2="Governorate and wilayates is not displayed";
			status2="Pass";
			exl.addResult(scenario2,state2,expectedResult2, actualResult2, status2, screenshotImage1);
		}
		
		exl.save(filepath);
	}

}
