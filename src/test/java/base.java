import Utility.Generate_randomNum;
import Utility.TakeScreenshot;
import Utility.TestDataReader;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class base {
    RemoteWebDriver driver;
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;

    Properties prop;
    int Num;
    String browser;

    String FName;
    String LName;
    String Mobile_num;
    //        String Email=prop.getProperty("Email");
    String Password;
    String Select_visibletext;
    String Email;
    String Id;
    String Listing_headline;
    String Content;
    String Status;
    String Business_category;
    String Price;
    String Location;

    @BeforeSuite
    public void startBrowserAndLoadTestData(ITestContext context) {
        //AllTest.html
        extent = new ExtentReports();
        spark  = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);


//        String desc=" registration ";
//        String author ="ABC";
        TestDataReader dataReader = new TestDataReader();
        prop = dataReader.init_prop();
        Generate_randomNum Gr = new Generate_randomNum();
        Num = Gr.getRandomValue(1, 99);

        browser = prop.getProperty("browser");



        switch (browser) {
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
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;


        }
        //loading test data from property file
        FName = prop.getProperty("FName");
        LName = prop.getProperty("LName");
        Mobile_num = prop.getProperty("Mobile_num");
//        String Email=prop.getProperty("Email");
        Password = prop.getProperty("Password");
        Select_visibletext = prop.getProperty("Select_visibletext");

        Listing_headline = prop.getProperty("Listing_headline");
        Content = prop.getProperty("Content");
        Status = prop.getProperty("Status");
        Business_category = prop.getProperty("Business_category");
        Price = prop.getProperty("Price");
        Location = prop.getProperty("Location");






        // creating unique email id everytime
        Email = "abc-27apr_" + Num + "@test.com";

        ExtentTest test = extent.createTest("Test data");
        test.info(FName);
        test.info(LName);
        test.info(Mobile_num);
        test.info(Password);
        test.info(Email);


    }

    @AfterSuite
    public void CloseBrowser() throws IOException {
        extent.flush();
//        driver.quit();
//        Desktop.getDesktop().browse(new File("target/Spark/Spark.html").toURI());

    }



//    public void initialiseReport() {
////        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("AllTest.html");
////        extentReports = new ExtentReports();
////        extentReports.attachReporter(sparkReporter);
////
////        //AllTest.html
//         extent = new ExtentReports();
//         spark  = new ExtentSparkReporter("target/Spark/Spark.html");
//        extent.attachReporter(spark);
//
//    }

//    @AfterClass
//    public void checkStatus(Method m, ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            //need to add code to capture screenshot
//
//            test.fail(result.getThrowable());
//
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.pass(m.getName() + " is passed");
//
//        }
//    }

}







