package demo.aircanada;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import io.cucumber.core.api.Scenario;;

public class AirCanadaSteps {

    public Scenario scenario;
    private AirCanadaPage aircanadaPage;
    private Scenario sce;

    public AirCanadaSteps() {
        this.aircanadaPage = new AirCanadaPage();
        
    }
    @Before
    public void beforeHook(Scenario scenario) {
        this.sce = scenario;
    }
//    @After
//    public void afterHook() {
//        this.aircanadaPage.eyesTestResults();
//    }
    @Given("^(A user navigates to Air Canada Web Site)$")
    public void aUserNavigatesToHomePage(String stepName) {
        this.aircanadaPage.goToHomePage(sce.getName(), stepName);
    }
    @When("^(A user launches the site in US Edition)$")
    public void a_user_launches_the_site_in(String stepName) {
        this.aircanadaPage.siteEditionSelection(stepName);
        //this.aircanadaPage.eyesCheck(sce.getName());
    }
    @When("User enters {string} and {string} in Home Page")
     public void user_enters_and_in_Home_Page(String origin, String destination) {
        this.aircanadaPage.selectOriginDestination(origin, destination);
    }


}
