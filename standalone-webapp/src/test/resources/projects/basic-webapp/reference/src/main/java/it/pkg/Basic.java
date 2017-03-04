package it.pkg;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Basic implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Hi!");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");

		// We can add style names or modify the element style to widgets
		sendButton.addStyleName("sendButton");
		sendButton.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);

		// Add the nameField and sendButton to the RootPanel
		RootPanel.get().add(nameField);
		RootPanel.get().add(sendButton);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Add a handler to show the name as an alert
		sendButton.addClickHandler(new ClickHandler() {
			@Override public void onClick(ClickEvent event) {
				sayHello(nameField.getText());
			}
		});
		nameField.addKeyUpHandler(new KeyUpHandler() {
			@Override public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sayHello(nameField.getText());
				}
			}
		});
	}

	private void sayHello(String name) {
		Window.alert("Hello " + name + "!");
	}
}
