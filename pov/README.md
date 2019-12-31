# Air Canada test example in Java with Cucumber, Selenium WebDriver, and Extent Reporting Framework (HTML Output) #

This project is an example of integrating Applitools with a UI functional test for the Air Canada Web Site using Selenium Webdriver, Cucumber, and the Extent Reporting Framework.

Test scenarios are described in feature files located here ./src/test/resources/demo.  Extent Reporting configuration is located in extent.properties and html-config.xml located here ./src/test/resources.



## Installation ##

You need to have [Java 8 JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) installed along with [maven](https://maven.apache.org/).

To run the tests locally with Chrome, install ChromeDriver from [here](http://chromedriver.chromium.org) and add its location to your system PATH.

To run the tests locally with Firefox, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases) and add its location to your system PATH.

Insert your Applitools API Key into src/test/java/demo/infrastructure/driver/Setup.java line 46 and line 61. If you need assistance with how to get your Applitools API Key please go [here](https://applitools.com/docs/topics/overview/obtain-api-key.html)  


To install all dependencies, run 

```console
$ mvn clean install
```

## Running tests ##

```console
$ mvn test
```

By default, tests will run locally on Chrome. To change that, specify `-Dbrowser={browser}` where `{browser}` is either `chrome` , `chrome-headless`, or `firefox`

To run the tests utilizing Applitools Ultrafast Visual Grid to render across Desktop Browsers and Mobile Web configurations, please make the following changes:

    Modify src/test/java/demo/infrastructure/driver/Setup.java: Enable Line 29, Disable Line 30 
    Modify src/test/java/demo/BasePage.java: Enable Line 21, Disable Line 22
    When running the test add the optional parameter -DeyesConfig=vg: Example mvn test -DeyesConfig=vg

