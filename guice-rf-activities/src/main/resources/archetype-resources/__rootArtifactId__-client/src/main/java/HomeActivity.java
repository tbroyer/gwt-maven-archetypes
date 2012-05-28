package ${package};

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements HomeView.Presenter {

	private final HomeView view;
	private final PlaceController placeController;

	@Inject
	HomeActivity(HomeView view, PlaceController placeController) {
		this.view = view;
		this.placeController = placeController;
	}

	@Override
	public void start(AcceptsOneWidget panel,
			@SuppressWarnings("deprecation") com.google.gwt.event.shared.EventBus eventBus) {
		view.setPresenter(this);
		view.setEnabled(true);
		panel.setWidget(view);
	}

	@Override
	public void sendNameToServer(String name) {
		// First, we validate the input.
		view.setError("");
		if (!FieldVerifier.isValidName(name)) {
			view.setError("Please enter at least four characters");
			return;
		}

		// Then, we send the input to the server.
		view.setEnabled(false);

		placeController.goTo(new GreetingPlace(name));
	}
}

