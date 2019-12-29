package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaptopCategoryPage {

    private By vaioi5 = By.xpath("//*[@id='tbodyid']/div[1]/div/div/h4/a");
    private By vaioi7 = By.xpath("//*[@id='tbodyid']/div[2]/div/div/h4/a");
    private By macbookair = By.xpath("//*[@id='tbodyid']/div[3]/div/div/h4/a");
    private By delli7 = By.xpath("//*[@id='tbodyid']/div[4]/div/div/h4/a");
    private By dell = By.xpath("//*[@id='tbodyid']/div[5]/div/div/h4/a");
    private By macbookpro = By.xpath("//*[@id='tbodyid']/div[6]/div/div/h4/a");

    private WebDriver driver;

    public LaptopCategoryPage(WebDriver driver){
        this.driver = driver;

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(vaioi5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(delli7));
    }

}
