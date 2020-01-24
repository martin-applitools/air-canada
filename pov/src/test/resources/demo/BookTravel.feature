Feature: Book Travel
  Visually Validate Book Travel Business Flow using local Selenium and Ultrafast Grid

    Scenario: Book Travel Local Selenium WebDriver
      Given A user navigates to Air Canada Web Site
      When I go to Book Travel Page
      When I go to Check In Information Page
      When I go to Flight Information Page
      When I go to Current Loyalty Program Overview Page
      Then Get Applitools Test Results

    Scenario: Book Travel Ultrafast Grid
      Given