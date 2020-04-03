@web


      Feature: Messenger page feature

        Background: I am on messenger page

      @messenger
          Scenario: Verify invalid login from messenger page
            When I enter halil@siliconelabs.com into username field on the messenger page
            And I enter test123! into password field on the messenger page
            And I click on login button on messenger page
            Then I verify invalid login message on sign in page

