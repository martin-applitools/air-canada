package com.pov.infrastructure.driver;

import com.applitools.eyes.selenium.Eyes;
import io.cucumber.core.api.Scenario; 
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TearDown {

    private WebDriver driver;
    private Eyes eyes;

    public TearDown() {
        this.driver = Setup.driver;
        this.eyes = Setup.eyes;
    }

    @After
    public void quitDriver(Scenario scenario){
        this.driver.quit();
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
