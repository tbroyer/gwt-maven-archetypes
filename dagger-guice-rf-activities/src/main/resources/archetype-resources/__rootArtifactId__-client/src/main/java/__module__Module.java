package ${package};

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.autobean.gwt.client.impl.JsoSplittable;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.RequestTransport;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ${module}Module {

	private Place getDefaultPlace() {
		return new HomePlace();
	}

	@Provides @Singleton
	SimpleEventBus provideSimpleEventBus() {
		return new SimpleEventBus();
	}

	@Provides
	EventBus provideEventBus(SimpleEventBus bus) {
		return bus;
	}

	@Provides
	PlaceHistoryMapper providePlaceHistoryMapper() {
		return GWT.create(${module}PlaceHistoryMapper.class);
	}

	@Provides
	Historian provideHistorian() {
		// For best UX at login time (preserving place), use an Html5History: https://gist.github.com/1883821
		return new DefaultHistorian();
	}

	@Provides
	RequestTransport provideRequestTransport() {
		return new AuthAwareRequestTransport();
	}

	@Provides @Singleton
	PlaceController providePlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Provides @Singleton
	PlaceHistoryHandler providePlaceHistoryHandler(PlaceHistoryMapper mapper, Historian historian,
			PlaceController controller, EventBus eventBus) {
		PlaceHistoryHandler handler = new PlaceHistoryHandler(mapper, historian);
		handler.register(controller, eventBus, getDefaultPlace());
		return handler;
	}

	@Provides @Singleton
	${module}Factory provide${module}Factory(EventBus eventBus, RequestTransport transport) {
		${module}Factory factory = GWT.create(${module}Factory.class);
		factory.initialize(eventBus, transport);
		return factory;
	}

	@Provides
	Scheduler provideScheduler() {
		return Scheduler.get();
	}

	@Provides @LogoutUrl
	native String provideLogoutUrl() /*-{
		return $wnd.logoutUrl != null ? String($wnd.logoutUrl) : null;
	}-*/;

	@Provides @CurrentUser @Singleton
	User provideCurrentUser() {
		final User.Factory factory = GWT.create(User.Factory.class);
		final JavaScriptObject nativeUser = getNativeUser();
		final AutoBean<User> user;
		if (GWT.isScript()) {
			user = AutoBeanCodex.decode(factory, User.class, (JsoSplittable) nativeUser);
		} else {
			// JsoSplittable is a @GwtScriptOnly, so we need this hack in devMode
			final String payload = new JSONObject(nativeUser).toString();
			user = AutoBeanCodex.decode(factory, User.class, payload);
		}
		return user.as();
	}

	@Provides @CurrentUser
	String provideUserName(@CurrentUser User user) {
		return user.getUserName();
	}

	@Provides @IsAdmin
	boolean provideIsAdmin(@CurrentUser User user) {
		return user.isAdmin();
	}

	private native JavaScriptObject getNativeUser() /*-{
		return $wnd.user;
	}-*/;
}
