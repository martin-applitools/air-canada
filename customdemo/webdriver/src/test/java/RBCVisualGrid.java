import com.applitools.eyes.TestResults;
import com.applitools.eyes.fluent.Target;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.Region;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
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
import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.Map.Entry;


public class TXMVisualGrid {
  private VisualGridRunner runner = new VisualGridRunner(100);
  private Eyes eyes = new Eyes(runner);
  private WebDriver driver;
  private Map<String, Object> vars;
  private static BatchInfo batchname;
  JavascriptExecutor js;

  @BeforeClass
  public static void batchInitialization(){
    BatchInfo batchname = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
    batchname.setSequenceName(System.getenv("APPLITOOLS_BATCH_SEQUENCE"));
    batchname.setId(System.getenv("APPLITOOLS_BATCH_ID"));

}
  @Before
  public void setUp() throws Exception {
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    eyes.setLogHandler(new FileLogger(this.getClass().getSimpleName().toString() + ".log", true, true));
    Configuration sconf = new Configuration();
    sconf.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
    sconf.setBatch(batchname);
    sconf.setForceFullPageScreenshot(true);
    sconf.setStitchMode(StitchMode.CSS);
    sconf.setBranchName(System.getenv("BRANCH_NAME"));
    for (BrowserType browserType : BrowserType.values()) {
      sconf.addBrowser(1366, 768, browserType);
      sconf.addBrowser(1920, 1080, browserType);
      sconf.addBrowser(1440, 900, browserType);
      sconf.addBrowser(1280, 800, browserType);
    }
    for (DeviceName deviceName : DeviceName.values()) {
      sconf.addDeviceEmulation(deviceName, ScreenOrientation.LANDSCAPE);
      sconf.addDeviceEmulation(deviceName, ScreenOrientation.PORTRAIT);
    }
    eyes.setConfiguration(sconf); 
 
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    eyes.abort();
  }

  @Test
  public void TXMPortalVG() throws Exception {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
    driver = new ChromeDriver(chromeOptions);
    eyes.open(driver, "TXMPortal-VG", this.getClass().getSimpleName().toString(), new RectangleSize(1200, 800));
    // 1 | open | / |  |
    driver.get("https://www.texasmutual.com/");
    eyes.check("Home Page", Target.window().fully());
    // 2 | click | linkText=Workers' Comp Basics |  |
    driver.findElement(By.linkText("Workers\' Comp Basics")).click();
    eyes.check("Workers' Comp Basics", Target.window().fully());
    // 3 | click | linkText=Just for Employers |  |
    driver.findElement(By.linkText("Just for Employers")).click();
    eyes.check("Just for Employers", Target.window().fully());
    // 4 | click | linkText=About Texas Mutual |  |
    driver.findElement(By.linkText("About Texas Mutual")).click();
    eyes.check("About Texas Mutual", Target.window().fully());
    // 5 | click | linkText=Agents |  |
    driver.findElement(By.linkText("Agents")).click();
    eyes.check("Agents", Target.window().fully());
    // 6 | click | linkText=Health Care Providers |  |
    driver.findElement(By.linkText("Health Care Providers")).click();
    eyes.check("Health Care Providers", Target.window().fully());
    // 7 | click | linkText=Injured Workers |  |
    driver.findElement(By.linkText("Injured Workers")).click();
    eyes.check("Injured Workers", Target.window().fully());
    // 8 | click | linkText=Login |  |
    driver.findElement(By.linkText("Login")).click();
    eyes.check("Login", Target.window().fully());


    System.out.println(
        "Please wait... we are now: \n1. Uploading resources, \n2. Rendering in Visual Grid, and \n3. Using Applitools A.I. to validate the checkpoints....");
    TestResultsSummary allTestResults = runner.getAllTestResults(false);
    System.out.println(allTestResults);
   
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
