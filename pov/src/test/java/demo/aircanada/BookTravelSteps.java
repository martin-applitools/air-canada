package demo.aircanada;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookTravelSteps {


    private BookTravelPage booktravelPage;

    public BookTravelSteps() {
        this.booktravelPage = new BookTravelPage();
    }

    @Given("A user navigates to Air Canada Web Site")
    public void aUserNavigatesToHomePage() {
        this.booktravelPage.goToHomePage();
    }
    @Then("^I Visually Validate \"([^\"]*)\"$")
    public void VisCheckHome(String tag) { this.booktravelPage.eyesCheckFluentLayoutRegion(tag);
    }
    @When("I go to Book Travel Page")
    public void NavigateBookTravel() {
         this.booktravelPage.goToBookTravelPage();
    }
    @Then("^I Check \"([^\"]*)\"$")
    public void VisTravel(String tag) {
         this.booktravelPage.eyesCheck(tag);
    }
    @When("I go to Check In Information Page")
     public void NavigatePlanCheckIn() {
          this.booktravelPage.goToPlanCheckInPage();
    }
    @Then("^I Review \"([^\"]*)\"$")
     public void VisCheckIn(String tag) {
          this.booktravelPage.eyesCheck(tag);
    }
    @When("I go to Flight Information Page")
    public void NavigateFlightInfoPage() {
         this.booktravelPage.goToFlyFlightInfoPage();
    }
    @Then("^I see \"([^\"]*)\"$")
    public void FlightInfo(String tag) {
         this.booktravelPage.eyesCheck(tag);
    }
    @When("I go to Current Loyalty Program Overview Page")
    public void NavigateLoyaltyOverviewPage() {
         this.booktravelPage.goToAltitudeOverviewPage();
    }
    @Then("^Validate \"([^\"]*)\"$")
    public void LoyaltyOverview(String tag) {
         this.booktravelPage.eyesCheck(tag);
    }
    @Then("Get Applitools Test Results")
    public void GetApplitoolsResults() { this.booktravelPage.eyesTestResults(); }
}
