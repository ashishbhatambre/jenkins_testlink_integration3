	 
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.*;
import java.awt.Toolkit;
import org.openqa.selenium.interactions.*;

import java.io.*;
import java.util.*;

public class SELTestRunner {

	    static WebDriver driver;
	    static Wait<WebDriver> wait;
		static TestResource resource;
		static String company;
		static String product;
		static String browser;
		static String baseURL;
		static int waitTime;
		static String commandOutput="";
		static String test_cases;
static boolean isSmokeTest = false;
	    
		private static void initDriver(String driverName){
		//    AndroidDriver, AndroidWebDriver, ChromeDriver, EventFiringWebDriver, FirefoxDriver, HtmlUnitDriver, InternetExplorerDriver, IPhoneDriver, IPhoneSimulatorDriver, RemoteWebDriver
			driverName = driverName.trim();



if(enableSSL){

	if("org.openqa.selenium.firefox.FirefoxDriver".equals(driverName)) {
FirefoxProfile firefoxprofile = new FirefoxProfile();
firefoxprofile.setEnableNativeEvents(true);

firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
firefoxprofile.setAcceptUntrustedCertificates(true);
driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxprofile);
return;
	}

	if("org.openqa.selenium.ie.InternetExplorerDriver".equals(driverName)){
String iedriverpath = resource.getItemInLocale("iedriverpath");
//System.setProperty("webdriver.ie.driver",iedriverpath);

 File file = new File(iedriverpath); 
 System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

driver = new org.openqa.selenium.ie.InternetExplorerDriver();

DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
 
 capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
 
  driver = new InternetExplorerDriver(capabilities);
 
 driver.manage().window().maximize();


driver.navigate().to("javascript:document.getElementById('overridelink').click()");
return;

	}

	if("org.openqa.selenium.chrome.ChromeDriver".equals(driverName)){

DesiredCapabilities capabilities = DesiredCapabilities.chrome();
capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
driver = new org.openqa.selenium.chrome.ChromeDriver(capabilities);
return;

	}

}
else{


if("org.openqa.selenium.ie.InternetExplorerDriver".equals(driverName)){
String iedriverpath = resource.getItemInLocale("iedriverpath");

 File file = new File(iedriverpath); 
 System.setProperty("webdriver.ie.driver", file.getAbsolutePath());


DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
 
 capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
 
  driver = new InternetExplorerDriver(capabilities);

return;
}

		try{
driver = (org.openqa.selenium.WebDriver)Class.forName(driverName).newInstance();
		}
		catch(Exception ne){ne.printStackTrace();}

}

//DesiredCapabilities dc = DesiredCapabilities.firefox();
//dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//FirefoxDriver driver = new FirefoxDriver(dc);


		}
		
		
		static HashMap testCaseNames = new HashMap();
				static HashMap testFileNameMap = new HashMap();

static HashMap testCaseStatus = new HashMap();
static HashMap testCaseForReportMap = new HashMap();
static HashMap screenImages = new HashMap();

				static HashMap componentMap = new HashMap();
				static HashMap roleMap = new HashMap();
				static HashMap artifactMap = new HashMap();

static int totalTestCases = 0;

public static void resizeTest() {

            Toolkit toolkit = Toolkit.getDefaultToolkit();

            Dimension screenResolution = new Dimension((int)
toolkit.getScreenSize().getWidth(), (int)
toolkit.getScreenSize().getHeight());

            driver.manage().window().setSize(screenResolution);

        } 

//public static void resizeTest() {
   // ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("window.resizeTo("+resource.getItemInLocale("browsersize")+");");

//driver.manage().window().setSize();

//((org.openqa.selenium.JavascriptExecutor)driver).executeScript("if (true) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth, window.screen.availHeight)}");
//}


