package it.pkg;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

public class GreetingResponse {
	private boolean successful;
	private String error;
	private String greeting;
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getServerInfo() {
		return RequestFactoryServlet.getThreadLocalServletContext().getServerInfo();
	}

	public String getUserAgent() {
		return RequestFactoryServlet.getThreadLocalRequest().getHeader("User-Agent");
	}
}
