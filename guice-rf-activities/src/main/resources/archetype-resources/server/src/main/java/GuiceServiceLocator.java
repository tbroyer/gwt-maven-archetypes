package ${package};

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class GuiceServiceLocator implements ServiceLocator {

	private final Injector injector;

	@Inject
	GuiceServiceLocator(Injector injector) {
		this.injector = injector;
	}

	@Override
	public Object getInstance(Class<?> clazz) {
		return injector.getInstance(clazz);
	}
}
