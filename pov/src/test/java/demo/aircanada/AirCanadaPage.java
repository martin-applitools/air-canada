package demo.aircanada;

import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.TestResultsSummary;
import demo.basepage.BasePage;
import com.applitools.eyes.selenium.fluent.Target;
import demo.TestResultsHandler.*;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AirCanadaPage extends BasePage{

    private static final String HOME_PAGE_URL = "https://www.aircanada.com/us/en/aco/home.html#/";
    

    AirCanadaPage() {
        PageFactory.initElements(driver, this);
    }

    void goToHomePage(String testName){
        eyes.open(driver, this.eyes.getAppName(), testName, viewport);
        driver.get(HOME_PAGE_URL);
        eyes.checkWindow();
        sleeptime();
        
    }
    void siteEditionSelection(String edition){
        driver.findElement(By.cssSelector("#enUSEdition > span:nth-child(2)")).click();
        sleeptime();
        eyes.checkWindow();
    }
    void selectOriginDestination(String origin, String destination) {
        driver.findElement(By.id("flightLocationListOrginId0Label")).click();
        sleeptime();
        driver.findElement(By.id("origin_R_0")).sendKeys(origin);
        sleeptime();
        driver.findElement(By.cssSelector(".active > span:nth-child(3) > span")).click();
        sleeptime();
        driver.findElement(By.id("destination_label_0")).click();
        sleeptime();
        driver.findElement(By.id("destination_R_0")).sendKeys(destination);
        sleeptime();
        driver.findElement(By.cssSelector("#flightLocationListDestinationId0_locationListItem_0 .airport-name")).click();
        eyes.checkWindow();
        sleeptime();
    }

    public void sleeptime() {
        try {
          // thread to sleep for 1000 milliseconds
          Thread.sleep(5000);
        } catch (Exception e) {
          System.out.println(e);
        }
        
      }

    void openEyes(String testName){ eyes.open(driver, this.eyes.getAppName(), testName, viewport ); }

    void eyesCheck(String testName, String tag){
        openEyes(testName);
        eyes.checkWindow(tag);
        eyes.closeAsync();
    }
    void eyesCheckFluentLayout(String testName) {
        openEyes(testName);
        eyes.check(testName, Target.window().layout());
        eyes.closeAsync();
    }
    void eyesCheckFluentLayoutRegion(String testName) {
        openEyes(testName);
        eyes.check(testName, Target.window().layout(By.cssSelector("#carousel-set-0")));
        eyes.closeAsync();
    }


    void eyesTestResults(){
        try {
            TestResultsSummary AllTestResults = this.runner.getAllTestResults(false);
            TestResultContainer[] results = AllTestResults.getAllResults();
            for(TestResultContainer result: results){
                TestResults test = result.getTestResults();

                if (test.getMismatches() > 0) {
                    System.out.println("My Mismatch URL: " + test.getUrl() );
                }

                assertEquals("Test Link: " + test.getUrl() + " has mismatches " + test.getName(), 0, test.getMismatches());
            }
            
        } finally {
            this.eyes.abortIfNotClosed();
        }
    } 

}
