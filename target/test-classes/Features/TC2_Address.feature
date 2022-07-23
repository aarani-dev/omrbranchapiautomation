
Feature: Address Module API automation
@Address
  Scenario Outline: verify add new address to the application through api
    Given User add headers and bearer authorization for accessing address endpoints
    When User add request body for Add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>"and"<address_type>"
    And User send "POST" request for add new address
    Then User verify the status code is 200
    And User verify the create address response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment        | state | city | country | zipcode | address       | address_type |
      | jeni       | s         | 7418438762 | 19/23 appartment |    35 | 3659 |     101 |  600089 | chennai nagar | home         |

  Scenario Outline: verify existing address is update to the application through api
    Given User add headers and bearer authorization for accessing address endpoints
    When User add request body for update existing address"<address_id>","<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>"and"<address_type>"
    And User send "PUT" request to update the existing address
    Then User verify the status code is 200
    And User verify the update address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment        | state | city | country | zipcode | address       | address_type |
      | jeni       | s         | 7418438762 | 19/23 appartment |    35 | 3659 |     101 |  600089 | chennai nagar | home         |

  Scenario: verify Delete address to the application through api
    Given User add headers and bearer authorization for accessing address endpoints
    When User send request body to delete address
    And User send "DELETE" request to delete the address
    Then User verify the status code is 200
    And User verify the delete address response message matches "Address deleted successfully"

  Scenario: verify Get address to the application through api
    Given User add headers and bearer authorization for accessing address endpoints
    When User send the "GET" request to get the existing address
    Then User verify the status code is 200
    And User verify the get address response message matches "OK"
