package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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
	Page<CustomQueryDTO> findAllGroupByProductEntityOrderedByRatingDesc(final Pageable pageable);

	/**
	 * Custom named query with names param, for aggregation function not supported by spring-data.
	 * Select products ordered by rating and limit result to size.
	 * Use a custom dto instead a list of object arrays.
	 *
	 * @param productUuidList {@link List} of {@link UUID}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link CustomQueryDTO}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Query("select new rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO(pe, sum(re.ratingAsNumber)) "
			+ "from ProductEntity pe "
			+ "join pe.ratingEntityList re "
			+ "where pe.productUuid in (:productUuids)"
			+ "group by pe "
			+ "order by 2 DESC ")
	Page<CustomQueryDTO> findAllLimitByProductUuidGroupByProductEntityOrderedByRatingDesc(@Param("productUuids") final List<UUID> productUuidList, final Pageable pageable);

	/**
	 * ## TODO.
	 *
	 * @param tagName  {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.tagEntity te "
			+ "where te.tagName = :tagName ")
	Page<ProductEntity> findAllByTagNameFromTagEntity(@Param("tagName") final String tagName, final Pageable pageable);

	/**
	 * ## TODO.
	 *
	 * @param tagName     {@link String}
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link List} of {@link CustomQueryDTO}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.tagEntity te "
			+ "join pe.keywordEntityList ke "
			+ "where te.tagName = :tagName "
			+ "and ke.name in (:keywords) "
			+ "or ke.alias in (:keywords) ")
	Page<ProductEntity> findAllByTagNameFromTagEntityLimitByKeywords(@Param("tagName") final String tagName, @Param("keywords") final List<String> limitToList, final Pageable pageable);

	/**
	 * ## TODO.
	 * make comparison case insensitive with <b>lower()</b>.
	 *
	 * @param name     {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.userEntity ue "
			+ "where lower(pe.name) like concat('%', lower(:name), '%') "
			+ "OR lower(ue.name) like concat('%', lower(:name), '%') ")
	Page<ProductEntity> findAllByProductNameOrUsernameOrderByNameDesc(@Param("name") final String name, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param keywordList {@link List} or {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Query("select pe "
			+ "from ProductEntity pe "
			+ "join pe.keywordEntityList ke "
			+ "where ke.name in (:keywordList) "
			+ "or ke.alias in (:keywordList)")
	Page<ProductEntity> findAllByKeywordEntityNameIn(@Param("keywordList") final List<String> keywordList, final Pageable pageable);

	/**
	 * ## todo : describe.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @return {@link List} of {@link UUID}
	 */
	@Query("select pe.productUuid "
			+ "from ProductEntity pe "
			+ "join pe.keywordEntityList ke "
			+ "where ke.name in (:keywords) "
			+ "or ke.alias in (:keywords)")
	List<UUID> findAllUuidByKeywords(@Param("keywords") final List<String> limitToList);

}

