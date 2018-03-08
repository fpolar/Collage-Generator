Feature: Light Gray Background
As a developer
I want out users to see a light gray background on the site
So that the stakeholders are happy

Background:
	Given I am on the welcome page

Scenario: User Visits the Welcome Page
	Then the background should be light-gray

Scenario: User Visits the welcome page
	Then the input box should have a dark-gray outline

Scenario: User Visits the welcome page
	Then the input box is centered in the middle of the screen

Scenario: User Visits the Welcome Page
	Then the input box has prompt text "Enter Topic"
	And the prompt text is in a light gray color
