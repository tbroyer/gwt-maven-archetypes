package ${package};

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class GreetingViewImpl implements GreetingView {

	private final VerticalPanel dialogVPanel;
	private final Label textToServerLabel;
	private final HTML serverResponseLabel;

	private Presenter presenter;

	GreetingViewImpl() {
		textToServerLabel = new Label();
		serverResponseLabel = new HTML();
		dialogVPanel = new VerticalPanel();

		Button closeButton = new Button("Back");

		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);

		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (presenter != null) {
					presenter.goBack();
				}
			}
		});
	}

	@Override
	public void setError(boolean error) {
		if (error) {
			serverResponseLabel.addStyleName("serverResponseLabelError");
		} else {
			serverResponseLabel.removeStyleName("serverResponseLabelError");
		}
	}

	@Override
	public void setName(String name) {
		textToServerLabel.setText(name);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setServerResponse(SafeHtml response) {
		serverResponseLabel.setHTML(response);
	}

	@Override
	public Widget asWidget() {
		return dialogVPanel;
	}
}
