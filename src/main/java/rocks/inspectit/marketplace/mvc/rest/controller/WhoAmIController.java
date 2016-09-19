package rocks.inspectit.marketplace.mvc.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rocks.inspectit.marketplace.mvc.rest.model.WhoAmIResponseModel;

/**
 * Prints simple project information.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.1-SNAPSHOT
 */
@RestController
public class WhoAmIController {

	/**
	 * set to public for junit test.
	 */
	public static final String URL_MAPPING = "/whoami";

	private final String applicationName;
	private final String applicationVersion;

	@Autowired
	public WhoAmIController(
			@Value("${marketplace.application.name}")
			final String applicationName,
			@Value("${marketplace.application.version}")
			final String applicationVersion) {
		this.applicationName = applicationName;
		this.applicationVersion = applicationVersion;
	}

	@RequestMapping(value = URL_MAPPING, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WhoAmIResponseModel> get() {
		return new ResponseEntity<>(new WhoAmIResponseModel(applicationName, applicationVersion), HttpStatus.OK);
	}

}

