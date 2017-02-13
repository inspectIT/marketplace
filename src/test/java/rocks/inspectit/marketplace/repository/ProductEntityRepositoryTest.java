package rocks.inspectit.marketplace.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.repository.jpa.entity.helper.CustomQueryDTO;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@ActiveProfiles("test, h2_db")
@WebAppConfiguration
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestExecutionListeners({ DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ProductEntityRepository repository;

	@Test
	public void findAll() throws Exception {
		List<ProductEntity> entityList = (List<ProductEntity>) repository.findAll();
		assertThat(entityList.size(), is(14));
	}

	@Test
	public void findAllWithRatings() throws Exception {
		List<ProductEntity> list = (List<ProductEntity>) this.repository.findAll();
		assertThat(list.get(0).getRatingEntityList().size(), is(2));
		assertThat(list.get(1).getRatingEntityList().size(), is(2));
		assertThat(list.get(2).getRatingEntityList().size(), is(2));
		assertThat(list.get(3).getRatingEntityList().size(), is(2));
		assertThat(list.get(4).getRatingEntityList().size(), is(2));
		assertThat(list.get(5).getRatingEntityList().size(), is(1));
		assertThat(list.get(6).getRatingEntityList().size(), is(1));
		assertThat(list.get(7).getRatingEntityList().size(), is(1));
		assertThat(list.get(8).getRatingEntityList().size(), is(1));
	}

	@Test
	public void getTotalRating() throws Exception {
		ProductEntity entity = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));
		assertThat(entity.getTotalRating(), is(7.0));

		entity = repository.findOne(UUID.fromString("14144bb2-892f-4657-bcd0-9ead9066f92a"));
		assertThat(entity.getTotalRating(), is(7.));

		entity = repository.findOne(UUID.fromString("a625e6f4-31ea-49be-b71e-eee54e283c43"));
		assertThat(entity.getTotalRating(), is(8.));

		entity = repository.findOne(UUID.fromString("b90bb1f1-6f7e-40a7-86a3-3675d4dea7c5"));
		assertThat(entity.getTotalRating(), is(5.5));

		entity = repository.findOne(UUID.fromString("f9e90ce0-a0e9-4296-868b-05314cb60b5a"));
		assertThat(entity.getTotalRating(), is(6.5));

		entity = repository.findOne(UUID.fromString("4c049192-929d-4a49-8f17-c285928fc769"));
		assertThat(entity.getTotalRating(), is(3.));

		entity = repository.findOne(UUID.fromString("0d36b243-aa29-407e-89d6-666e164c5118"));
		assertThat(entity.getTotalRating(), is(4.));

		entity = repository.findOne(UUID.fromString("6370604e-1d58-4e5b-b1b2-97fd54fb59b5"));
		assertThat(entity.getTotalRating(), is(1.));

		entity = repository.findOne(UUID.fromString("105b789e-84c5-45bf-8722-108533a81530"));
		assertThat(entity.getTotalRating(), is(5.));
	}

	@Test
	public void getProductTag() throws Exception {
		ProductEntity entity = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));
		assertThat(entity.getTagEntity().getTagName(), is("featured"));

		entity = repository.findOne(UUID.fromString("14144bb2-892f-4657-bcd0-9ead9066f92a"));
		assertThat(entity.getTagEntity().getTagName(), is("featured"));

		entity = repository.findOne(UUID.fromString("105b789e-84c5-45bf-8722-108533a81530"));
		assertThat(entity.getTagEntity().getTagName(), is("promoted"));
	}

	/**
	 * there are not enough values with the expected creation date in the dummy db.
	 * therefore we assert that list size will be 5
	 */
	@Test
	public void findTop5ByCreationDateOrderByCreationDate() throws Exception {
		List<ProductEntity> list = repository.findTop20ByCreationDateGreaterThanOrderByCreationDateDesc(Date.from(LocalDateTime.of(2017, 1, 1, 0, 0).atZone(ZoneId.of("GMT")).toInstant()));
		assertThat(list.size(), is(5));
		assertThat(list.get(0).getCreationDate().toString(), is("2017-02-13 19:15:34.372"));
		assertThat(list.get(1).getCreationDate().toString(), is("2017-02-12 16:34:34.372"));
		assertThat(list.get(2).getCreationDate().toString(), is("2017-02-11 17:55:34.372"));
		assertThat(list.get(3).getCreationDate().toString(), is("2017-02-11 17:45:34.372"));
		assertThat(list.get(4).getCreationDate().toString(), is("2017-01-13 12:32:34.372"));
	}

	/**
	 * there are not enough values with the expected creation date in the dummy db.
	 * therefore we assert that list size will be 14 and test only the first three entries
	 */
	@Test
	public void findTop5ByCreationDateLowerOrEqualsOrderByCreationDateDesc() throws Exception {
		List<ProductEntity> list = repository.findTop20ByCreationDateLessThanOrderByCreationDateDesc(new Date());
		assertThat(list.size(), is(14));
		assertThat(list.get(0).getCreationDate().toString(), is("2017-02-13 19:15:34.372"));
		assertThat(list.get(1).getCreationDate().toString(), is("2017-02-12 16:34:34.372"));
		assertThat(list.get(2).getCreationDate().toString(), is("2017-02-12 16:34:34.372"));
		assertThat(list.get(3).getCreationDate().toString(), is("2017-02-11 17:55:34.372"));
	}

	@Test
	public void findAllProductEntitiesGroupByProductEntityOrderedByRatingDesc() throws Exception {
		List<CustomQueryDTO> list = repository.findAllProductEntitiesGroupByProductEntityOrderedByRatingDesc();
		assertThat(list.size(), is(9));

		assertThat(list.get(0).getProductEntity().getTotalRating(), is(8.));
		assertThat(list.get(0).getSum(), is(16L));

		assertThat(list.get(1).getProductEntity().getTotalRating(), is(7.));
		assertThat(list.get(1).getSum(), is(14L));

		assertThat(list.get(2).getProductEntity().getTotalRating(), is(7.));
		assertThat(list.get(2).getSum(), is(14L));

		assertThat(list.get(3).getProductEntity().getTotalRating(), is(6.5));
		assertThat(list.get(3).getSum(), is(13L));

		assertThat(list.get(4).getProductEntity().getTotalRating(), is(5.5));
		assertThat(list.get(4).getSum(), is(11L));

		assertThat(list.get(5).getProductEntity().getTotalRating(), is(5.));
		assertThat(list.get(5).getSum(), is(5L));

		assertThat(list.get(6).getProductEntity().getTotalRating(), is(4.));
		assertThat(list.get(6).getSum(), is(4L));

		assertThat(list.get(7).getProductEntity().getTotalRating(), is(3.));
		assertThat(list.get(7).getSum(), is(3L));

		assertThat(list.get(8).getProductEntity().getTotalRating(), is(1.));
		assertThat(list.get(8).getSum(), is(1L));
	}

}