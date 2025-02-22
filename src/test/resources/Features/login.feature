Feature: Automation Login Feature
  As a user, I want to be able to login into a website
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid credentials
    Then I should be logged in successfully
