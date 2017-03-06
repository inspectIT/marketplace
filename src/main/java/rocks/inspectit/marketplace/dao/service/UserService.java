package rocks.inspectit.marketplace.dao.service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface UserService {
	/**
	 * ## todo : describe.
	 *
	 * @param userUuid {@link UUID}
	 * @return {@link UserEntity}
	 */
	UserEntity getUserEntityByUuid(final UUID userUuid);

	/**
	 * ## todo : describe.
	 *
	 * @param userName {@link String}
	 * @return {@link UserEntity}
	 */
	UserEntity getUserEntityByUsername(final String userName);

	/**
	 * ## todo : describe.
	 *
	 * @param userEntity {@link UUID}
	 */
	void persistUserEntity(final UserEntity userEntity);
}
