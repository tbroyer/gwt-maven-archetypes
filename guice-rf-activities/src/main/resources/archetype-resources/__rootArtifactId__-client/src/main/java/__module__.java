package ${package};

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class ${module} implements EntryPoint {

	public void onModuleLoad() {
		${module}Ginjector injector = GWT.create(${module}Ginjector.class);

		SimplePanel mainContainer = new SimplePanel();
		MainActivityMapper mainMapper = injector.mainActivityMapper();
		ActivityManager mainManager = new ActivityManager(mainMapper, injector.eventBus());
		mainManager.setDisplay(mainContainer);

		RootPanel.get().add(mainContainer);

		injector.placeHistoryHandler().handleCurrentHistory();
	}
}
