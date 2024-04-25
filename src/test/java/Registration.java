import Utility.Generate_randomNum;
import Utility.Report;
import Utility.TestDataReader;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Listeners(ExtentITestListenerClassAdapter.class)
public class Registration extends Report {
    RemoteWebDriver driver;

    Properties prop;
    int Num;




    String FName ;
    String LName ;
    String Mobile_num ;
    //        String Email=prop.getProperty("Email");
    String Password ;
    String Select_visibletext;
    String Email ;
    String browser;
    String Id;

    //this code is to initialize wait
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public void startBrowserAndLoadTestData(){
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



    }



    @Test(priority = 0)
    public void registration() throws InterruptedException {


        Homepage homepage = new Homepage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://staging-www.daltonsbusiness.com/");


        //executing
        homepage.registration();
        homepage.FirstName(FName);
        homepage.LastName(LName);
        homepage.Mobile(Mobile_num);
        homepage.BuyorSell(Select_visibletext);
        homepage.Email(Email);
        homepage.password(Password);
        homepage.ClickAcceptTerm();
        homepage.Click_registration();

//        stepReport("pass","user inputted basic information");

        LoggedInUser_Homepage loggedInUser_homepage = new LoggedInUser_Homepage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        loggedInUser_homepage.myAccountTab();
        loggedInUser_homepage.profile();

        //getting the detail and asserting
        String First_name = driver.findElement(By.xpath("//*[@name='firstname']")).getAttribute("value");
        String Last_name = driver.findElement(By.xpath("//*[@name='lastname']")).getAttribute("value");
        String Phone = driver.findElement(By.xpath("//*[@name='usermobile']")).getAttribute("value");
        Id = driver.findElement(By.xpath("//*[@name='useremail']")).getAttribute("value");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(First_name,FName);
        softAssert.assertEquals(Last_name,LName);
        softAssert.assertEquals(Phone,Mobile_num);
        softAssert.assertEquals(Id,Email);
        softAssert.assertAll();
//        stepReport("pass","Asserting inputted basic information");

        homepage.Logut();

        System.out.println(Id);


    }

    @Test(priority = 1)
    public void Loggedinto(){
        Homepage homepage = new Homepage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homepage.Click_loginButton();
        homepage.Email(Id);
        homepage.password(Password);
        homepage.Click_LoginAfterInputting();

        LoggedInUser_Homepage loggedInUser_homepage = new LoggedInUser_Homepage(driver);
        loggedInUser_homepage.myAccountTab();
        loggedInUser_homepage.profile();


        String First_name = driver.findElement(By.xpath("//*[@name='firstname']")).getAttribute("value");
        String Last_name = driver.findElement(By.xpath("//*[@name='lastname']")).getAttribute("value");
        String Phone = driver.findElement(By.xpath("//*[@name='usermobile']")).getAttribute("value");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(First_name,FName);
        softAssert.assertEquals(Last_name,LName);
        softAssert.assertEquals(Phone,Mobile_num);
        softAssert.assertEquals(Id,Email);
        softAssert.assertAll();

//        stepReport("pass","Logging in and verifying the details");

        homepage.Logut();




    }
    @AfterTest
   public void CloseBrowser(){
       driver.quit();

   }
}




