Feature: Online Process

@goToWebsite
Scenario: Website Test Scenario
	Given Go to "https://t-con.co.uk/"
	Then Wait "3000" ms
	Then Click random link