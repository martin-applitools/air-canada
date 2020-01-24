package demo;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import java.io.*;
import org.junit.AfterClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/demo/BookTravel.feature"},
        strict = false, plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "json:target/cucumber_json_reports/aircanada.json"},
        glue = {"demo.infrastructure.driver",
                "demo.aircanada"})
public class BookTravelTest {
}
