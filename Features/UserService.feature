@UserService
Feature: As a user I want to use User Service to Create, Update, Get, and Delete the user details

  @CreateUser
  Scenario: Create User Details
    Given the user a UserService endpoint
    When the user call "POST" endpoint with payload "NewUser.json"
    Then new user details must be created

  @GetAllUsers
  Scenario: Get All User Details
    Given the user a UserService endpoint
    When the user call "GET" endpoint with queryParam "page" and value "2"
    Then all user details must be fetched from page number "2"