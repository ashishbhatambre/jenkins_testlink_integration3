package com.example.tests;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import com.thoughtworks.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.*;
import testlink.api.java.client.TestLinkAPIResults;




public class login implements IConstantes{

	static String resultado = null;
	  static String nota = null;
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	 // @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://cu156.cloud.maa.collab.net/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  //@Test
	  public void testLogin() throws Exception {
		 
	    driver.get(baseUrl + "/sf/sfmain/do/home");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("admin");
	    driver.findElement(By.cssSelector("strong")).click();
	 // Warning: verifyTextPresent may require manual changes
	    try {
	      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*My Workspace[\\s\\S]*$"));
	      System.out.println("passed");
	      resultado = TestLinkAPIResults.TEST_PASSED;
	    } catch (Error e) {
	    	System.out.println("failed");
	    	resultado = TestLinkAPIResults.TEST_FAILED;
	    	nota = e.getMessage();
	      verificationErrors.append(e.toString());
	    }
	   finally {
	    	ResultadoExecucao.reportTestCaseResult1(PROJETO, PLANO, CASO_TESTE1, BUILD, nota, resultado);
	    	}
	  }
	  

	  //@After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	 public login()  {
		// TODO Auto-generated method stub
		 
           login lo=new login();
           try{
           //ResultadoExecucao re= new ResultadoExecucao();
           //re.reportTestCaseResult1(PROJETO, PLANO, CASO_TESTE1, BUILD, nota, resultado);
           
           lo.setUp();
           lo.testLogin();
           lo.tearDown();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 }
	}



