package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
public class UserController {

	/**
	 * Return a simple User of {@link Principal}.
	 *
	 * @param principal of {@link Principal}
	 * @return user information of {@link Principal}
	 */
	@CrossOrigin
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
