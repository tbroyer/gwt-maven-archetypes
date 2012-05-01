package it.pkg;

/**
 * The server side implementation of the GreetingContext.
 */
public class GreetingService {

	public static GreetingResponse greetServer(String input) {
		GreetingResponse response = new GreetingResponse();

		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, return an error back to the client.
			response.setSuccessful(false);
			response.setError("Name must be at least 4 characters long");
		} else {
			response.setSuccessful(true);
			response.setGreeting("Hello, " + input + "!");
		}
		
		return response;
	}
}
