package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface RatingEntityRepository extends CrudRepository<RatingEntity, UUID> {
}

