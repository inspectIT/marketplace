package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.dao.service.RoleService;
import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.UserDetailModel;
import rocks.inspectit.marketplace.service.GitHubRestService;
import rocks.inspectit.marketplace.service.ProfileService;
import rocks.inspectit.marketplace.service.dto.GitHubEmailDto;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class UserController {

	private final ProfileService service;
	private final RoleService roleService;
	private final GitHubRestService githubService;
	private final ObjectMapper mapper;

	@Autowired
	public UserController(final ProfileService service, final RoleService roleService, final GitHubRestService githubService, final ObjectMapper mapper) {
		this.service = service;
		this.roleService = roleService;
		this.githubService = githubService;
		this.mapper = mapper;
	}

	/**
	 * Return a simple User of {@link UserDetailModel}.
	 *
	 * @param auth of {@link Authentication}
	 * @return user information of {@link UserDetailModel}
	 */
	@RequestMapping("/get/user")
	public UserDetailModel user(final Authentication auth) {
		// return null if auth is null
		if (auth == null) {
			return null;
		}

		// cast to {@link OAuth2AuthenticationDetails}
		final List<GitHubEmailDto> emailList = this.githubService.getUserEmailFromGithubByToken(((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue());
		final UserDetailModel user = this.mapper.getUserDetailModelFromOAuth2Authentication(auth, emailList);

		// find user entity by username
		final Optional<UserEntity> userEntity = Optional.ofNullable(this.service.getUserEntityByUsername(user.getUserName()));

		// get Role from repository if userEntity is null
		final RoleEntity roleEntity;
		if (userEntity.isPresent()) {
			roleEntity = userEntity.get().getRoleEntity();
		} else {
			roleEntity = this.roleService.getRoleEntityByName(user.getRole());
		}

		// update user entity from user detail model and persist
		this.service.persistUserEntity(this.mapper.getUpdatedUserEntityFromUserDetailModel(userEntity, roleEntity, user));
		return user;
	}

	@RequestMapping("/get/user/{userName}/detail")
	public UserDetailModel user(@PathVariable final String userName) {
		final UserEntity userEntity = this.service.getUserEntityByUsername(userName);
		return this.mapper.getUserDetailModelFromUserEntity(userEntity);
	}
}
