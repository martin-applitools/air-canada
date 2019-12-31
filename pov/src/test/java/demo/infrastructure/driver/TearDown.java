package pov.demo.infrastructure.driver;

import com.applitools.eyes.selenium.Eyes;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TearDown {

    private WebDriver driver;
    private Eyes eyes;

    public TearDown() {
        this.driver = Setup.driver;
        this.eyes = Setup.eyes;
    }

    @After
    public void quitDriver(Scenario scenario){
        this.driver.quit();
    }


}
