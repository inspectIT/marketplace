package rocks.inspectit.marketplace.service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface ProfileService {

	UserEntity getUserEntityByUserUuid(final UUID userUuid);

	UserEntity getUserEntityByUsername(final String userName);

	void persistUserEntity(final UserEntity userEntity);
}
