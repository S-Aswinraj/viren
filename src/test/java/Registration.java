
import Pages.CreateListingPage;
import Pages.Homepage;
import Pages.LoggedInUser_Homepage;
import Utility.TakeScreenshot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class Registration extends base {



    @Test(priority = 0)
    public void registration() throws InterruptedException {


        Homepage homepage = new Homepage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentTest test = extent.createTest("Registration");


        driver.get("https://staging-www.daltonsbusiness.com/");


        WebElement a = null;
        try{
            a=driver.findElement(homepage.Consent);
        }
        catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Consent is not found, proceeding..");
        }


        if(a!=null){
            homepage.Click_consent();
        }

        //executing
        homepage.registration();
        homepage.FirstName(FName);
        homepage.LastName(LName);
        homepage.Mobile(Mobile_num);
        homepage.BuyorSell(Select_visibletext);
        homepage.Email(Email);
        homepage.password(Password);
        homepage.ClickAcceptTerm();
        //code to add screenshot
        TakeScreenshot A = new TakeScreenshot(driver);
        A.Screenshot("Registration"+Num);


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
        softAssert.assertEquals(First_name, FName);
        softAssert.assertEquals(Last_name, LName);
        softAssert.assertEquals(Phone, Mobile_num);
        softAssert.assertEquals(Id, Email);
        softAssert.assertAll();
//        stepReport("pass","Asserting inputted basic information");
        test.addScreenCaptureFromPath("C:\\Users\\ashwinraj.s\\Desktop\\viren\\target\\Spark\\Registration"+Num+".png")
                .pass(MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\ashwinraj.s\\Desktop\\viren\\target\\Spark\\Registration"+Num+".png").build());
        test.pass("User registered");







        homepage.Logut();

        System.out.println(Id);


    }

    @Test(priority = 1)
    public void Loggedinto() {

        Homepage homepage = new Homepage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentTest test = extent.createTest("My profile");


        homepage.Click_loginButton();
        homepage.Email(Id);
        homepage.password(Password);
        homepage.Click_LoginAfterInputting();

        LoggedInUser_Homepage loggedInUser_homepage = new LoggedInUser_Homepage(driver);
        test.pass("User logged In");
        loggedInUser_homepage.myAccountTab();

        loggedInUser_homepage.profile();

        //code to add screenshot
        TakeScreenshot A = new TakeScreenshot(driver);
        A.Screenshot("Profile"+Num);



        String First_name = driver.findElement(By.xpath("//*[@name='firstname']")).getAttribute("value");
        String Last_name = driver.findElement(By.xpath("//*[@name='lastname']")).getAttribute("value");
        String Phone = driver.findElement(By.xpath("//*[@name='usermobile']")).getAttribute("value");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(First_name, FName);
        softAssert.assertEquals(Last_name, LName);
        softAssert.assertEquals(Phone, Mobile_num);
        softAssert.assertEquals(Id, Email);
        softAssert.assertAll();
        test.info("User details verified");
        test.pass("passed");


//        stepReport("pass","Logging in and verifying the details");

//        homepage.Logut();
        test.addScreenCaptureFromPath("C:\\Users\\ashwinraj.s\\Desktop\\viren\\target\\Spark\\Profile"+Num+".png")
                .pass(MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\ashwinraj.s\\Desktop\\viren\\target\\Spark\\Profile"+Num+".png").build());
        test.pass("log out");



    }
@Test(priority = 2)
    public void createListing() throws InterruptedException {
        //just to login in
    Homepage homepage = new Homepage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get("https://staging-www.daltonsbusiness.com/login/?redirect_to=https://staging-www.daltonsbusiness.com/");
    homepage.Email("abc-27apr_25@test.com");
    homepage.password(Password);
    driver.wait();
    homepage.Click_LoginAfterInputting();

        driver.get("https://staging-www.daltonsbusiness.com/create-listing/");
        CreateListingPage createListingPage = new CreateListingPage(driver);
        createListingPage.ListingHeading(Listing_headline);
        createListingPage.Content(Content);
        createListingPage.Status();
        createListingPage.Business_category(Business_category);
        createListingPage.Asking_price();
        createListingPage.Price(Price);
        createListingPage.Currency();
        createListingPage.property_status();
        createListingPage.Location(Location);

    }

}





