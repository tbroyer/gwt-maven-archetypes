package ${package};

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter for Ajax requests, for applicative (client) authentication handling.
 * <p>
 * Returns a 401 (Unauthorized) HTTP status code if a request is not authenticated, instead of using the container's
 * "standard" authentication method (e.g. redirecting to a login page) which could be undetected by the client Web app.
 * <p>
 * <b>Implémentation note</b>: largely inspired by <a href=
 * "http://code.google.com/p/google-web-toolkit/source/browse/tags/2.4.0/samples/expenses/src/main/java/com/google/gwt/sample/gaerequest/server/GaeAuthFilter.java"
 * ><code>GaeAuthFilter</code></a>.
 */
public class AjaxAuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// no-op
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (request.getUserPrincipal() == null) {
			// TODO: per spec, HTTP requires a WWW-Authenticate header on 401 (Unauthorized) responses
			// Should it use http://tools.ietf.org/html/draft-broyer-http-cookie-auth ?
			// or a "custom" authentication scheme?
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// no-op
	}
}