static boolean enableSSL = false;

		public static void main(String[] args) {

 testCaseNames = new HashMap();
 testCaseStatus = new HashMap();
 testCaseForReportMap = new HashMap();
 screenImages = new HashMap();

componentMap = new HashMap();
testFileNameMap = new HashMap();
	roleMap = new HashMap();
	artifactMap=new HashMap();

 commandOutput ="";

			resource = new TestResource(args[0]);
			test_cases=args[1];
			String driverName = resource.getItemInLocale("driver");
			
			company = resource.getItemInLocale("company");
			product = resource.getItemInLocale("product");
			browser = resource.getItemInLocale("browser");
			baseURL = resource.getItemInLocale("baseURL");

			enableSSL = false;

			try{
			enableSSL = Boolean.parseBoolean(resource.getItemInLocale("enableSSL"));
			}
			catch(Exception ne){}

			isSmokeTest = false;

			try{
			isSmokeTest = Boolean.parseBoolean(resource.getItemInLocale("isSmokeTest"));
			}
			catch(Exception ne){}

			waitTime =30;

			String wTStr = resource.getItemInLocale("waitTime");

			try{
				wTStr = wTStr.trim();
waitTime = Integer.parseInt(wTStr);
			}
			catch(Exception ne){}

			//System.out.println("Before loading Driver ");

			
	        initDriver(driverName); //new FirefoxDriver();
			//System.out.println("Driver loaded");
				
resizeTest();

			String testCasesC = resource.getItemInLocale("testcaseorder");

			String testCases[] = testCasesC.split(",");
totalTestCases = testCases.length;

			int count =0;

			for(String testCase: testCases){


			 HashMap result;
			 boolean flag= false;

	        try {
	           	
				result = executeTest(testCase,count);
				flag = ((Boolean)result.get("2")).booleanValue();

	        } catch(Exception e) {
	            e.printStackTrace();
				result=null;
				flag=false;
	        } 
			count++;
	 
	       
						if(result!=null)
							System.out.println(result.get("1")+" Test " + (flag? "passed." : "failed."));
						else	        
						System.out.println(testCase+" Test " + (flag? "passed." : "failed."));
			String report = ReportManager.getIndex(totalTestCases,company,product,browser,testCaseNames,testCaseStatus,testCaseForReportMap,screenImages,testFileNameMap);

			ReportThread rt = new ReportThread();
			rt.report = report;
			rt.start();

						String report2 = ReportManager.getCSV(totalTestCases,company,product,browser,testCaseNames,testCaseStatus,testCaseForReportMap,screenImages,testFileNameMap);

						ReportCSVThread rt2 = new ReportCSVThread();
			rt2.report = report2;
			rt2.start();
			}
			
			driver.close();
	

	/*
			try{
			String report = ReportManager.getIndex(company,product,browser,testCaseNames,testCaseStatus,testCaseForReportMap,screenImages,totalTestCases);
			FileManager.setContents(new File("report/index.html"), report);
			}
			catch(Exception ne){}
			*/
			String report = ReportManager.getIndex(totalTestCases,company,product,browser,testCaseNames,testCaseStatus,testCaseForReportMap,screenImages,testFileNameMap);

			ReportThread rt = new ReportThread();
			rt.report = report;
			rt.start();

			String report2 = ReportManager.getCSV(totalTestCases,company,product,browser,testCaseNames,testCaseStatus,testCaseForReportMap,screenImages,testFileNameMap);

						ReportCSVThread rt2 = new ReportCSVThread();
			rt2.report = report2;
			rt2.start();
	      
	    }
	 

	 

private static void typeHiddenField(String nameOrID, String text){


JavascriptExecutor jse = (JavascriptExecutor)driver;

try{
jse.executeScript("document.getElementById('"+nameOrID+"').value='"+text+"';");
//driver.findElement(By.id(nameOrID)).clear();
//driver.findElement(By.id(nameOrID)).sendKeys(""+text);
}
catch(Exception ne){
ne.printStackTrace();
try{
jse.executeScript("document.getElementsByName('"+nameOrID+"')[0].value='"+text+"';");
//driver.findElement(By.name(nameOrID)).clear();
//driver.findElement(By.name(nameOrID)).sendKeys(""+text);
}
catch(Exception ne2){ne2.printStackTrace();}


}

/*
try{
jse.executeScript("document.getElementsByName('"+nameOrID+"')[0].style.display='block';");
driver.findElement(By.name(nameOrID)).clear();
driver.findElement(By.name(nameOrID)).sendKeys(""+text);
}
catch(Exception ne){ne.printStackTrace();}
*/

}

