package ${package};

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GuiceRequestFactoryServlet extends RequestFactoryServlet {

	@Inject
	GuiceRequestFactoryServlet(ExceptionHandler exceptionHandler, GuiceServiceLayer guiceSL) {
		super(exceptionHandler, guiceSL);
	}
}

