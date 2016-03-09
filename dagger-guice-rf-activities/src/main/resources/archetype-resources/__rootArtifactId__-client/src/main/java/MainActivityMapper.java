package ${package};

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import javax.inject.Inject;
import javax.inject.Provider;

public class MainActivityMapper implements ActivityMapper {

	private static final Logger logger = Logger.getLogger(MainActivityMapper.class.getName());

	@Inject Provider<HomeActivity> homeActivityProvider;
	@Inject GreetingActivityFactory greetingActivityFactory;

	@Inject MainActivityMapper() {
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return homeActivityProvider.get();
		}
		if (place instanceof GreetingPlace) {
			GreetingPlace greetingPlace = (GreetingPlace) place;
			return greetingActivityFactory.create(greetingPlace.getUser());
		}
		logger.severe("Unhandled place type: " + place.getClass().getName());
		return null;
	}
}
