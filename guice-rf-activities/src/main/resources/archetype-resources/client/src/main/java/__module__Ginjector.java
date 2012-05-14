package ${package};

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(${module}GinModule.class)
public interface ${module}Ginjector extends Ginjector {

	EventBus eventBus();

	PlaceHistoryHandler placeHistoryHandler();

	MainActivityMapper mainActivityMapper();
}