private static HashMap executeTest(String testFileName, int count) {
	
	//int waitTime = 30;
	boolean flag = false;
String testCaseName =null;


StringBuffer testCaseForReport=new StringBuffer();
StringBuffer imageHref=new StringBuffer();


	String testCaseList = FileManager.getContents(test_cases+"/"+testFileName);

testFileNameMap.put(new Integer(count), testFileName );

	String testCaseCommands[] = testCaseList.split(System.getProperty("line.separator"));
	

	

		for(int i=0; i<testCaseCommands.length; i++){
			

			String command = testCaseCommands[i];

							if(command.startsWith("name")){
							testCaseName = command.replaceAll("name=","").trim();
							testCaseNames.put(new Integer(count),testCaseName);
							testCaseStatus.put(testCaseName, new Boolean(flag));

						}


						else if(command.startsWith("component")){ //component
							String component = command.replaceAll("component=","").trim();
							componentMap.put(new Integer(count),component);
						}


else if(command.startsWith("role")){
							String role = command.replaceAll("role=","").trim();
							roleMap.put(new Integer(count),role);
						}


else if(command.startsWith("artifact")){
							String artifact = command.replaceAll("artifact=","").trim();
							artifactMap.put(new Integer(count),artifact);
						}


						//artifactMap


			else if(command.startsWith("wait")){
			try{
				waitTime = Integer.parseInt(command.replaceAll("wait=","").trim());
			}
			catch(Exception ne){}

			wait = new WebDriverWait(driver, waitTime);
			}

		else if(command.startsWith("url")){
			String urr=command.substring(4).trim();
		driver.get(baseURL+urr);
		
		testCaseForReport.append("<br>Click "+urr);
		}

		else{
			/**
			wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver webDriver) {
	                 return webDriver.findElement(By.tagName("body")) != null;
	            }
	        }); **/
		}

		if(command.startsWith("runJavaScriptPopToMain")){


			command = command.replaceAll("runJavaScriptPopToMain=","");

((JavascriptExecutor) driver).  
                executeScript(  
                        command  
                ); 



	  	  
	     Set<String> codeprojectWindowID  =driver.getWindowHandles();
   Iterator<String> it  = codeprojectWindowID.iterator();
 String MainWindowHandle = it.next();

//myTestDriver.findElement(By.xpath("//*[@id='ctl01_MC_MemberLogOn_OpenAuth']/a[2]/img")).click();

driver.switchTo().window(MainWindowHandle); // Switch back to parent window.



			testCaseForReport.append("<br>Run javascript</b>");

		}

		if(command.startsWith("runJavaScriptMainToPop")){


			command = command.replaceAll("runJavaScriptMainToPop=","");

((JavascriptExecutor) driver).  
                executeScript(  
                        command  
                ); 



	  
	     Set<String> codeprojectWindowID  =driver.getWindowHandles();
   Iterator<String> it  = codeprojectWindowID.iterator();
 String MainWindowHandle = it.next();
 String NewWindowHandle = it.next();

//myTestDriver.findElement(By.xpath("//*[@id='ctl01_MC_MemberLogOn_OpenAuth']/a[2]/img")).click();

driver.switchTo().window(NewWindowHandle);
driver.close();
driver.switchTo().window(MainWindowHandle); // Switch back to parent window.


			testCaseForReport.append("<br>Run javascript</b>");

		}


		if(command.startsWith("runCommands")){


command = command.replaceAll("runCommands=","");
commandOutput = RunCommands.runCommands(command, baseURL);


			testCaseForReport.append("<br>Run OS Script</b>");

		}

		//flag

		if(command.startsWith("verifyCommandOutput")){


command = command.replaceAll("verifyCommandOutput=","");

flag=commandOutput.indexOf(command)!=-1;

			if(flag)
			testCaseForReport.append("<br>"+command+" text found in command output.</b>");

			else
			testCaseForReport.append("<br>"+command+" text not found in command output.</b>");


		}


		if(command.startsWith("runJavaScriptOnSameWindow")){


			command = command.replaceAll("runJavaScriptOnSameWindow=","");

((JavascriptExecutor) driver).  
                executeScript(  
                        command  
                ); 




			testCaseForReport.append("<br>Run javascript</b>");

		}
			//custom=attribute=attributeValue=type=text

			if(command.startsWith("custom")){
			command = command.replaceAll("custom=","");
			String nameValue[] = command.split("=");


			if("type".equals(nameValue[2])){

				driver.findElement(By.xpath("//input[contains(@"+nameValue[0]+",'"+nameValue[1]+"')]")).clear(); // use this for clearing existing text
			driver.findElement(By.xpath("//input[contains(@"+nameValue[0]+",'"+nameValue[1]+"')]")).sendKeys(nameValue[3]);

			testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and enter <b>"+nameValue[3]+"</b>");
			}
//------------ for checkboxes

//custom=attribute=attributeValue=checkboxclick=index

	if("checkboxclick".equals(nameValue[2])){

int index=0;
try{ index=Integer.parseInt(nameValue[2]); } catch(Exception ne){}

List checkbox = driver.findElements(By.xpath("//input[contains(@"+nameValue[0]+",'"+nameValue[1]+"')]")); 
((WebElement) checkbox.get(index)).click();

	
			testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and click <b>"+nameValue[3]+"</b>");
			}

//custom=attribute=attributeValue=submit

if("submit".equals(nameValue[2])){


driver.findElement(By.xpath("//input[contains(@"+nameValue[0]+",'"+nameValue[1]+"')]")).submit(); 

	
			testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and submit </b>");
			}


//custom=attribute=attributeValue=radioclick

			
			

		}




		if(command.startsWith("typeElementByName")){
			command = command.replaceAll("typeElementByName=","");
			String nameValue[] = command.split("=");

			driver.findElement(By.name(nameValue[0])).clear(); // use this for clearing existing text
			driver.findElement(By.name(nameValue[0])).sendKeys(nameValue[1]);
			testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and enter <b>"+nameValue[1]+"</b>");

		}

if(command.startsWith("typeHiddenField")){
			command = command.replaceAll("typeHiddenField=","");
			String nameValue[] = command.split("=");

		typeHiddenField(nameValue[0],nameValue[1]);

			testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and enter <b>"+nameValue[1]+"</b>");

		}


if(command.startsWith("acceptJavaScriptConfirm")){
			command = command.replaceAll("acceptJavaScriptConfirm","");
		
	//Alert javascriptconfirm = driver.switchTo().alert();
    //javascriptconfirm.accept();


// driver.findElement(By.xpath("//input[@value = 'OK']")).click();
 try{
//Thread.sleep(2000L);
 }
 catch(Exception ne){}
 Alert javascriptconfirm = driver.switchTo().alert();
 javascriptconfirm.accept();

			
			testCaseForReport.append("<br>Click OK from confirmation dialog.</b>");

		}

		if(command.startsWith("cancelJavaScriptConfirm")){
			command = command.replaceAll("cancelJavaScriptConfirm","");

			
	Alert javascriptconfirm = driver.switchTo().alert();
    javascriptconfirm.dismiss();

			
			testCaseForReport.append("<br>Click cancel from confirmation dialog.</b>");

		}

		if(command.startsWith("clickImage")){
			

		command = command.replaceAll("clickImage=","");

		
		if(command.indexOf("=")!=-1) {
						String nameValue[] = command.split("=");

						int index=0;
						try{index=Integer.parseInt(nameValue[1].trim()); } catch(Exception ne){}

List checkbox = driver.findElements(By.xpath("//img[contains(@src,'"+command+"')]")); 
((WebElement) checkbox.get(index)).click();
		}
		else{
			WebElement div = driver.findElement(By.xpath("//img[contains(@src,'"+command+"')]"));
                             div.click();
			testCaseForReport.append("<br>Click "+command+" image.");
		}
		
		
		}

		if(command.startsWith("switchToPopupWindow")){

			  Set<String> codeprojectWindowID  =driver.getWindowHandles();
   Iterator<String> it  = codeprojectWindowID.iterator();
 String MainWindowHandle = it.next();
 String NewWindowHandle = it.next();

driver.switchTo().window(NewWindowHandle); // Switch back to parent window.


	}


		if(command.startsWith("switchToMainWindow")){

			  Set<String> codeprojectWindowID  =driver.getWindowHandles();
   Iterator<String> it  = codeprojectWindowID.iterator();
 String MainWindowHandle = it.next();
 String NewWindowHandle = it.next();

//myTestDriver.findElement(By.xpath("//*[@id='ctl01_MC_MemberLogOn_OpenAuth']/a[2]/img")).click();

driver.switchTo().window(NewWindowHandle);
driver.close();
driver.switchTo().window(MainWindowHandle); // Switch back to parent window.

		}

if(command.startsWith("withoutPopCloseSwitchToMainWindow")){

			  Set<String> codeprojectWindowID  =driver.getWindowHandles();
   Iterator<String> it  = codeprojectWindowID.iterator();
 String MainWindowHandle = it.next();
 //String NewWindowHandle = it.next();

//myTestDriver.findElement(By.xpath("//*[@id='ctl01_MC_MemberLogOn_OpenAuth']/a[2]/img")).click();

driver.switchTo().window(MainWindowHandle); // Switch back to parent window.

		}



if(command.startsWith("selectCheckBoxFromList")){
			command = command.replaceAll("selectCheckBoxFromList=","");
						String nameValue[] = command.split("=");

						int index=0;
						try{index=Integer.parseInt(nameValue[1].trim()); } catch(Exception ne){}

List checkbox = driver.findElements(By.name(nameValue[0])); 
((WebElement) checkbox.get(index)).click();
			testCaseForReport.append("<br>Click "+nameValue[0]+"("+index+") checkbox and click ");

		}


		if(command.startsWith("clickElementByName")){
			command = command.replaceAll("clickElementByName=","");
			driver.findElement(By.name(command)).click();
			testCaseForReport.append("<br>Find "+command+"(name) element and click ");

		}

		if(command.startsWith("submitElementByName")){
			command = command.replaceAll("submitElementByName=","");
			driver.findElement(By.name(command)).submit();
			testCaseForReport.append("<br>Find "+command+"(name) element and submit. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");

		}


//custom=attribute=attributeValue=1=inputType=text=type=text

/* adding ByCssSelector here */

if(command.startsWith("submitElementByCssSelector")){
			command = command.replaceAll("submitElementByCssSelector=","");
			driver.findElement(By.cssSelector(command)).submit();
			testCaseForReport.append("<br>Find "+command+"(name) element and submit. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");

		}

		if(command.startsWith("clickElementByCssSelector")){
			command = command.replaceAll("clickElementByCssSelector=","");
			//driver.findElement(By.XPath(command)).submit();



try{

int location =0;
					if(command.indexOf("=")!=-1) {
							String nameValue[] = command.split("=");

							try{  location=Integer.parseInt(nameValue[1]); }catch(Exception ne){}
					
				 List divTags = driver.findElements(By.xpath("//div[contains(@class, '"+nameValue[0]+"')]"));
					WebElement divTag = (WebElement)divTags.get(location);
				divTag.click();

					}
					else{

			       WebElement divTag = driver.findElement(By.xpath("//div[contains(@class, '"+command+"')]"));
				   divTag.click();

					}

}
catch(Exception ne2){

int location =0;
					if(command.indexOf("=")!=-1) {
							String nameValue[] = command.split("=");
								

							try{  location=Integer.parseInt(nameValue[2]); }catch(Exception ne){}
					
				 List divTags = driver.findElements(By.xpath("//"+nameValue[0]+"[contains(@class, '"+nameValue[1]+"')]"));
					WebElement divTag = (WebElement)divTags.get(location);
				divTag.click();

					}
					else{

			       ///WebElement divTag = driver.findElement(By.xpath("//i[contains(@class, '"+command+"')]"));
				  // divTag.click();

					}

}


			testCaseForReport.append("<br>Find "+command+"(name) element and submit. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
//string val1 = driver.FindElement(By.XPath("//div[contains(@class, 'errorblock'/ value = 'E024|E012|E014'")).Text;
		}


if(command.startsWith("dragElementToByCSS")){
			command = command.replaceAll("dragElementToByCSS=","");
		
			String nameValue[] = command.split("=");

			
			List<WebElement> divTags = driver.findElements(By.xpath("//div[contains(@class, '"+nameValue[0]+"')]"));
			WebElement someElement = null;
			 			 WebElement otherElement =null; // driver.findElement(By.xpath("//div[contains(@class, '"+nameValue[1]+"')]"));

			 for(WebElement elem1:divTags){


				 if(elem1.getText().indexOf(nameValue[1])!=-1) {
					 someElement = elem1;
					break;
				 }

				  

			 }

			 List<WebElement> divTags2 = driver.findElements(By.xpath("//div[contains(@class, '"+nameValue[2]+"')]"));
			 
			
			 for(WebElement elem1:divTags2){


				
				  if(elem1.getText().indexOf(nameValue[3])!=-1) {
					 otherElement = elem1;
					break;
				 }

			 }



		Actions builder = new Actions(driver);
/*
Action dragAndDrop = builder.clickAndHold(someElement)
   .moveToElement(otherElement)
   .release(otherElement)
   .build();
*/

//System.out.println("someElement "+someElement.getText());
//System.out.println("otherElement "+otherElement.getText());

builder.dragAndDrop(someElement,otherElement).build().perform();
 
//dragAndDrop.perform();


			testCaseForReport.append("<br>Drag "+nameValue[0]+"(CSS) element to "+nameValue[1]+" location. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
//string val1 = driver.FindElement(By.XPath("//div[contains(@class, 'errorblock'/ value = 'E024|E012|E014'")).Text;
		}


if(command.startsWith("clickSpanElementByCssSelector")){
			command = command.replaceAll("clickSpanElementByCssSelector=","");
			//driver.findElement(By.XPath(command)).submit();

					int location =0;
					if(command.indexOf("=")!=-1) {
							String nameValue[] = command.split("=");

							try{  location=Integer.parseInt(nameValue[1]); }catch(Exception ne){}
					
					//System.out.println("location "+location);

				 List divTags = driver.findElements(By.xpath("//span[contains(@class, '"+nameValue[0]+"')]"));
					WebElement divTag = (WebElement)divTags.get(location);
				divTag.click();

					}
					else{
WebElement divTag = driver.findElement(By.xpath("//span[contains(@class, '"+command+"')]"));
				   divTag.click();

					}


			   
			testCaseForReport.append("<br>Find "+command+"(name) element and click. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
//string val1 = driver.FindElement(By.XPath("//div[contains(@class, 'errorblock'/ value = 'E024|E012|E014'")).Text;
		}


		if(command.startsWith("clickCssSelectorAndValueForSpan")){
			command = command.replaceAll("clickCssSelectorAndValueForSpan=","");
			//driver.findElement(By.XPath(command)).submit();
							String nameValue[] = command.split("=");

					


					if(true){


						
				
				 List<WebElement> divTags = driver.findElements(By.xpath("//span[contains(@class, '"+nameValue[0]+"')]"));
					
					
					for(WebElement divTag:divTags) {
					//WebElement divTag = (WebElement)divTags.get(location);
	

					if(nameValue[1].equals(divTag.getText())) {
					divTag.click();
			testCaseForReport.append("<br>Find "+command+"(name) element and click. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
					break;
					}
					}
	

					}
					else{

			      		 WebElement divTag = driver.findElement(By.xpath("//span[contains(@class, '"+nameValue[0]+"')]"));
				

					if(nameValue[1].equals(divTag.getText())) {
					divTag.click();
			testCaseForReport.append("<br>Find "+command+"(name) element and click. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
					}

					}

//string val1 = driver.FindElement(By.XPath("//div[contains(@class, 'errorblock'/ value = 'E024|E012|E014'")).Text;
		}


if(command.startsWith("clickByCSSAndValue")){
			command = command.replaceAll("clickByCSSAndValue=","");
			//driver.findElement(By.XPath(command)).submit();
							String nameValue[] = command.split("=");

			       WebElement inputTag = driver.findElement(By.xpath("//input[contains(@class, '"+nameValue[0]+"')]"));

			   //System.out.println("getText()  "+inputTag.getText()); 

				
			testCaseForReport.append("<br>Find "+nameValue[0]+"(CSS) element and click. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");
//string val1 = driver.FindElement(By.XPath("//div[contains(@class, 'errorblock'/ value = 'E024|E012|E014'")).Text;
		}




/* Ending here */


		if(command.startsWith("submitElementById")){
			command = command.replaceAll("submitElementById=","");
			driver.findElement(By.name(command)).submit();
			testCaseForReport.append("<br>Find "+command+"(id) element and submit. If this current element is a form, or an element within a form, then this will be submitted to the remote server. ");

		}


		if(command.startsWith("searchElementValueByTagName")){
			command = command.replaceAll("searchElementValueByTagName=","");
			String nameValue[] = command.split("=");
			//flag=driver.findElement(By.tagName(nameValue[0])).getText().contains(nameValue[1].trim());

			testCaseForReport.append("<br>Find "+nameValue[0]+" tag and verify <b>"+nameValue[1]+"</b>. If true, mark this test case as passed.");


wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver webDriver) {
	                 return webDriver.findElement(By.tagName("body")) != null;
	            }
	        });

if(nameValue[1].trim().indexOf("#NEXT#")!=-1) {

String split[]=nameValue[1].trim().split("#NEXT#");
int passC=0;
for(int h=0; h<split.length; h++){

	
	
	String cont=driver.findElement(By.tagName("body")).getText().toLowerCase(); //driver.getPageSource().toLowerCase(); 
	


	//String pageSource=driver.findElement(By.tagName("body")).getText();
	cont=cont.replaceAll(" ","");
	cont=cont.replaceAll("\n","");

	String cont2=split[h]+"".toLowerCase().trim();
	cont2=cont2.replaceAll(" ","");
cont2=cont2.toLowerCase().trim();

flag = cont.contains(cont2);

if(flag)
passC+=1;

}

if(passC==split.length)
	flag=true;

else
	flag=false;


}
else{

String cont=driver.findElement(By.tagName("body")).getText().toLowerCase(); //driver.getPageSource().toLowerCase(); 	cont=cont.replaceAll(" ","");
	cont=cont.replaceAll("\n","");
	cont=cont.replaceAll(" ","");

	String cont2=nameValue[1]+"".toLowerCase().trim();
	cont2=cont2.replaceAll(" ","");
cont2=cont2.toLowerCase().trim();

flag = cont.contains(cont2);

//System.out.println("cont. "+cont);
//System.out.println("cont2 "+cont2);

//System.out.println("cont.indexOf(cont2) "+cont.indexOf(cont2));



//flag = driver.getPageSource().toLowerCase().contains(nameValue[1].trim()+"".toLowerCase());

}

			if(!flag){ // take screenshot
				String imageName = new String(testCaseName);
				imageName = imageName.replaceAll(" ","");

				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//System.out.println(scrFile);
				try{
				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/report/"+imageName+i+".png"));
				imageHref.append("\n<li><a href=\""+imageName+i+".png\" rel=\"gb_page_fs[]\">Page at Test Case [Find "+nameValue[0]+" tag and verify <b>"+nameValue[1]+"]</a></li>");


				}
				catch(Exception ne){ne.printStackTrace();}
			}

		}


			if(command.startsWith("selectOptionValueByIdValue")){
							command = command.replaceAll("selectOptionValueByIdValue=","");
							String nameValue[] = command.split("=");
				Select oSingleSelection = new Select(driver.findElement(By.id(nameValue[0])));
				oSingleSelection.selectByVisibleText(nameValue[1]);

							testCaseForReport.append("<br>Find "+nameValue[0]+" (id) element and select <b>"+nameValue[1]+"</b>.");

		
			}

			if(command.startsWith("selectOptionValueByNameValue")){
							command = command.replaceAll("selectOptionValueByNameValue=","");
							String nameValue[] = command.split("=");
				//Select oSingleSelection = new Select(driver.findElement(By.name(nameValue[0])));
				Select oSingleSelection = new Select(driver.findElement(By.xpath("//select[@name='"+nameValue[0]+"']")));

				
				oSingleSelection.selectByVisibleText(nameValue[1]);
											testCaseForReport.append("<br>Find "+nameValue[0]+" (name) element and select <b>"+nameValue[1]+"</b>.");

		
			}


if(command.startsWith("selectButtonByValue")){
		command = command.replaceAll("selectButtonByValue=","");
		WebElement oRadioBtn = driver.findElement(By.xpath("//input[@value='"+command+"']"));
    oRadioBtn.click();
				testCaseForReport.append("<br>Find "+command+"(value) element and click ");

			}


if(command.startsWith("clickHrefByLinkText")){
		command = command.replaceAll("clickHrefByLinkText=","");

/*
		wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver webDriver) {
	                 return webDriver.findElement(linkText(command)) != null;
	            }
	        });
			*/


			try { driver.findElement(By.linkText(command)).click(); }
			catch(Exception ne){
				try {Thread.sleep(5000);} catch(Exception ne2){}
				driver.findElement(By.linkText(command)).click();
			}
							testCaseForReport.append("<br>Find "+command+" href text and click ");


			}

			if(command.startsWith("clickHrefByURL")){
		command = command.replaceAll("clickHrefByURL=","");
					driver.findElement(By.xpath("//a[@href='"+command+"']")).click();
							testCaseForReport.append("<br>Find "+command+" href url and click ");

			}




			if(command.startsWith("selectOptionValueByIdIndex")){
							command = command.replaceAll("selectOptionValueByIdIndex=","");
							String nameValue[] = command.split("=");
				Select oSingleSelection = new Select(driver.findElement(By.id(nameValue[0])));
				
				try{
				int index = Integer.parseInt(nameValue[1]);
				oSingleSelection.selectByIndex(index);
				}
				catch(Exception ne){}
									testCaseForReport.append("<br>Find "+command+" (id) element and select "+nameValue[1]+" index");

			}

//

 if(command.startsWith("typeValueForFileField")){
       command = command.replaceAll("typeValueForFileField=","");

	   	   
		   if(command.indexOf("=")!=-1){
			   	   			String nameValue[] = command.split("=");

		   List<WebElement> fileFields = driver.findElements(By.xpath("//input[@type='file']"));
       
	  int index=0;
						try{index=Integer.parseInt(nameValue[0].trim()); } catch(Exception ne){}

WebElement fileField= ((WebElement) fileFields.get(index));
       fileField.sendKeys(nameValue[1]);



		   }
		   else{


       WebElement fileField = driver.findElement(By.xpath("//input[@type='file']"));
       fileField.sendKeys(command);
       testCaseForReport.append("<br>Type "+command+" in file field. ");

		   }

       
	   
	   }

	    if(command.startsWith("typeFileFieldValueForName")){
       command = command.replaceAll("typeFileFieldValueForName=","");
       
	   			String nameValue[] = command.split("=");

	   List<WebElement> fileFields = driver.findElements(By.xpath("//input[@type='file']"));
       
	   
	   for(WebElement fileField:fileFields) {


		   if(nameValue[0].equals(fileField.getAttribute("name"))) {
	//   System.out.println("Inside : "+fileField.getAttribute("name")+" Value "+nameValue[1]);

	

				fileField.sendKeys(nameValue[1]);
		   break;
		   }
	   }
      
	   testCaseForReport.append("<br>Type "+command+" in file field. ");
     
	   }
//

if(command.startsWith("typeElementById")){
			command = command.replaceAll("typeElementById=","");
			String nameValue[] = command.split("=");


			driver.findElement(By.id(nameValue[0])).clear(); // use this for clearing existing text
			driver.findElement(By.id(nameValue[0])).sendKeys(nameValue[1]);

			//javascript{this.browserbot.getCurrentWindow().document.getElementById('hiddenElementId').value='TheValue'}

						testCaseForReport.append("<br>Find "+nameValue[0]+" (id) element and enter <b>"+nameValue[1]+"</b>");

		}

		if(command.startsWith("clickElementById")){
			command = command.replaceAll("clickElementById=","");
			driver.findElement(By.id(command)).click();
						testCaseForReport.append("<br>Find "+command+"(id) element and click ");

		}
	if(command.startsWith("closeDriver")){
		driver.close();
	}
		

	if(command.startsWith("waitUntilById")){
			command = command.replaceAll("waitUntilById=","");
			final String idN=new String(command);
			 wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver webDriver) {
	                 return webDriver.findElement(By.id(idN)) != null;
	            }
	        });
						testCaseForReport.append("<br>Search "+command+"(id) element. Wait till the search element gets loaded. ");

		}

if(command.startsWith("wait")){
			command = command.replaceAll("wait=","");

try{
	int waitT = Integer.parseInt(command);
Thread.sleep(waitT);
}
catch(Exception ne){}

}

if(command.startsWith("waitUntilByName")){
			command = command.replaceAll("waitUntilByName=","");
final String idN=new String(command);
			 wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver webDriver) {
	                 return webDriver.findElement(By.name(idN)) != null;
	            }
	        });


						testCaseForReport.append("<br>Search "+command+"(name) element. Wait till the search element gets loaded. ");

		}
		//

		
		}


testCaseStatus.put(testCaseName, new Boolean(flag));
testCaseForReportMap.put(testCaseName,testCaseForReport.toString());
screenImages.put(testCaseName,imageHref.toString());

	HashMap retMap = new HashMap();
	
	    retMap.put("1",testCaseName); 
		retMap.put("2",new Boolean(flag));

return retMap;
	    }


		public static void selectValue(String valToBeSelected){
        List <WebElement> options = driver.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (valToBeSelected.equalsIgnoreCase(option.getText())){
				option.click();
			}
		    }
	}

public static void selectCheckBox(){
	WebElement kancheck = driver.findElement(By.name("sss"));
        kancheck.click();
   //    System.out.println(kancheck.isSelected());


}


public static void selectRadioButton(){
	WebElement gender = driver.findElement(By.xpath("//input[@name='male']"));
        gender.click();
		
   //   System.out.println(gender.isSelected());
	 

}

}