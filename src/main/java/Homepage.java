import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Homepage {
    WebDriver driver=null;
    Homepage(WebDriver driver){
        this.driver =driver;
    }


    //Locators
    By Registration = By.className("register-link");
    By FirstName = By.xpath("//*[@placeholder='First Name *']");
    By LastName = By.xpath("//*[@placeholder='Last Name *']");
    By Telephone = By.xpath("//*[@placeholder='Telephone *']");
    By BuyorSell = By.xpath("//*[@class='form-group-field' and @name='role']");
    By Email = By.xpath("//*[@placeholder='Email *']");
    By Password = By.xpath("//*[@placeholder='Password *']");
    By acceptTerms = By.xpath("(//*[@class='control__indicator'])[3]");
    By register_button = By.xpath("//*[@data-event-label='Register']");
    By Login = By.xpath("//*[@class='btn btn-icon-login-register']");
    By Login_Afterinputting = By.xpath("//*[@data-event-label='User login button']");
    By Logut_button = By.xpath("(//i[@class='houzez-icon icon-lock-5 mr-2'])[2]");


    //Method
    public void registration(){
        driver.findElement(Registration).click();
    }

    public void FirstName(String text){
        driver.findElement(FirstName).sendKeys(text);
    }
    public void LastName(String text){
        driver.findElement(LastName).sendKeys(text);
    }
    public void Mobile(String Number){
        driver.findElement(Telephone).sendKeys(Number);
    }
    public void BuyorSell(String text){
         Select select = new Select(driver.findElement(BuyorSell));
         select.selectByVisibleText(text);
    }
    public void Email(String text){
        driver.findElement(Email).sendKeys(text);
    }
    public void password(String text){
        driver.findElement(Password).sendKeys(text);
    }


    public void ClickAcceptTerm(){
        WebElement a = driver.findElement(acceptTerms);
        Actions actions = new Actions(driver);

        actions.moveToElement(a).click().perform();

    }




    public  void Click_registration() {

    WebElement r = driver.findElement(register_button);
        Actions actions = new Actions(driver);
    actions.moveToElement(r).click().perform();
    }

    public void Click_loginButton(){

        driver.findElement(Login).click();
    }

    public void Click_LoginAfterInputting(){
        driver.findElement(Login_Afterinputting).click();
    }
    public void Logut(){
        driver.findElement(Logut_button).click();
    }

}
