@ChangeProfile
Feature: ChangeProfilePic Module API automation

  Scenario: verify changeprofilepic to the application through api
    Given User add headers and bearer authorization and multipart/form-data for accessing changeprofilepic endpoints
    And User send "POST" request for changeprofilepic
    Then User verify the status code is 200
    And User verify the changeprofilepic response message matches "Profile updated Successfully"
