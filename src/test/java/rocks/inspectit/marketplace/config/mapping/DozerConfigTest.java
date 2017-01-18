package rocks.inspectit.marketplace.config.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.repository.jpa.entity.UserEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test mapping from Product Entity to Dashboard Model
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.X-SNAPSHOT
 */
public class DozerConfigTest {

	private static final String DEFAULT_CREATION_USER = "CampusUser_Default";

	private DozerBeanMapper mapper;

	@Before
	public void setUp() throws Exception {
		final DozerConfig dozerConfiguration = new DozerConfig();
		final BeanMappingBuilder mappingBuilder = dozerConfiguration.getBeanMappingBuilder();
		mapper = dozerConfiguration.dozerBean(mappingBuilder);
	}

	@Test
	public void testMapProductEntityToDashboardModel() throws Exception {
		// setup entity
		final ProductEntity productEntity = new ProductEntity();
		final UserEntity productEntityInner = new UserEntity();

		productEntity.setProductUuid(UUID.randomUUID());
		productEntity.setActive(true);
		productEntity.setCreationDate(new Date());
		productEntity.setDescription("Lorem Ipsum Kraut");
		productEntity.setModifyDate(new Date());
		productEntity.setName("Awesome Product Description is Awesome");
		productEntity.setNumberOfDownloads(1235578L);
		productEntity.setTag("webTag");
		productEntity.setTotalRating(47.11);

		// we only need username
		productEntityInner.setUserName("not a nik");
		productEntity.setUserEntity(productEntityInner);

		final DashBoardModel mappedResult = mapper.map(productEntity, DashBoardModel.class);

		// test some random attributess
		assertEquals(productEntity.getProductUuid(), mappedResult.getId());
		assertEquals(productEntity.getName(), mappedResult.getName());
		assertNotNull(mappedResult.getCreationDate());
		assertNotNull(mappedResult.getAuthor());
		assertEquals(mappedResult.getAuthor(), "not a nik");

	}

}