package rocks.inspectit.marketplace.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.2-SNAPSHOT
 */
@Controller
public class MainRedirectController {

	private static final String LANDING_PAGE_VIEW = "index.html";

	/**
	 * Build the application context path
	 *
	 * @param request {@link HttpServletRequest} to obtain the scheme, server name, server port and a possible context path
	 * @return a {@link String} representation of the application context path
	 */
	@ModelAttribute
	public String applicationContextPath(final HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/**
	 * Render landing page view.
	 *
	 * @return index.html
	 */
	@RequestMapping("/")
	public String getLandingPage() {
		return "redirect:" + LANDING_PAGE_VIEW;
	}

}