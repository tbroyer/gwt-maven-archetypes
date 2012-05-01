package it.pkg;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

/**
 * The client side stub for the RequestFactory service.
 */
@ServiceName("it.pkg.GreetingService")
public interface GreetingContext extends RequestContext {
	Request<GreetingResponseProxy> greetServer(String name);
}
