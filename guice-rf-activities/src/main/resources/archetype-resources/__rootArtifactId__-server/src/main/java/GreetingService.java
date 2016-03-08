package ${package};

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * The server side implementation of the GreetingContext.
 */
public class GreetingService {

	private final Provider<ServletContext> ctx;
	private final Provider<HttpServletRequest> req;

	@Inject
	GreetingService(Provider<ServletContext> ctx, Provider<HttpServletRequest> req) {
		this.ctx = ctx;
		this.req = req;
	}

	public GreetingResponse greetServer(String input) {
		GreetingResponse response = new GreetingResponse();

		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, return an error back to the client.
			response.setSuccessful(false);
			response.setError("Name must be at least 4 characters long");
		} else {
			response.setSuccessful(true);
			response.setGreeting("Hello, " + input + "!");
			response.setServerInfo(ctx.get().getServerInfo());
			response.setUserAgent(req.get().getHeader("User-Agent"));
		}

		return response;
	}
}
