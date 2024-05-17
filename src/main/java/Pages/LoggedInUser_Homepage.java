package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInUser_Homepage {
    WebDriver driver =null;
    public LoggedInUser_Homepage(WebDriver driver){
        this.driver =driver;
    }

    //Locator
    By myAccount_tabButton = By.xpath("//*[@class='dropdown-toggle']") ;
    By profile_Button = By.xpath("(//*[@class='side-menu-item'])[8]");


    //methods
    public void myAccountTab() {
        driver.findElement(myAccount_tabButton).click();
    }

    public void profile(){
        driver.findElement(profile_Button).click();
    }

}
