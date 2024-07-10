package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateListingPage {
    WebDriver driver=null;
    public CreateListingPage(WebDriver driver){
        this.driver =driver;
    }


    //Locators
    By ListingHeading = By.xpath("//*[@id='prop_title']");
    By Content = By.id("prop_des");
    By Status =  By.xpath("//*[@class='btn dropdown-toggle btn-light bs-placeholder' and @aria-owns='bs-select-1']");
    By Status_forSale = By.xpath("//*[@id='bs-select-1-0']");
    By Business_Category= By.name("prop_type[]");
    By Business_category_Cafes= By.xpath("//*[text()='Cafes']");
    By Asking_price= By.xpath("(//span[@class='control__indicator'])[1]");
    By Price = By.id("prop_price");
    By Currency = By.xpath("//*[@value='USD']");
    By property_status_NA = By.xpath("//*[@value='N/A']");
    By Location = By.id("property_city");
    By ShadowRoot = By.xpath("//*[@id='getsitecontrol-346111']");
    By Next = By.xpath("//button[@id='save_and_next']");

    //Methods
    public void ListingHeading(String text){
        driver.findElement(ListingHeading).sendKeys(text);
    }
    public  void Content(String text){
        driver.findElement(Content).sendKeys(text);
    }
    public void Status(){
        driver.findElement(Status).sendKeys("F");
        driver.findElement(Status_forSale).click();
    }
    public  void Business_category_input(String text){
        driver.findElement(Business_Category).sendKeys(text);

    }
    public void Business_category_select(){
        Actions actions = new Actions(driver);
        WebElement ele = driver.findElement(Business_category_Cafes);
        actions.moveToElement(ele).tick().perform();

    }
    public void Asking_price (){
        Actions actions = new Actions(driver);

        WebElement ele = driver.findElement(Asking_price);
       actions.click(ele).perform();
    }
    public void Price(String valueInNumber){
        driver.findElement(Price).sendKeys(valueInNumber);
    }
    public void Currency(){
        Actions actions = new Actions(driver);
        WebElement a = driver.findElement(Currency);
        actions.moveToElement(a).click().perform();
    }
    public void property_status(){
        driver.findElement(property_status_NA);
    }
    public void Location(String text){
        driver.findElement(Location).sendKeys(text);
    }
    public void Close_widget(){
       WebElement element = (WebElement) driver.findElement(ShadowRoot).getShadowRoot()
               .findElement(By.cssSelector(".close"));
       element.click();
    }



}
