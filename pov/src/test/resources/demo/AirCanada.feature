Feature: AirCanada
  Visually Validate Air Canada Web Pages

  Scenario: Check Air Canada Web Site Display
    Given A user navigates to Air Canada Web Site
    Then I Visually Validate "Home Page"
    When I go to Book Travel Page
    Then I Check "Book Travel Page"
    When I go to Check In Information Page
    Then I Review "Check In Information Page"
    When I go to Flight Information Page
    Then I see "Flight Information Page"
    When I go to Current Loyalty Program Overview Page
    Then Validate "Loyalty Program Overview Page"
    