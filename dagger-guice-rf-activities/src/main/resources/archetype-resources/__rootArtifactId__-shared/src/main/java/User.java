package ${package};

import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface User {

	String getUserName();

	boolean isAdmin();
}

