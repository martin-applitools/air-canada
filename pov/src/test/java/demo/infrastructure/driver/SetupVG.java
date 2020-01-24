package demo.infrastructure.driver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class SetupVG {

    public static WebDriver driver;

    public static VisualGridRunner runnerVG = new VisualGridRunner(100);
    public static Eyes eyesVG = new Eyes(runnerVG);
    public static RectangleSize viewport = new RectangleSize(1200, 800);

    private static Long unixTime = System.currentTimeMillis();
    private static String batchId = Long.toString(unixTime);

    @Before
    public static void setWebDriver() throws Exception {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("src/test/resources/test.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Configuration sconfvg = new Configuration();
        sconfvg.setApiKey(System.getProperty("applitools.api.key"));
        sconfvg.setViewportSize(viewport);
        sconfvg.setParentBranchName("master");
        sconfvg.setForceFullPageScreenshot(true);
        sconfvg.setStitchMode(StitchMode.CSS);
        sconfvg.setMatchLevel(MatchLevel.LAYOUT);
        sconfvg.setBranchName(getCurrentGitBranch());
        sconfvg.setAppName(System.getProperty("vg.app.name"));
        //eyes.setLogHandler(new StdoutLogHandler(true));
        System.out.println("My Batch Id: " + batchId);
        BatchInfo batchvg = new BatchInfo(System.getProperty("vg.batch.name"));
        batchvg.setId(batchId);
        batchvg.setSequenceName(System.getProperty("vg.sequence.name"));
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
        eyesVG.setConfiguration(sconfvg);

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
