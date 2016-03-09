package ${package};

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import java.util.Objects;

public class GreetingPlace extends Place {

	@Prefix("greeting")
	public static class Tokenizer implements PlaceTokenizer<GreetingPlace> {

		@Override
		public GreetingPlace getPlace(String token) {
			return new GreetingPlace(token);
		}

		@Override
		public String getToken(GreetingPlace place) {
			return place.getUser();
		}
	}

	private final String user;

	public GreetingPlace(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o instanceof GreetingPlace) {
			GreetingPlace other = (GreetingPlace) o;
			return Objects.equals(this.user, other.user);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.user);
	}
}

