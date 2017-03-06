package rocks.inspectit.marketplace.dao.service;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
public interface RoleService {
	/**
	 * ## todo : describe.
	 *
	 * @param name {@link String}
	 * @return {@link RoleEntity}
	 */
	RoleEntity getRoleEntityByName(final String name);
}
