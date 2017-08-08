package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.UserDetailModel;
import rocks.inspectit.marketplace.service.ProfileService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class ProfileController {

	private final ProfileService service;
	private final ObjectMapper mapper;

	@Autowired
	public ProfileController(final ProfileService service, final ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("user/{userId}")
	public UserDetailModel getUserByUserId(@PathVariable final UUID userId) {
		final UserDetailModel model = this.mapper.getUserDetailModelFromUserEntity(this.service.getUserEntityByUserUuid(userId));
		return model;
	}
}
