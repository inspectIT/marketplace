package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;

/**
 * TODO
 * there are currently different methods returning exactly what the front-end expects, due to missing entities.
 * please implement entities and rewrite functions properly.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
public interface UserEntityRepository extends CrudRepository<UserEntity, UUID> {

	/**
	 * Finds a user by his id.
	 *
	 * @param uuid of {@link UUID}
	 * @return a single User of {@link UserEntity}
	 */
	UserEntity findUserEntityByUserUuid(final UUID uuid);

	/**
	 * Finds a user by his unique user name.
	 *
	 * @param name {@link String}
	 * @return {@link UserEntity}
	 */
	UserEntity findOneByName(final String name);
}

