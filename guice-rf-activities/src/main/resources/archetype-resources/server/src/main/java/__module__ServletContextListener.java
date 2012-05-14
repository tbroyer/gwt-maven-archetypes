package ${package};

import javax.servlet.http.HttpServletRequest;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.RequestScoped;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;

public class ${module}ServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/gwtRequest").with(GuiceRequestFactoryServlet.class);

				bind(ExceptionHandler.class).to(DefaultExceptionHandler.class);
			}

			@Provides @CurrentUser @RequestScoped
			String provideCurrentUser(HttpServletRequest request) {
				return request.getRemoteUser();
			}

			@Provides @IsAdmin @RequestScoped
			boolean provideIsAdmin(HttpServletRequest request) {
				return request.isUserInRole("admin");
			}
		});
	}
}

