Feature: AirCanada
  Visually Validate Air Canada Web Pages

  Scenario Outline: Run Web Application Test <ID>
    Given A user navigates to Air Canada Web Site 
    When A user launches the site in US Edition
    And User enters "<Origin>" and "<Destination>" in Home Page

  Examples:
  | ID | Origin | Destination |
  | 1 | AUS  | YYZ  |
  | 2 | AUS  | YEG  |
