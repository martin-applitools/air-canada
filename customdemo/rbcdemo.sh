#!/usr/bin/env bash

setup() {
  export APPLITOOLS_API_KEY=zu4BybAMbOeRPtYF2mWHTAjNJOiuRPcD5JOUWcBP8lU110
  echo "Clearing previous Branch Name"
  export BRANCH_NAME=
  export BRANCH_NAME=$(git branch | grep '^*' | sed 's/* //' )
  #Set Applitools Environment Variables, Disable BATCH_NAME and BATCH_ID when using with Jenkins
  echo "Clearing previous batch id"
  export APPLITOOLS_BATCH_ID=
  echo "Setting new batch id"
  export APPLITOOLS_BATCH_ID=$(cat /dev/urandom | env LC_CTYPE=C tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
  echo $APPLITOOLS_BATCH_ID

}
setupvg() {
  export APPLITOOLS_API_KEY=zu4BybAMbOeRPtYF2mWHTAjNJOiuRPcD5JOUWcBP8lU110
  echo "Clearing previous Branch Name"
  export BRANCH_NAME=
  export BRANCH_NAME=$(git branch | grep '^*' | sed 's/* //' )
  #Set Applitools Environment Variables, Disable BATCH_NAME and BATCH_ID when using with Jenkins
  echo "Clearing previous batch id"
  export APPLITOOLS_BATCH_ID=
  echo "Setting new batch id"
  export APPLITOOLS_BATCH_ID=$(cat /dev/urandom | env LC_CTYPE=C tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
  echo $APPLITOOLS_BATCH_ID
}

case "$1" in
  "-appium")
    setup
    cd mobile/
    echo "Starting Appium Java Tests"
    mvn clean -Dtest=AppiumJava test
    echo "Completed Appium Java Tests - Review Visual Differences in Applitools"
    mv *.log logs/
    ;;
  "-webdriver")
    setup
    cd webdriver/
    echo "Starting WebDriver Tests"
    export APPLITOOLS_BATCH_NAME=RBC-WebDriver
    echo "Setting Batch Statistics Sequence Name"
    export APPLITOOLS_BATCH_SEQUENCE=RBC-WebDriver
    echo $APPLITOOLS_BATCH_SEQUENCE
    mvn clean -Dtest=RBCWebDriver test
    sleep 30

    echo "Completed WebDriver Localization Tests - Review Visual Differences in Applitools"
    mv *.log logs/
    ;;
  "-vg")
    setupvg
    cd webdriver/
    echo "Starting Visual Grid Browser Only"
    export APPLITOOLS_BATCH_NAME=TXM-V
    echo "Setting Batch Statistics Sequence Name"
    export APPLITOOLS_BATCH_SEQUENCE=TXM-VisualGrid
    echo $APPLITOOLS_BATCH_SEQUENCE
    mvn clean -Dtest=TXMVisualGrid test
    echo "Completed Visual Grid Tests - Review Visual Differences in Applitools"
    ;;
  "-jenkins")
    export APPLITOOLS_API_KEY=zu4BybAMbOeRPtYF2mWHTAjNJOiuRPcD5JOUWcBP8lU110
    export APPLITOOLS_BATCH_ID=`echo ${GIT_COMMIT}`
    echo "Starting Local WebDriver Tests"
    /usr/local/bin/mvn clean -Dtest=TXMJenkins test
    echo "Completed Local WebDriver Tests - Review Visual Differences in Applitools"
    ;;
  "-pdf")
    echo "Starting E2E PDF Test"
    cd pdfdemo
    echo "Creating Baseline"
    cp Baseline/TXMSample.pdf QuotePDF/
    java -jar ImageTester.jar -k $APPLITOOLS_API_KEY -a TXMQuoteApp -f QuotePDF
    sleep 10
    echo "Cleaning up Baseline file"
    rm QuotePDF/*.*
    echo "Executing Current Build"
    cp Current/TXMSample.pdf QuotePDF/
    java -jar ImageTester.jar -k $APPLITOOLS_API_KEY -a TXMQuoteApp -f QuotePDF
    echo "Cleaning up Current file"
    rm QuotePDF/*.*
    echo "E2E PDF Test Complete - Review Visual Differences in Applitools"
    ;;
  "-pdfoptions")
    echo "Starting PDF Options Test"
    cd pdfdemo
    echo "Creating Baseline"
    cp Baseline/TXMSample.pdf OptionsPDF/
    java -jar ImageTester.jar -k $APPLITOOLS_API_KEY -a TXMQuoteAppOptions -br $BRANCH_NAME -sp "2,4,7" -f OptionsPDF
    echo "Cleaning up Baseline file"
    rm OptionsPDF/*.*
    echo "Executing Current Build"
    cp Current/TXMSample.pdf OptionsPDF/
    java -jar ImageTester.jar -k $APPLITOOLS_API_KEY -a TXMQuoteAppOptions -br $BRANCH_NAME -sp "2,4,7" -f OptionsPDF
    echo "Cleaning up Current file"
    rm OptionsPDF/*.*
    echo "Options PDF Test Complete - Review Visual Differences in Applitools"
    ;;
  "-h")
    echo $"Usage: $0 {-appium|-vg|-jenkins|-pdf|-pdfoptions}"""
    echo "	-appium : Run Appium Java Mobile Use Case."
    echo "	-vg : Run Visual Grid Test Suite Only."
    echo "	-jenkins : Run End to End Scenario in Jenkins"
    echo "	-pdf : Run PDF End to End Scenario with Applitools"
    echo "	-pdfoptions : Run ImageTester Options End to End Scenario"
    ;;
  *)
    echo "You have failed to specify any options, please specify -h for usage."
    exit 1
    ;;
esac