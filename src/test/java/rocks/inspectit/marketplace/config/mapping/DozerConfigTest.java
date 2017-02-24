package rocks.inspectit.marketplace.config.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 * Test mapping from Product Entity to Dashboard Model,
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@ActiveProfiles("test,h2_db")
public class DozerConfigTest {

	private static final String DEFAULT_CREATION_USER = "CampusUser_Default";

	private DozerBeanMapper mapper;

	@Before
	public void setUp() throws Exception {
		final DozerConfig dozerConfiguration = new DozerConfig();
		final BeanMappingBuilder mappingBuilder = dozerConfiguration.getBeanMappingBuilder();
		mapper = dozerConfiguration.dozerBean(mappingBuilder);
	}

	/**
	 * update test case. simple filed rating is now an object of type {@link RatingEntity}
	 */
	@Test
	public void testMapProductEntityToOverviewItemModel() throws Exception {
		// setup entity
		final ProductEntity productEntity = new ProductEntity();
		final UserEntity innerClassUserEntity = new UserEntity();
		final RatingEntity innerClassRatingEntity = new RatingEntity();

		productEntity.setProductUuid(UUID.randomUUID());
		productEntity.setActive(true);
		productEntity.setCreationDate(new Date());
		productEntity.setDescription("Lorem Ipsum Kraut");
		productEntity.setModifyDate(new Date());
		productEntity.setName("Awesome Product Description is Awesome");
		productEntity.setNumberOfDownloads(1235578L);

		//		we only need username
		innerClassUserEntity.setName("not a nik");

		// we only need rating and description
		innerClassRatingEntity.setRatingAsNumber(10);
		innerClassRatingEntity.setRatingDescription("Lorem Ipsum");

		productEntity.setUserEntity(innerClassUserEntity);
		productEntity.setRatingEntityList(new ArrayList<RatingEntity>() {{
			add(innerClassRatingEntity);
		}});

		final OverviewItemModel mappedResult = mapper.map(productEntity, OverviewItemModel.class);
		mappedResult.setRating(productEntity.getTotalRating().orElse(0.));

		// test some random attributess
		assertEquals(productEntity.getProductUuid(), mappedResult.getId());
		assertEquals(productEntity.getName(), mappedResult.getName());
		assertNotNull(mappedResult.getCreationDate());
		assertNotNull(mappedResult.getAuthor());
		assertEquals(mappedResult.getAuthor(), "not a nik");
	}

	@Test
	public void testMapProductEntityToDetailItemModel() throws Exception {
		// TODO
	}

	@Test
	public void testMapRatingEntityToRatingItemModel() throws Exception {
		// TODO
	}
}