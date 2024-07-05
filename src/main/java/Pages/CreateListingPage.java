package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    By Asking_price= By.id("askingPrice");
    By Price = By.id("prop_price");
    By Currency = By.xpath("//*[@value='USD']");
    By property_status_NA = By.xpath("//*[@value='N/A']");
    By Location = By.id("property_city");

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
    public  void Business_category(String text){
        driver.findElement(Business_Category).sendKeys(text);
        driver.findElement(Business_category_Cafes).click();
    }
    public void Asking_price (){
        driver.findElement(Asking_price).click();
    }
    public void Price(String valueInNumber){
        driver.findElement(Price).sendKeys(valueInNumber);
    }
    public void Currency(){
        driver.findElement(Currency).click();
    }
    public void property_status(){
        driver.findElement(property_status_NA);
    }
    public void Location(String text){
        driver.findElement(Location).sendKeys(text);
    }



}
