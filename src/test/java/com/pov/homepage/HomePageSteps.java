package com.pov.homepage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {


    private HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }

    @Given("^A user navigates to HomePage \"([^\"]*)\"$")
    public void aUserNavigatesToHomePage(String country) {
        this.homePage.goToHomePage(country);
        //this.homePage.eyesCheck(country);
    }

    @Then("^I open my eyes for test \"([^\"]*)\"$")
    public void openEyes(String test) {
        this.homePage.openEyes(test);
    }

    @Then("^I visually check page \"([^\"]*)\"$")
    public void openCheck(String tag) {
        this.homePage.eyesCheck(tag);
    }

    @Then("^I click link \"([^\"]*)\"$")
    public void click(String selector) {
        this.homePage.clickLink(selector);
    }

    @Then("^I close my eyes$")
    public void click() {
        this.homePage.eyesClose();
    }

    @Then("^Google logo is displayed$")
    public void googleLogoIsDisplayed() {
        this.homePage.checkLogoDisplay();
    }

    @And("^search bar is displayed$")
    public void searchBarIsDisplayed() {
        this.homePage.checkSearchBarDisplay();
    }

    @Then("^page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        this.homePage.checkTitle(title);
    }

    @When("^a user searches for \"([^\"]*)\"$")
    public void aUserSearchesFor(String searchValue) {
        this.homePage.searchFor(searchValue);
    }
}
