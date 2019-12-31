package demo.aircanada;

import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.TestResultsSummary;

import demo.basepage.BasePage;
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
    private static final String BOOK_TRAVEL_URL = "https://www.aircanada.com/us/en/aco/home/book/travels.html";
    private static final String PLAN_CHECK_IN_INFO_URL = "https://www.aircanada.com/us/en/aco/home/plan/check-in-information.html";
    private static final String FLY_FLIGHT_INFO_URL = "https://www.aircanada.com/us/en/aco/home/fly/flight-information.html";
    private static final String ALTITUDE_OVERVIEW_URL = "https://www.aircanada.com/us/en/aco/home/altitude/program-overview.html";

    AirCanadaPage() {
        PageFactory.initElements(driver, this);
    }

    void goToHomePage(){
        driver.get(HOME_PAGE_URL);
        wait.forLoading(5);
        driver.findElement(By.cssSelector("#enUSEdition > span:nth-child(2)")).click();
    }
    void goToBookTravelPage(){
        driver.get(BOOK_TRAVEL_URL);
        wait.forLoading(5);
    }
    void goToPlanCheckInPage(){
        driver.get(PLAN_CHECK_IN_INFO_URL);
        wait.forLoading(5);
    }
    void goToFlyFlightInfoPage(){
        driver.get(FLY_FLIGHT_INFO_URL);
        wait.forLoading(5);
    }
    void goToAltitudeOverviewPage(){
        driver.get(ALTITUDE_OVERVIEW_URL);
        wait.forLoading(5);
    }
    void openEyes(String testName){ eyes.open(driver, this.eyes.getAppName(), testName, viewport ); }

    void eyesCheck(String testName){
        openEyes(testName);
        eyes.checkWindow(testName);
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

                assertEquals(test.getName() + " has mismatches " + "Test Link: " + test.getUrl(), 0, test.getMismatches());
            }
            
        } finally {
            this.eyes.abortIfNotClosed();
        }
    } 

}
