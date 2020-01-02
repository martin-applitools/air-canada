package demo.basepage;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.fluent.Target;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import demo.infrastructure.driver.Setup;
import demo.infrastructure.driver.Wait;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected Eyes eyes;
    protected Wait wait;
    protected RectangleSize viewport;
    //protected VisualGridRunner runner;
    protected ClassicRunner runner;


    public BasePage() {
        this.driver = Setup.driver;
        this.eyes = Setup.eyes;
        this.viewport = Setup.viewport;
        this.wait = new Wait(this.driver);
        this.runner = Setup.runner;
    }
}