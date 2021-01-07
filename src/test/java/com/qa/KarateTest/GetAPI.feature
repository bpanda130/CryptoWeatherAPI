Feature: This Feature file contains validation Test for Weather Get API.

	Background:
		* url 'https://data.weather.gov.hk/weatherAPI/opendata/weather.php'
		* header Accept = 'application/json'
		* configure ssl = true
		
	@Weather_RHRREAD
	Scenario Outline: Verifying the Weather Get API with dataType as <dataType> and Language as <lang> 
		Given params { dataType: <dataType>, lang: <lang>}
		When method get
		#And print response
		Then status 200
		And match responseType == 'json'
		And match response.humidity.data[0].place == <Country>
		Then match response.temperature.data[0].unit == '#string'
		Then match response.temperature.data[0].value == '#number'
		Then match response.temperature.data[0].place == '#string'
		Examples:
		|dataType|lang|Country|
		|'rhrread'|'en'|'Hong Kong Observatory'|
