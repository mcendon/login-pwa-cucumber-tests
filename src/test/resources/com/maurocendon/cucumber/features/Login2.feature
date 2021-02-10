#Author: mau.cendon@gmail.com
#Feature: Login pwa application feature.
#Scenario: Business rule through list of steps with arguments.
@loginpwafeature
Feature: Login PWA
  Login PWA application feature file

  @successlogin @click
  Scenario Outline: Success login
    Given User is in the login form
    When User complete the form with valid <username> and <password> with <displayName>
    When User click the submit button
    Then User should see the welcome page
    And User display name should be in the page title

    Examples: 
      |username|password|displayName|
      |"user1@test.com"|"T3stuser1$"|"Mauro"|
      |"user2@test.com"|"T3stuser2$"|"Cristina"|
      