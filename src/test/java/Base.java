import Utility.Generate_randomNum;
import Utility.TestDataReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class Base  {
    RemoteWebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest test;



    Properties prop;
    int Num;
    String browser;

    String FName ;
    String LName ;
    String Mobile_num ;
    //        String Email=prop.getProperty("Email");
    String Password ;
    String Select_visibletext;
    String Email ;
    String Id;


    @BeforeSuite
    public void initialiseReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("AllTest.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);




    }


    @BeforeTest
    public void startBrowserAndLoadTestData(ITestContext context){


//        String desc=" registration ";
//        String author ="ABC";
        TestDataReader dataReader = new TestDataReader();
        prop = dataReader.init_prop();
        Generate_randomNum Gr = new Generate_randomNum();
        Num =Gr.getRandomValue(1,99);

        browser = prop.getProperty("browser");


        switch (browser){
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(option);
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                break;


        }
        //loading test data from property file
        FName = prop.getProperty("FName");
        LName = prop.getProperty("LName");
        Mobile_num = prop.getProperty("Mobile_num");
//        String Email=prop.getProperty("Email");
        Password = prop.getProperty("Password");
        Select_visibletext = prop.getProperty("Select_visibletext");

        // creating unique email id everytime
        Email = "abc-25apr_"+ Num +"@test.com";

        test = extentReports.createTest(context.getName());

    }
    @AfterClass
    public  void checkStatus(Method m, ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE ){
            //need to add code to capture screenshot

            test.fail(result.getThrowable());

        }
        else if (result.getStatus() == ITestResult.SUCCESS){
            test.pass(m.getName()+ " is passed");

        }

    }


    @AfterSuite
    public void CloseBrowser() throws IOException {
        extentReports.flush();
        driver.quit();
        Desktop.getDesktop().browse(new File("AllTest.html").toURI());

    }
}

