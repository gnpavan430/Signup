package setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utilities.HTMLUtility;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class NewSetUp {
    public static WebDriver driver;
    PropertyUtils properties;
    public static Logger logger;
    public static int testCasesCount=0;
    public static HashMap<Object,Object> testdata;
    ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest loggerExtent;

    public static String screenshotsPath=System.getProperty("user.dir") + File.separator + "screenshots\\";
    public static String logPath = System.getProperty("user.dir") + File.separator + "logs\\";
    TestCaseScenario testCaseScenario;
    TestCaseExecutor testCaseExecutor;
    //In this method the driver initialization and property files initialization is done
    @BeforeMethod
    public void setUp() throws IOException {
        properties= new PropertyUtils();
        System.setProperty("webdriver.chrome.driver", properties.getPropertyValue("chromeDriverPath"));
        driver=new ChromeDriver();
        driver.get(properties.getPropertyValue("appURL"));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        logger = Logger.getLogger("NewSetUp.class");
        PropertyConfigurator.configure(properties.getPropertyValue("log4jproperties"));


    }
    @BeforeTest
    public void startExtentReport(){

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\test-output\\STMExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "TestAutomation");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Pavan");

        htmlReporter.config().setDocumentTitle("Signup Regression Report");
        htmlReporter.config().setReportName("Regression Testing Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    //This method starts creating HTML template
    @BeforeClass
    public void startReport() throws IOException {
        HTMLUtility.createHTMLTemplate();
    }
    //This method prepares the test automation report, based on test result. It generates html report and also adds log files for each and every test cases
    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        properties= new PropertyUtils();
        testCasesCount=testCasesCount+1;
        TestCaseScenario testCaseScenario=TestCaseExecutor.getTestScenario(result.getName());
        if(result.getStatus()==ITestResult.SUCCESS){

            File file = new File(properties.getPropertyValue("logFilePath"));
            File newFile = new File(logPath+result.getName()+"\\");
            FileUtils.copyFile(file,newFile);
            clearFileData(file);
            HTMLUtility.testCaseExecutionStatus(testCasesCount,testCaseScenario.getName(),"Pass",screenshotsPath+result.getName()+"\\",newFile);
        }
        else if(result.getStatus()==ITestResult.FAILURE){
            File file = new File(properties.getPropertyValue("logFilePath"));
            File newFile = new File(logPath+result.getName()+"\\");
            FileUtils.copyFile(file,newFile);
            clearFileData(file);
            HTMLUtility.testCaseExecutionStatus(testCasesCount,testCaseScenario.getName(),"Fail",screenshotsPath+result.getName()+"\\",newFile);
            loggerExtent.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            loggerExtent.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
        else if(result.getStatus()==ITestResult.SKIP){
            File file = new File(properties.getPropertyValue("logFilePath"));
            File newFile = new File(logPath+result.getName()+"\\");
            FileUtils.copyFile(file,newFile);
            clearFileData(file);
            HTMLUtility.testCaseExecutionStatus(testCasesCount,testCaseScenario.getName(),"No Run",screenshotsPath+result.getName()+"\\",newFile);
            loggerExtent.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));

        }


    }

    //This method is used for clearing the log file data. Log file data is cleared to get separate log file for each test case run
    public void clearFileData(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.write("");
        pw.flush();
        pw.close();
        fw.close();
    }
    //This method takes the screenshot and returns the path of the screenshot
    public static String getScreenshot(WebDriver driver, String screenshotName,String methodName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder

        String destination = System.getProperty("user.dir") + File.separator + "screenshots/"+methodName+"/"+screenshotName+".jpeg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    //This method takes the screenshot and returns the path of the screenshot
    public static String getScreenshotExtent(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder

        String destination = System.getProperty("user.dir") + File.separator + "screenshots\\"+screenshotName+".jpeg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    //This method is for quitting the driver
    @AfterMethod
    public void atearDown()
    {
        driver.quit();
    }
    //This method is to end the HTML report
    @AfterClass
    public void endReport() throws IOException {
        HTMLUtility.closeHTMLReport();
    }
    @AfterTest
    public void endExtentReport(){
        extent.flush();
    }
}
