package ${package};

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

@Singleton
public class GuiceRequestFactoryServlet extends RequestFactoryServlet {

	@Inject
	GuiceRequestFactoryServlet(ExceptionHandler exceptionHandler, GuiceServiceLayer guiceSL) {
		super(exceptionHandler, guiceSL);
	}
}

