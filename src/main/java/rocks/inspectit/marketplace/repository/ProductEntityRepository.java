package rocks.inspectit.marketplace.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.repository.jpa.entity.helper.CustomQueryDTO;

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

	/**
	 * Select top 20 results and order by date descending.
	 * Will match all results, that are after "creationDate".
	 *
	 * @param creationDate as {@link Date}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	List<ProductEntity> findTop20ByCreationDateGreaterThanOrderByCreationDateDesc(final Date creationDate);

	/**
	 * Select top 20 results and order by date descending.
	 * The first match will have at least the date of "yesterday".
	 *
	 * @param creationDate {@link Date}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	List<ProductEntity> findTop20ByCreationDateLessThanOrderByCreationDateDesc(final Date creationDate);

	/**
	 * Custom query for aggregation function not supported by spring-data.
	 * Select all products ordered by rating.
	 * Use a custom dto instead a list of object arrays.
	 *
	 * @return {@link List} of {@link CustomQueryDTO}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select new rocks.inspectit.marketplace.repository.jpa.entity.helper.CustomQueryDTO(pe, sum(re.ratingAsNumber)) "
			+ "from ProductEntity pe "
			+ "join pe.ratingEntityList re "
			+ "group by pe "
			+ "order by 2 DESC")
	List<CustomQueryDTO> findAllProductEntitiesGroupByProductEntityOrderedByRatingDesc();
}

