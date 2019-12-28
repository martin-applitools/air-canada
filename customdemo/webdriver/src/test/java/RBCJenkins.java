import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.Region;
import com.applitools.eyes.TestResultsSummary;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.Map.Entry;


public class TXMJenkins {
  private Eyes eyes = new Eyes();
  private WebDriver driver;
  private Map<String, Object> vars;
  private static BatchInfo batchname;
  JavascriptExecutor js;

  @BeforeClass
  public static void batchInitialization(){
    //BatchInfo batchname = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
    //batchname.setSequenceName(System.getenv("APPLITOOLS_BATCH_SEQUENCE"));
    //batchname.setId(System.getenv("APPLITOOLS_BATCH_ID"));

}
  @Before
  public void setUp() throws Exception {
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    eyes.setLogHandler(new FileLogger(this.getClass().getSimpleName().toString() + ".log", true, true));
    
    Configuration sconf = new Configuration();
    sconf.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
    //sconf.setBatch(batchname);
    sconf.setForceFullPageScreenshot(true);
    sconf.setStitchMode(StitchMode.CSS);
    sconf.setMatchLevel(MatchLevel.LAYOUT);
    eyes.setConfiguration(sconf);
 
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    eyes.abort();   
  }

  @Test
  public void TXMPortalJenkins() throws Exception {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
    driver = new ChromeDriver();
    eyes.open(driver, "JenkinsJavaSDK", this.getClass().getSimpleName().toString(), new RectangleSize(1200, 800));

    eyes.close(false);
    //System.out.println(results);
    //assertEquals(true, results.isPassed());
  }
  
  public static void sleeptime() {
    try {
      // thread to sleep for 1000 milliseconds
      Thread.sleep(3000);
    } catch (Exception e) {
      System.out.println(e);
    }
    
  }
}
