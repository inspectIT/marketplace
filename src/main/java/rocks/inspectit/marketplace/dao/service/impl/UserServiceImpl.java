package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.UserEntityRepository;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.dao.service.UserService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserEntityRepository repository;

	@Autowired
	public UserServiceImpl(final UserEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserEntity getUserEntityByUuid(UUID userUuid) {
		return this.repository.findOne(userUuid);
	}

	@Override
	public UserEntity getUserEntityByUsername(final String userName) {
		return this.repository.findOneByName(userName);
	}

	@Override
	public void persistUserEntity(final UserEntity userEntity) {
		this.repository.save(userEntity);
	}
}
