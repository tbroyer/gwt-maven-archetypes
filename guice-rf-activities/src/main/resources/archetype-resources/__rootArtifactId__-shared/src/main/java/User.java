package ${package};

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface User {

        interface Factory extends AutoBeanFactory {

		AutoBean<User> user();

		AutoBean<User> user(User wrapped);
	}

	String getUserName();

	boolean isAdmin();
}

