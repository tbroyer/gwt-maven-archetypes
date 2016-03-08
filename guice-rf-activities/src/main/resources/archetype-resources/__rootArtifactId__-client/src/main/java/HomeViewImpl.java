package ${package};

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HomeViewImpl implements HomeView {

	public interface Binder extends UiBinder<Widget, HomeViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private final Widget w;

	private Presenter presenter;

	@UiField TextBox nameField;
	@UiField Button  sendButton;
	@UiField Label   errorLabel;

	@Inject
	HomeViewImpl() {
		w = binder.createAndBindUi(this);
	}

	@Override
	public void setUserName(String userName) {
		nameField.setText(userName);
	}

	@Override
	public void setEnabled(boolean enabled) {
		sendButton.setEnabled(enabled);
	}

	@Override
	public void setError(String error) {
		errorLabel.setText(error);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Widget asWidget() {
		return w;
	}

	@UiHandler("sendButton")
	void onClick(ClickEvent event) {
		sendNameToServer();
	}

	@UiHandler("nameField")
	void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			sendNameToServer();
		}
	}

	private void sendNameToServer() {
		if (presenter != null) {
			presenter.sendNameToServer(nameField.getText());
		}
	}
}
