package com.pov.infrastructure.driver;


import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.selenium.fluent.*;
import cucumber.api.junit.Cucumber;
import io.cucumber.java.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup {

    public static WebDriver driver;
    public static ClassicRunner runner = new ClassicRunner();
    public static Eyes eyes = new Eyes();
    public static RectangleSize viewport = new RectangleSize(1300, 900);

    private static Long unixTime = System.currentTimeMillis();
    private static String batchId = Long.toString(unixTime);

    @Before
    public void setWebDriver() throws Exception {
        eyes.setLogHandler(new FileLogger(true));
        Configuration sconf = new Configuration();
        sconf.setApiKey("R0LKZ5VS9nB4Eq5L5lr2ilDxF5Jl716kNgSxxY9wJ8o110");
        eyes.s
        sconf.setLogHandler(new StdoutLogHandler(true));
        eyes.setForceFullPageScreenshot(true);
        eyes.setStitchMode(StitchMode.CSS);

        System.out.println("My Batch Id: " + batchId);

        BatchInfo batch = new BatchInfo("Java Cucumber Test");
        batch.setId(batchId);
        eyes.setBatch(batch);

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }
}
