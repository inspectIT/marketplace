package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.inspectit.marketplace.dao.repository.RoleEntityRepository;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;
import rocks.inspectit.marketplace.dao.service.RoleService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.X-SNAPSHOT
 */
@Service
public class RoleServiceImpl implements RoleService {
	private final RoleEntityRepository repository;

	@Autowired
	public RoleServiceImpl(final RoleEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public RoleEntity getRoleEntityByName(final String name) {
		return this.repository.findOneByRole(name);
	}
}
