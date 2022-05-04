Feature: Shopping

  Scenario: I want to buy 1 p1
    Given a shopping with 10 p1 products that costs 2
    When buy 1 item of p1
    Then I have to pay 2