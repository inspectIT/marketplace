package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO;

/**
 * TODO.
 * there are currently different methods returning exactly what the front-end expects, due to missing entities.
 * please implement entities and rewrite functions properly
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
public interface ProductEntityRepository extends PagingAndSortingRepository<ProductEntity, UUID> {

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
	 * Use {@link Page} for limited request.
	 * Avoid select all to increase performance.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	Page<ProductEntity> findByCreationDateLessThanOrderByCreationDateDesc(final Date creationDate, final Pageable pageable);

	/**
	 * Select all Products ordered by # Downloads.
	 * Limit query duration by increasing param to a min value; default is 0.
	 *
	 * @param numberOfDownloads {@link Long}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	List<ProductEntity> findTop20ByNumberOfDownloadsGreaterThanOrderByNumberOfDownloadsDesc(final Long numberOfDownloads);

	/**
	 * Custom query for aggregation function not supported by spring-data.
	 * Select all products ordered by rating.
	 * Use a custom dto instead a list of object arrays.
	 *
	 * @return {@link List} of {@link CustomQueryDTO}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select new rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO(pe, sum(re.ratingAsNumber)) "
			+ "from ProductEntity pe "
			+ "join pe.ratingEntityList re "
			+ "group by pe "
			+ "order by 2 DESC")
	List<CustomQueryDTO> findAllProductEntitiesGroupByProductEntityOrderedByRatingDesc();

	/**
	 * Custom named query with names param, for aggregation function not supported by spring-data.
	 * Select products ordered by rating and limit result to size.
	 * Use a custom dto instead a list of object arrays.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link CustomQueryDTO}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select new rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO(pe, sum(re.ratingAsNumber)) "
			+ "from ProductEntity pe "
			+ "join pe.ratingEntityList re "
			+ "group by pe "
			+ "order by 2 DESC ")
	Page<CustomQueryDTO> findPageableProductEntitiesGroupByProductEntityOrderedByRatingDesc(final Pageable pageable);

	/**
	 * ## TODO.
	 *
	 * @param tagName {@link String}
	 * @return {@link List} of {@link CustomQueryDTO}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.tagEntity te "
			+ "where te.tagName = :tagName "
			+ "order by pe.creationDate DESC, pe.numberOfDownloads DESC ")
	List<ProductEntity> findAllByTagNameFromTagEntityOrderedByCreationDateAndNumberOfDownloadsDesc(@Param("tagName") final String tagName);

	/**
	 * ## TODO.
	 *
	 * @param tagName {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link List} of {@link CustomQueryDTO}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.tagEntity te "
			+ "where te.tagName = :tagName "
			+ "order by pe.creationDate DESC, pe.numberOfDownloads DESC ")
	Page<ProductEntity> findPageableByTagNameFromTagEntityOrderedByCreationDateAndNumberOfDownloadsDesc(@Param("tagName") final String tagName, final Pageable pageable);

	/**
	 * ## TODO.
	 * make comparison case insensitive with <b>lower()</b>.
	 *
	 * @param name {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.userEntity ue "
			+ "where lower(pe.name) like concat('%', lower(:name), '%') "
			+ "OR lower(ue.name) like concat('%', lower(:name), '%') "
			+ "order by pe.name ASC")
	Page<ProductEntity> findPageableByProductNameOrUsernameOrderByNameDesc(@Param("name") final String name, final Pageable pageable);
}

