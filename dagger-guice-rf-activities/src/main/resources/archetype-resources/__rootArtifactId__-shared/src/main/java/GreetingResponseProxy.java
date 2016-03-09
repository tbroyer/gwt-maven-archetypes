package ${package};

import com.google.web.bindery.requestfactory.shared.ProxyForName;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyForName("${package}.GreetingResponse")
public interface GreetingResponseProxy extends ValueProxy {
	
	boolean isSuccessful();

	String getError();
	
	String getGreeting();

	String getServerInfo();

	String getUserAgent();
}