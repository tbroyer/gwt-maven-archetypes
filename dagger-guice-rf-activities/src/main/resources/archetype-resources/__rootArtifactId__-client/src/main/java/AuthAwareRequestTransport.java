package ${package};

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Extends {@link DefaultRequestTransport} to handle unauthenticated requests (HTTP status 401 (Unauthorized)).
 *
 * <b>Implementation note</b>: largely inspired by <a href=
 * "http://code.google.com/p/google-web-toolkit/source/browse/tags/2.4.0/samples/expenses/src/main/java/com/google/gwt/sample/gaerequest/client/GaeAuthRequestTransport.java"
 * ><code>GaeAuthRequestTransport</code></a>.
 */
public class AuthAwareRequestTransport extends DefaultRequestTransport {

	private boolean lastResponseWasUnauthorized = false;

	@Override
	protected RequestCallback createRequestCallback(final TransportReceiver receiver) {
		final RequestCallback superCallback = super.createRequestCallback(receiver);
		return new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (Response.SC_UNAUTHORIZED == response.getStatusCode()) {
					/*
					 * Hand the receiver a non-fatal callback, so that
					 * com.google.gwt.requestfactory.shared.Receiver will not post a
					 * runtime exception.
					 */
					receiver.onTransportFailure(
						new ServerFailure("Unauthenticated user", null, null, false /* non-fatal */));
					boolean responseWasUnauthorized = AuthAwareRequestTransport.this.lastResponseWasUnauthorized;
					AuthAwareRequestTransport.this.lastResponseWasUnauthorized = true;
					if (!responseWasUnauthorized) {
						// TODO: Maybe fire an event on the EventBus instead...
						Window.alert("You've been disconnected. Reload the page to authenticate back.");
					}
					return;
				}
				AuthAwareRequestTransport.this.lastResponseWasUnauthorized = false;
				superCallback.onResponseReceived(request, response);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				superCallback.onError(request, exception);
			}
		};
	}
}

