package demo.infrastructure.driver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;


import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Setup {

    public static WebDriver driver;

    //public static VisualGridRunner runner = new VisualGridRunner(100);
    public static ClassicRunner runner = new ClassicRunner();
    public static Eyes eyes = new Eyes(runner);
    public static RectangleSize viewport = new RectangleSize(1200, 800);

    private static Long unixTime = System.currentTimeMillis();
    private static String batchId = Long.toString(unixTime);

    @Before
    public static void setWebDriver() throws Exception {
        String eyesConfig = System.getProperty("eyesConfig");
        if (eyesConfig == null) {
            eyesConfig = "local";
        }
        switch (eyesConfig) {
            case "local":
                Configuration sconf = new Configuration();
                sconf.setApiKey("INSERT APPLITOOLS API");
                //eyes.setLogHandler(new StdoutLogHandler(true));
                sconf.setParentBranchName("master");
                sconf.setForceFullPageScreenshot(true);
                sconf.setStitchMode(StitchMode.CSS);
                sconf.setMatchLevel(MatchLevel.LAYOUT);
                sconf.setBranchName(getCurrentGitBranch());
                sconf.setAppName("AirCanadaDemo");
                System.out.println("My Batch Id: " + batchId);
                BatchInfo batch = new BatchInfo("Applitools Demo");
                batch.setId(batchId);
                batch.setSequenceName(eyesConfig);
                sconf.setBatch(batch);
                eyes.setConfiguration(sconf);
                break;
            case "vg":
                Configuration sconfvg = new Configuration();
                sconfvg.setApiKey("INSERT APPLITOOLS API");
                sconfvg.setParentBranchName("master");
                sconfvg.setForceFullPageScreenshot(true);
                sconfvg.setStitchMode(StitchMode.CSS);
                sconfvg.setMatchLevel(MatchLevel.LAYOUT);
                sconfvg.setBranchName(getCurrentGitBranch());
                sconfvg.setAppName("AirCanadaDemoVG");
                //eyes.setLogHandler(new StdoutLogHandler(true));
                System.out.println("My Batch Id: " + batchId);
                BatchInfo batchvg = new BatchInfo("Applitools VG Demo");
                batchvg.setId(batchId);
                batchvg.setSequenceName(eyesConfig);
                sconfvg.setBatch(batchvg);
                for (BrowserType browserType : BrowserType.values()) {
                    if (browserType != BrowserType.IE_10) {
                        sconfvg.addBrowser(1366, 768, browserType);
                        sconfvg.addBrowser(1920, 1080, browserType);
                        sconfvg.addBrowser(1400, 900, browserType);
                    }
                }
                for (DeviceName deviceName : DeviceName.values()) {
                    sconfvg.addDeviceEmulation(deviceName, ScreenOrientation.LANDSCAPE);
                    sconfvg.addDeviceEmulation(deviceName, ScreenOrientation.PORTRAIT);
                }
                eyes.setConfiguration(sconfvg);

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
            case "chrome-headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless", "--ignore-certificate-errors");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }
    public static String getCurrentGitBranch() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec( "git rev-parse --abbrev-ref HEAD" );
        process.waitFor();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader( process.getInputStream() ) );

        return reader.readLine();
    }
}
