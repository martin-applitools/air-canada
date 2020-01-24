package demo.infrastructure.driver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;

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

import java.io.*;
import java.util.Properties;

public class Setup {

    public static WebDriver driver;
    public static ClassicRunner runner = new ClassicRunner();
    public static Eyes eyes = new Eyes(runner);
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
        Configuration sconf = new Configuration();
        sconf.setApiKey(System.getProperty("applitools.api.key"));
        sconf.setViewportSize(viewport);
        eyes.setLogHandler(new StdoutLogHandler(true));
        sconf.setParentBranchName("master");
        //sconf.setEnvironmentName("Chrome");
        sconf.setForceFullPageScreenshot(true);
        sconf.setStitchMode(StitchMode.CSS);
        //sconf.setMatchLevel(MatchLevel.LAYOUT);
        sconf.setBranchName(getCurrentGitBranch());
        sconf.setAppName(System.getProperty("local.app.name"));
        System.out.println("My Batch Id: " + batchId);
        BatchInfo batch = new BatchInfo(System.getProperty("local.batch.name"));
        batch.setId(batchId);
        batch.setSequenceName(System.getProperty("local.sequence.name"));
        sconf.setBatch(batch);
        eyes.setConfiguration(sconf);

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
