package ${package};

import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ${module}Module.class)
public interface ${module}Component {

	EventBus eventBus();

	PlaceHistoryHandler placeHistoryHandler();

	MainActivityMapper mainActivityMapper();
}
