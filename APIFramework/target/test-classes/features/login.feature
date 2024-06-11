# new feature
# Tags: optional

Feature: Backend API Test

#  @Login
#  Scenario: zix postman connection
#    Given Connect to access token
##    When Make an API "POST" Request with "AccessToken"
#    When Make a api request


  @TransportruleALL_NO_POJO
    Scenario: zix postman connection
    Given Connect to access token
    When Make TransportruleAll api request

  @Login
  Scenario: zix postman connection
    Given Connect to access token
#    When Make an API "POST" Request with "AccessToken"
    Given transportRuleAllAPI payload
    When user calls "transportRuleAllAPI" with "POST" https request
#    Then the API call got success with status code 200
#    And "status" in response body is "OK"


#    Then Get the "id" from the "POST" https request
#    Then the API call got success with status code 200
#    And "status" in response body is "OK"


@LoginDisableallPOJO
  Scenario: Disable all Trasnport rule with pojo
    Given Connect to access token
    And transportRuleAllAPI payload
    When user calls "disableAllTransportRule" with "POST" https request
    And Stored id is available
#  Then the response should have "data.id"
 #data.id is because of the api body that i have thats why

 @LoginDisableallPOJO
 Scenario: Get the result of Disable all Transport rule with pojo
#   Given Connect to access token
#   Given Entering the get method
   Given Stored get id is available
#   Given "gettransportRuleAllAPI" request
   When user calls "disableAllGetTransportRule" with "GET" https request




