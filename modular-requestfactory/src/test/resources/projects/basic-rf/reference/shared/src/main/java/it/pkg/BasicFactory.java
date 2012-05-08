package it.pkg;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface BasicFactory extends RequestFactory {
	GreetingContext greeting();
}