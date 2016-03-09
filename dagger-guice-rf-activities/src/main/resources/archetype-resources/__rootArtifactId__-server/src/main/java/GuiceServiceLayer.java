package ${package};

import static java.util.Objects.requireNonNull;

import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

import javax.inject.Inject;

public class GuiceServiceLayer extends ServiceLayerDecorator {

	private final Injector injector;

	@Inject
	GuiceServiceLayer(Injector injector) {
		this.injector = requireNonNull(injector);
	}

	@Override
	public <T extends Locator<?,?>> T createLocator(java.lang.Class<T> clazz) {
		return injector.getInstance(clazz);
	}

	@Override
	public <T extends ServiceLocator> T createServiceLocator(java.lang.Class<T> clazz) {
		return injector.getInstance(clazz);
	}
}

