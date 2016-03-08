package ${package};

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class ${module} implements EntryPoint {

	public void onModuleLoad() {
		${module}Component component = Dagger${module}Component.builder()
			.${module.substring(0,1).toLowerCase()}${module.substring(1)}Module(new ${module}Module())
			.build();

		SimplePanel mainContainer = new SimplePanel();
		MainActivityMapper mainMapper = component.mainActivityMapper();
		ActivityManager mainManager = new ActivityManager(mainMapper, component.eventBus());
		mainManager.setDisplay(mainContainer);

		RootPanel.get().add(mainContainer);

		component.placeHistoryHandler().handleCurrentHistory();
	}
}
