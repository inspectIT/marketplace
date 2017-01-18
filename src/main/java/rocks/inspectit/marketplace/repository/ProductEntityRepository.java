package rocks.inspectit.marketplace.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;

/**
 * FIXME
 * there are currently different methods returning exactly what the front-end expects, due to missing entities. please implement entities and rewrite functions properly
 * TODO write unit tests
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
public interface ProductEntityRepository extends CrudRepository<ProductEntity, UUID> {
	List<ProductEntity> findAll();

	List<ProductEntity> findByTag(final String tag);

	List<ProductEntity> findTop10ByTag(final String tag);
}

