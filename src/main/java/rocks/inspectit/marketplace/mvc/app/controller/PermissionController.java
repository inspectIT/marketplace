package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.service.RatingService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class PermissionController {

	private final RatingService ratingService;

	@Autowired
	public PermissionController(final RatingService ratingService) {
		this.ratingService = ratingService;
	}

	/**
	 * check if there is already at least one comment for product by user.
	 * if there is already a comment; return false, since user has no permission to add another comment, else return true.
	 *
	 * @param userName  {@link String}
	 * @param productId {@link UUID}
	 * @return <code>false</code> if at least one comment exists for product by user; <code>true</code> otherwise.
	 */
	@RequestMapping("get/userHasPermission/{userName}/{productId}/comment")
	public Map<String, Boolean> ratingExistsForUserAndProduct(@PathVariable final String userName, @PathVariable final UUID productId) {
		return Collections.singletonMap("ratingExists", this.ratingService.ratingExistForUserAndProduct(userName, productId));
	}

}
