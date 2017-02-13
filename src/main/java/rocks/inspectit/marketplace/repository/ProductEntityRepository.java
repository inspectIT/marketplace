package rocks.inspectit.marketplace.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;

/**
 * TODO.
 * there are currently different methods returning exactly what the front-end expects, due to missing entities.
 * please implement entities and rewrite functions properly
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
public interface ProductEntityRepository extends CrudRepository<ProductEntity, UUID> {
}

