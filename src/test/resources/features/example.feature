Feature: Online Process 

@goToWebsite
Scenario: Website Test Scenario
	Given Go to "http://www.google.com"
	Then Wait "3000" ms
	Then Click random link
