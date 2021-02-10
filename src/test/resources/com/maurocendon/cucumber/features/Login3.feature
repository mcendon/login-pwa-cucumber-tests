#Author: mau.cendon@gmail.com
#Feature: Login pwa application feature.
#Scenario: Business rule through list of steps with arguments.
@loginpwafeature
Feature: Login PWA
  Login PWA application feature file

  @successlogin @userdisabled
  Scenario: Disabled user
    Given User is in the login form
    When User complete the form with valid credentials
    	|user3@test.com|T3stuser3$||
    When User submit the form
    Then User should see the user disabled message
