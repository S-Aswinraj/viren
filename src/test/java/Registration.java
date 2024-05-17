
import Pages.Homepage;
import Pages.LoggedInUser_Homepage;
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

        driver.get("https://staging-www.daltonsbusiness.com/");
        test.info("navigated to url");


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
        homepage.Click_registration();

        test.info("inputted user details to register");



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

        test.info("Asserted all the inputted details");



        homepage.Logut();

        System.out.println(Id);


    }

    @Test(priority = 1)
    public void Loggedinto() {
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
        softAssert.assertEquals(First_name, FName);
        softAssert.assertEquals(Last_name, LName);
        softAssert.assertEquals(Phone, Mobile_num);
        softAssert.assertEquals(Id, Email);
        softAssert.assertAll();

//        stepReport("pass","Logging in and verifying the details");

        homepage.Logut();


    }

}





