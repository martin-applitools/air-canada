package pov.demo.infrastructure.driver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.MatchLevel;


import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup {

    public static WebDriver driver;
    public static Eyes eyes = new Eyes();
    public static RectangleSize viewport = new RectangleSize(1200, 800);

    private static Long unixTime = System.currentTimeMillis();
    private static String batchId = Long.toString(unixTime);

    @Before
    public void setWebDriver() throws Exception {
        String eyesConfig = System.getProperty("eyesConfig");
        if (eyesConfig == null) {
            eyesConfig = "local";
        }
        switch (eyesConfig) {
            case "local":
                eyes.setApiKey("SZT3IpNet90iVi7HVGwsJSzz5lXFXrxM99LYlyQYYMDA110");
                eyes.setLogHandler(new StdoutLogHandler(true));
                eyes.setForceFullPageScreenshot(true);
                eyes.setStitchMode(StitchMode.CSS);
                eyes.setMatchLevel(MatchLevel.LAYOUT);          
                System.out.println("My Batch Id: " + batchId);
                BatchInfo batch = new BatchInfo("Applitools Demo");
                batch.setId(batchId);
                eyes.setBatch(batch);
                break;
            case "vg":
                break;
            default:
                throw new IllegalArgumentException("Eyes Config \"" + eyesConfig + "\" isn't supported.");
        }    
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
