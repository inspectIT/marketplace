package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface KeywordEntityRepository extends CrudRepository<KeywordEntity, UUID> {




}

