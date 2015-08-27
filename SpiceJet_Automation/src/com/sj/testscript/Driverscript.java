package com.sj.testscript;
import com.sj.config.*;
import com.sj.datatable.XlsReader;
import com.sj.reports.ReportUtil;
import comm.sj.utils.TestUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import jxl.read.biff.BiffException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;



public class Driverscript {

	public static Properties CONFIG;
	public static Log log;
    public static Properties OBJECTS;
    public static XlsReader SJ_Testscript;
    public static XlsReader SJ_Testdata;
    public static String currentTest;
    public static String keyword;
    public static String currentTSID;
    public static String stepDescription;
    public static String datasheet;
    public static String test_data;
    public static String datavalue;
    public static String screen_shot;
    public static String object_name;
    public static String action;
    public static String expectedResult;
    public static String screenshotPath;
    public static String fileName;
    public static String testStatus;
    public static ChromeDriverService service;
    protected static WebDriver driver;
    
	
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeTest
  public void beforeTest() throws IOException {
	  //driver initialize
	    service =new ChromeDriverService.Builder().usingDriverExecutable(new File("C:\\Users\\Arunjunai\\Desktop\\Selenium\\chromedriver.exe")).usingAnyFreePort().build();
		service.start();
	  ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
	
		DesiredCapabilities capabilities=DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY,options);
		driver = new ChromeDriver(service,capabilities);	
	
  }

  @BeforeClass
  public void initialize() throws IOException {
	 CONFIG = new Properties();
	 OBJECTS= new Properties();
	 //Load Config.properties
	 FileInputStream fconfig= new FileInputStream(System.getProperty("user.dir")
	+ "//src//com//sj//config//Config.properties");
	// E:\workspace\SpiceJet_Automation\src\com\sj\config
	 CONFIG.load(fconfig);
	
	//Load Objects.properties
	 FileInputStream fobjects= new FileInputStream(System.getProperty("user.dir")
				+ "//src//com//sj//config//Objects.properties");
	 OBJECTS.load(fobjects);
	 screenshotPath = CONFIG.getProperty("screenshotPath");
	 System.out.println("a");
	 System.out.println(screenshotPath);
	 SJ_Testscript = new XlsReader(System.getProperty("user.dir")
				+ "\\src\\com\\sj\\config\\SJ_Testscript.xls");
	 SJ_Testdata = new XlsReader(System.getProperty("user.dir")
				+ "\\src\\com\\sj\\config\\SJ_Testdata.xls");
	 
    ReportUtil.startTesting(System.getProperty("user.dir")
			+ "\\src\\com\\sj\\reports\\index.html" ,
			TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
			CONFIG.getProperty("env"), CONFIG.getProperty("version"),
			CONFIG.getProperty("testbrowser"),
			CONFIG.getProperty("testsiteurl"));
   
	
    
  }
  @Test
  public void TestApplication() throws BiffException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	  String startTime = null;
	  String firstsheetname = SJ_Testscript.getFirstsheetname();
	  ReportUtil.startSuite(firstsheetname);
	  for(int TCID = 1;TCID < SJ_Testscript.getRowcount(firstsheetname);TCID++){
		  currentTest = SJ_Testscript.getCelldata(firstsheetname,"TC_ID",TCID);
		  if(SJ_Testscript.getCelldata(firstsheetname, "Execution_Flag", TCID).equals("Y")){
			 //Load test data sheet and iterate corresponding sheet of TC
			 // datasheet = SJ_Testdata.getRowcount(currentTest);
			  log.debug("Executing First test");
				startTime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
			
				// It will find the Keyword
				for (int tsid = 1; tsid < SJ_Testscript.getRowcount(currentTest); tsid++)
				{
					test_data =  SJ_Testscript.getCelldata(currentTest, "Data_column_Name",
							tsid);
						
					
					System.out.println("Inside for loop");

					// values from xls
					// Stores the current keyword
					
					//datasheet column name
					if(!SJ_Testdata.getCellData(firstsheetname,test_data).equals("")){
						datavalue  = SJ_Testdata.getCellData(firstsheetname,test_data);
					}else{
						datavalue="";
					}
					

					System.out.println(datavalue);

					//screenshot flag
					screen_shot =  SJ_Testscript.getCelldata(currentTest, "Screenshot",
							tsid);
					
					//object_name
					object_name =  SJ_Testscript.getCelldata(currentTest, "Object_Name",
							tsid);
					
					//action (similar to keyword)
					action =  SJ_Testscript.getCelldata(currentTest, "Action",
							tsid);

					keyword = SJ_Testscript.getCelldata(currentTest, "Keyword",
							tsid);
					System.out.println(keyword);

					// Stores the current TSID
					currentTSID = SJ_Testscript.getCelldata(currentTest, "TSID",
							tsid);
					System.out.println(currentTSID);

					// Stores the current description
					stepDescription = SJ_Testscript.getCelldata(currentTest,
							"Test_Steps", tsid);

					System.out.println(stepDescription);
					
					expectedResult = SJ_Testscript.getCelldata(currentTest,
							"Expected_Result", tsid);

					System.out.println(expectedResult);
					
				Method method = Keywords.class.getMethod(keyword);
				String result = (String) method.invoke(method,object_name,datavalue);
				//	String result = Keywords.Operation(action,datavalue,object_name);
				
					if(screen_shot.equals('Y') && screen_shot.equals(' ') )
					{
						//take screenshot
						 fileName = "Suite1_TC" + TCID + "_TS" + tsid
						+ "_" + keyword + ".jpg";
						String path = screenshotPath + fileName;
						TestUtil.takeScreenShot(path);
					}
					else if(screen_shot.equals('N'))
					{
						break;
					}
					
					if(result.equals("Pass")){
						ReportUtil.addKeyword(stepDescription, keyword,
								result, fileName);
					}
					else if(result.equals("Fail")){
						ReportUtil.addKeyword(stepDescription, keyword,
								result, fileName);
					}
				}
				// Report pass or fail
				if (testStatus == null) {
					testStatus = "Pass";
				}

				log.debug("***********************************"
						+ currentTest + " --- " + testStatus);

				ReportUtil.addTestCase(currentTest, startTime,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			else {

				log.debug("Skipping the test : " + currentTest);
				System.out.println("Skipping the test : " + currentTest);
				testStatus = "Skip";

				// Report skipped
				log.debug("***********************************"
						+ currentTest + " --- " + testStatus);
				ReportUtil.addTestCase(currentTest,
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
						TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), testStatus);

			}

			testStatus = null;

		}

		// End test reporting
		ReportUtil.endSuite();

		  }
	  
  
  

  @AfterClass
  
	  public static void endScript() {
			// Update test end time under HTML test report
			ReportUtil.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));

			log.debug("\nTest run finished");
			System.out.println("\nTest run finished");
  }

 
  @AfterTest
  public void afterTest() {
	  service.stop();
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
