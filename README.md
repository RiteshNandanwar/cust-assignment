# cust-assignment

###Project Overview
This project provides a REST API for managing users, weather information, and weather history. It consists of three main controllers:

UserController: Handles user activation and deactivation.
WeatherController: Retrieves weather information based on zip code.
WeatherHistoryController: Retrieves weather history based on postal code or user.

# Endpoints

UserController:
	/api/users/{userId}/activate (PUT): Activates a user by ID.
	/api/users/{userId}/deactivate (PUT): Deactivates a user by ID.
	
WeatherController: 
	/api/weather/{zipCode} (GET): Retrieves weather information for a given zip code. Requires a valid userName as a query parameter.
	
WeatherHistoryController: 
	/app/history (GET): Retrieves weather history based on postalCode or user query parameters.

# Request Parameters:

userId: A string representing the user's ID.
zipCode: A long representing the zip code.
userName: A string representing the user's name.
postalCode: A string representing the postal code.
user: A string representing the user's name.

# Response Formats:

ResponseEntity<String>: For success messages from user activation/deactivation.
ResponseEntity<WeatherInfo>: For weather information.
ResponseEntity<List<Weather>>: For weather history.

# Usage
To use this API, you can send HTTP requests to the specified endpoints using tools like Postman, curl, or your preferred HTTP client. For example, to activate a user with ID MAX, you would send a PUT request to /api/users/MAX/activate.
