package pov.demo.aircanada;

import com.applitools.eyes.TestResults;

import pov.demo.basepage.BasePage;
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
    void openEyes(String testName){
        eyes.open(driver, "AirCanadaDemo", testName );
    }

    void eyesCheck(String testName){
        openEyes(testName);
        eyes.checkWindow(testName);
        eyesClose();
    }

    void eyesClose(){
        try {
            TestResults results = this.eyes.close(false);
            assertTrue("Batch Name:" + results.getBatchName() + "Matches:" + results.getMatches() + "MisMatches" + results.getMismatches()
            + " Test Link:" + results.getUrl(), results.isPassed());
            
        } finally {
            this.eyes.abortIfNotClosed();
        }
    } 

}
