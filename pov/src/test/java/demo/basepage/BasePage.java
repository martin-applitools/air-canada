package demo.basepage;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import demo.infrastructure.driver.Setup;
import demo.infrastructure.driver.SetupVG;
import demo.infrastructure.driver.Wait;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected Eyes eyes;
    protected Eyes eyesVG;
    protected Wait wait;
    protected RectangleSize viewport;
    protected VisualGridRunner runnerVG;
    protected ClassicRunner runner;


    public BasePage() {
        this.driver = Setup.driver;
        this.eyes = Setup.eyes;
        this.viewport = Setup.viewport;
        this.wait = new Wait(this.driver);
        this.runner = Setup.runner;
    }
    public void BasePageVG() {
        this.driver = SetupVG.driver;
        this.eyesVG = SetupVG.eyesVG;
        this.viewport = SetupVG.viewport;
        this.wait = new Wait(this.driver);
        this.runnerVG = SetupVG.runnerVG;
    }
}