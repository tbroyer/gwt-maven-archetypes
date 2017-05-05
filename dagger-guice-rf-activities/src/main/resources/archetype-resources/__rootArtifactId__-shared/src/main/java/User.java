package ${package};

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface User {

	@JsProperty
	String getUserName();

	@JsProperty
	boolean isAdmin();
}

