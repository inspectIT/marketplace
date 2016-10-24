package rocks.inspectit.marketplace.mvc.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import rocks.inspectit.marketplace.config.ActiveProfile;
import rocks.inspectit.marketplace.mvc.advice.model.ErrorMessage;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.X-SNAPSHOT
 */
@ControllerAdvice
public class GeneralControllerAdvice {

	private final static Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

	/**
	 * Main Exception handler
	 *
	 * @param request default {@link HttpServletRequest} object
	 * @param ex      default {@link Exception} object
	 * @return ErrorMessage {@link ErrorMessage} with exception stacktrace
	 */
	@Profile(ActiveProfile.DEV)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorMessage handleException(final HttpServletRequest request, final Exception ex) {
		LOG.error("Request: " + request.getRequestURL() + " raised " + ex);
		return new ErrorMessage(request.getRequestURL().toString(), ex);
	}
}
