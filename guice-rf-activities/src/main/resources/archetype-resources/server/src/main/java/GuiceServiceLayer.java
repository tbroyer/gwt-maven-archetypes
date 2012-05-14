package ${package};

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

public class GuiceServiceLayer extends ServiceLayerDecorator {

	private final Injector injector;

	@Inject
	GuiceServiceLayer(Injector injector) {
		this.injector = checkNotNull(injector);
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

