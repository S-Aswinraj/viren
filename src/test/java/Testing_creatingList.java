import Pages.CreateListingPage;
import Pages.Homepage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Testing_creatingList extends base {
    @Test
    public void createListing() throws InterruptedException {
        //just to login in
        driver.get("https://staging-www.daltonsbusiness.com/login/?redirect_to=https://staging-www.daltonsbusiness.com/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        Homepage homepage = new Homepage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homepage.Email("abc-27apr_25@test.com");
        homepage.password(Password);
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@placeholder='Password *']"),Password));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homepage.Click_LoginAfterInputting();





        CreateListingPage createListingPage = new CreateListingPage(driver);
        driver.navigate().to("https://staging-www.daltonsbusiness.com/create-listing/");
        createListingPage.ListingHeading(Listing_headline);
        createListingPage.Content(Content);
        createListingPage.Status();

        createListingPage.Asking_price();
        createListingPage.Price(Price);
        createListingPage.Currency();
        createListingPage.property_status();
        createListingPage.Location(Location);
        createListingPage.Business_category(Business_category);

    }
}
