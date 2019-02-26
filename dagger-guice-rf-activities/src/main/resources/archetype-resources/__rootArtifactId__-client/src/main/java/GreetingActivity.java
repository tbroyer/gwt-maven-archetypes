package ${package};

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.squareup.inject.assisted.Assisted;
import com.squareup.inject.assisted.AssistedInject;

import javax.inject.Inject;

public class GreetingActivity extends AbstractActivity implements GreetingView.Presenter {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final SafeHtml SERVER_ERROR =
		SafeHtmlUtils.fromSafeConstant("An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.");

	@AssistedInject.Factory
	public interface Factory {
		GreetingActivity create(String name);
	}

	private final GreetingView view;
	private final PlaceController placeController;
	private final ${module}Factory factory;
	private final String name;

	private boolean cancelled;

	@AssistedInject
	GreetingActivity(GreetingViewImpl view, PlaceController placeController,
			${module}Factory factory, @Assisted String name) {
		this((GreetingView) view, placeController, factory, name);
	}

	// For tests, independent from view implementation
	GreetingActivity(GreetingView view, PlaceController placeController, ${module}Factory factory, String name) {
		this.view = view;
		this.placeController = placeController;
		this.factory = factory;
		this.name = name;
	}

	@Override
	public void start(final AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		cancelled = false;

		view.setPresenter(this);
		view.setName(this.name);

		factory.greeting().greetServer(this.name).fire(
			new Receiver<GreetingResponseProxy>() {
				public void onFailure(ServerFailure failure) {
					if (cancelled) {
						return;
					}

					showFailureMessage();
				}

				public void onSuccess(GreetingResponseProxy response) {
					if (cancelled) {
						return;
					}

					if (!response.isSuccessful()) {
						showFailureMessage();
						return;
					}

					view.setError(false);
					view.setServerResponse(new SafeHtmlBuilder()
						.appendEscaped(response.getGreeting())
						.appendHtmlConstant("<br><br>I am running ")
						.appendEscaped(response.getServerInfo())
						.appendHtmlConstant(".<br><br>It looks like you are using:<br>")
						.appendEscaped(response.getUserAgent())
						.toSafeHtml());

					panel.setWidget(view);
				}

				private void showFailureMessage() {
					view.setError(true);
					view.setServerResponse(SERVER_ERROR);

					panel.setWidget(view);
				}
			});
	}

	@Override
	public void onCancel() {
		cancelled = true;
	}

	@Override
	public void goBack() {
		placeController.goTo(new HomePlace());
	}
}
