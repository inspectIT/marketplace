package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.dao.service.UserService;
import rocks.inspectit.marketplace.service.ProfileService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	private final UserService service;

	@Autowired
	public ProfileServiceImpl(final UserService service) {
		this.service = service;
	}

	@Override
	public UserEntity getUserEntityByUserUuid(final UUID userUuid) {
		return this.service.getUserEntityByUuid(userUuid);
	}

	@Override
	public UserEntity getUserEntityByUsername(final String userName) {
		return this.service.getUserEntityByUsername(userName);
	}

	@Override
	public void persistUserEntity(final UserEntity userEntity) {
		this.service.persistUserEntity(userEntity);
	}

}
