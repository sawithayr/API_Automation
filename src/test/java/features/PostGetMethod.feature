Feature: Verify Rreqres HTTPS Methods using REST-assured

  @createNewUser
  Scenario Outline: POST - Create new user
    Given User sets the base API request "/api/users"
    When User create new data user with POST request "/api/users" and request body "<name>", "<job>"
    Then User validates the response status is 201
    And User validates id is not null
    And User validates the body response with name "<name>"
    And User validates the body response with job "<job>"

    Examples:
      | name          | job    |
      | Justin Bieber | Singer |

  @getDataUser
  Scenario: GET - Get data user details
    Given User gets new data user with GET request "/api/users" with id "/1"
    Then User validates the response status is 200
    And User validates data id is not null
    And User validates the body response with first name "George"
    And User validates the body response with last name "Bluth"







