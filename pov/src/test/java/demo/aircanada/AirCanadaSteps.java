package pov.demo.aircanada;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

public class AirCanadaSteps {


    private AirCanadaPage aircanadaPage;

    public AirCanadaSteps() {
        this.aircanadaPage = new AirCanadaPage();
    }

    @Given("A user navigates to Air Canada Web Site")
    public void aUserNavigatesToHomePage() {
        this.aircanadaPage.goToHomePage(); 
    }
    @Then("^I Visually Validate \"([^\"]*)\"$")
    public void VisCheckHome(String tag) {
        this.aircanadaPage.eyesCheck(tag);
    }
    @When("I go to Book Travel Page")
    public void NavigateBookTravel() {
         this.aircanadaPage.goToBookTravelPage();
    }
    @Then("^I Check \"([^\"]*)\"$")
    public void VisTravel(String tag) {
         this.aircanadaPage.eyesCheck(tag);
    }
    @When("I go to Check In Information Page")
     public void NavigatePlanCheckIn() {
          this.aircanadaPage.goToPlanCheckInPage();
    }
    @Then("^I Review \"([^\"]*)\"$")
     public void VisCheckIn(String tag) {
          this.aircanadaPage.eyesCheck(tag);
    }
    @When("I go to Flight Information Page")
    public void NavigateFlightInfoPage() {
         this.aircanadaPage.goToFlyFlightInfoPage();
    }
    @Then("^I see \"([^\"]*)\"$")
    public void FlightInfo(String tag) {
         this.aircanadaPage.eyesCheck(tag);
    }
    @When("I go to Current Loyalty Program Overview Page")
    public void NavigateLoyaltyOverviewPage() {
         this.aircanadaPage.goToAltitudeOverviewPage();
    }
    @Then("^Validate \"([^\"]*)\"$")
    public void LoyaltyOverview(String tag) {
         this.aircanadaPage.eyesCheck(tag);
    }
}
