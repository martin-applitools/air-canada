package components;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;



public class LoginComponent {
    private WebDriver driver;

    //Locators
    private By loginModal = By.id("login2");
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton = By.className("btn btn-primary");     
    
    // Constructor
    public LoginComponent(WebDriver driver) {
        this.driver = driver;
    }
    public void login(){
        login(System.getProperty("customer.username"),
                System.getProperty("customer.password"));
    }

    public void login(String username, String password){
        driver.findElement(loginModal).click();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }  
}