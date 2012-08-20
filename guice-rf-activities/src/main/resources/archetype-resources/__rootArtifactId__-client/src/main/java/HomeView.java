package ${package};

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(HomeViewImpl.class)
public interface HomeView extends IsWidget {

	public interface Presenter {

		void sendNameToServer(String name);
	}

	void setUserName(String userName);

	void setEnabled(boolean enabled);

	void setError(String error);

	void setPresenter(Presenter presenter);
}

