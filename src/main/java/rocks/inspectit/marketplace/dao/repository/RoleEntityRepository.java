package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;

/**
 * TODO
 * there are currently different methods returning exactly what the front-end expects, due to missing entities.
 * please implement entities and rewrite functions properly.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface RoleEntityRepository extends CrudRepository<RoleEntity, UUID> {

	/**
	 * Finds a role by role.
	 *
	 * @param role {@link String}
	 * @return {@link RoleEntity}
	 */
	RoleEntity findOneByRole(final String role);
}

