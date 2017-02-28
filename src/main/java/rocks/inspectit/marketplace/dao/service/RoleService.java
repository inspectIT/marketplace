package rocks.inspectit.marketplace.dao.service;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.X-SNAPSHOT
 */
public interface RoleService {
	RoleEntity getRoleEntityByName(final String name);
}
