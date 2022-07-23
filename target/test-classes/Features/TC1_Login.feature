@Login
Feature: Login Module API automation

  Scenario: Get User Logtoken from login endpoint
    Given User add header
    And User add basic authentication for login
    When User send "POST" request for login endpoint
    Then User verify the status code is 200
    And User verify the login response body firstName present as "Jeni" and get the logtoken
