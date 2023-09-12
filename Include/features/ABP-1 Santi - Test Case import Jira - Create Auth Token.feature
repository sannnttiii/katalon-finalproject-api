@AuthToken

Feature: Authentication Feature

As a user, I want to create token authentication.

 

@Valid

Scenario Outline: API Create Token Authentication

When I set username <username> and password <password>

And I click Run Verify to check token has been created

Examples:

| username | password |

| admin | password123 |