package rocks.inspectit.marketplace.dao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.Order;

/**
 * Make sure to stay in test context, therefore use test and h2 profiles
 * for test application.properties by adding {@link ActiveProfiles} annotation.
 * <p/>
 * <p>
 * Avoid testing if {@link rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity}, {@link RatingEntity} and {@link rocks.inspectit.marketplace.dao.repository.jpa.entity.TagEntity} of {@link ProductEntity} are  null or empty. Some Entities might not be set or initialized.
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
	public void testFindAll() throws Exception {
		List<ProductEntity> entityList = (List<ProductEntity>) repository.findAll();
		assertThat(entityList.size(), is(47));
	}

	@Test
	public void testFindAllWithRatings() throws Exception {
		List<ProductEntity> list = (List<ProductEntity>) this.repository.findAll();
		assertThat(list.get(0).getRatingEntityList().size(), is(6));
		assertThat(list.get(1).getRatingEntityList().size(), is(6));
		assertThat(list.get(2).getRatingEntityList().size(), is(6));
		assertThat(list.get(3).getRatingEntityList().size(), is(6));
		assertThat(list.get(4).getRatingEntityList().size(), is(6));
		assertThat(list.get(5).getRatingEntityList().size(), is(5));
		assertThat(list.get(6).getRatingEntityList().size(), is(5));
		assertThat(list.get(7).getRatingEntityList().size(), is(5));
		assertThat(list.get(8).getRatingEntityList().size(), is(3));
		assertThat("the list size should be equals to a distinct list size", list.size(), is((int) list.stream().distinct().count()));
	}

	@Test
	public void testGetTotalRating() throws Exception {
		ProductEntity entity = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));
		assertThat(entity.getTotalRating().get(), is(7.0));

		entity = repository.findOne(UUID.fromString("14144bb2-892f-4657-bcd0-9ead9066f92a"));
		assertThat(entity.getTotalRating().get(), is(7.));

		entity = repository.findOne(UUID.fromString("a625e6f4-31ea-49be-b71e-eee54e283c43"));
		assertThat(entity.getTotalRating().get(), is(8.));

		entity = repository.findOne(UUID.fromString("b90bb1f1-6f7e-40a7-86a3-3675d4dea7c5"));
		assertThat(entity.getTotalRating().get(), is(5.5));

		entity = repository.findOne(UUID.fromString("f9e90ce0-a0e9-4296-868b-05314cb60b5a"));
		assertThat(entity.getTotalRating().get(), is(6.5));

		entity = repository.findOne(UUID.fromString("4c049192-929d-4a49-8f17-c285928fc769"));
		assertThat(entity.getTotalRating().get(), is(2.2));

		entity = repository.findOne(UUID.fromString("0d36b243-aa29-407e-89d6-666e164c5118"));
		assertThat(entity.getTotalRating().get(), is(4.4));

		entity = repository.findOne(UUID.fromString("6370604e-1d58-4e5b-b1b2-97fd54fb59b5"));
		assertThat(entity.getTotalRating().get(), is(4.2));

		entity = repository.findOne(UUID.fromString("105b789e-84c5-45bf-8722-108533a81530"));
		assertThat(entity.getTotalRating().get(), is(5.));
	}

	@Test
	public void testGetProductTag() throws Exception {
		ProductEntity entity = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));
		assertThat(entity.getTagEntity().getTagName(), is("featured"));

		entity = repository.findOne(UUID.fromString("14144bb2-892f-4657-bcd0-9ead9066f92a"));
		assertThat(entity.getTagEntity().getTagName(), is("featured"));

		entity = repository.findOne(UUID.fromString("105b789e-84c5-45bf-8722-108533a81530"));
		assertThat(entity.getTagEntity().getTagName(), is("promoted"));
	}

	@Test
	public void testFindAllByPageable() throws Exception {
		// order by name
		final Page<ProductEntity> pageName = repository.findAll(new PageRequest(0, 10000, new Sort(Direction.DESC, "name")));
		assertThat(pageName.getTotalElements(), is(47L));
		assertThat(pageName.getContent().size(), is((int) 47));
		assertThat(pageName.getContent().get(0).getName(), is("product 93 product 11"));
		assertThat(pageName.getContent().get(0).getNumberOfDownloads(), is(7725L));
		assertThat(pageName.getContent().get(0).getCreationDate().toString(), is("2017-02-13 21:12:34.372"));
		assertThat(pageName.getContent().get(0).getTotalRating().orElse(0.), is(0.));
		assertThat("the list size should be equals to a distinct list size", pageName.getContent().size(), is((int) pageName.getContent().stream().distinct().count()));

		// order by number of downloads
		final Page<ProductEntity> pageDownloads = repository.findAll(new PageRequest(0, 10000, new Sort(Direction.DESC, "numberOfDownloads")));
		assertThat(pageDownloads.getTotalElements(), is(47L));
		assertThat(pageDownloads.getContent().size(), is(47));
		assertThat(pageDownloads.getContent().get(0).getName(), is("product  8"));
		assertThat(pageDownloads.getContent().get(0).getNumberOfDownloads(), is(99125L));
		assertThat(pageDownloads.getContent().get(0).getCreationDate().toString(), is("2014-02-13 19:15:34.372"));
		assertThat(pageDownloads.getContent().get(0).getTotalRating().orElse(0.), is(4.2));
		assertThat("the list size should be equals to a distinct list size", pageDownloads.getContent().size(), is((int) pageDownloads.getContent().stream().distinct().count()));

		// order by creation date
		final Page<ProductEntity> pageCreationDate = repository.findAll(new PageRequest(0, 10000, new Sort(Direction.DESC, "creationDate")));
		assertThat(pageCreationDate.getTotalElements(), is(47L));
		assertThat(pageCreationDate.getContent().size(), is(47));
		assertThat(pageCreationDate.getContent().get(0).getName(), is("PRODUCT 18 PRODUCT  7"));
		assertThat(pageCreationDate.getContent().get(0).getNumberOfDownloads(), is(54125L));
		assertThat(pageCreationDate.getContent().get(0).getCreationDate().toString(), is("2017-02-25 18:16:34.372"));
		assertThat(pageCreationDate.getContent().get(0).getTotalRating().orElse(0.), is(0.));
		assertThat("the list size should be equals to a distinct list size", pageCreationDate.getContent().size(), is((int) pageCreationDate.getContent().stream().distinct().count()));

		// order by userEntity.name
		final Page<ProductEntity> pageUserEntityName = repository.findAll(new PageRequest(0, 10000, new Sort(Direction.DESC, "userEntity.name")));
		assertThat(pageUserEntityName.getTotalElements(), is(47L));
		assertThat(pageUserEntityName.getContent().size(), is(47));
		// don't check for product values since this can change on each select, due to multi products for a user
		assertThat(pageUserEntityName.getContent().get(0).getUserEntity().getName(), is("stefe"));
		assertThat("the list size should be equals to a distinct list size", pageUserEntityName.getContent().size(), is((int) pageUserEntityName.getContent().stream().distinct().count()));

		final Page<ProductEntity> pageTagOrderEntityName = repository.findAll(
				new PageRequest(0, 10000, new Sort(
						new Order(Direction.DESC, "tagEntity.tagName").ignoreCase())));
		assertThat(pageTagOrderEntityName.getTotalElements(), is(47L));
		assertThat(pageTagOrderEntityName.getContent().size(), is(47));
		// don't check for product values since this can change on each select, due to multi products for a user
		assertThat(pageTagOrderEntityName.getContent().get(0).getTagEntity().getTagName(), is("promoted"));
		assertThat(pageTagOrderEntityName.getContent()
				.stream().filter(productEntity -> productEntity.getTagEntity().getTagName().equalsIgnoreCase("promoted"))
				.count(), is(30L));
		assertThat(pageTagOrderEntityName.getContent().get(30).getTagEntity().getTagName(), is("featured"));
		assertThat(pageTagOrderEntityName.getContent()
				.stream().filter(productEntity -> productEntity.getTagEntity().getTagName().equalsIgnoreCase("featured"))
				.count(), is(17L));
		assertThat("the list size should be equals to a distinct list size", pageTagOrderEntityName.getContent().size(), is((int) pageTagOrderEntityName.getContent().stream().distinct().count()));

		final Page<ProductEntity> pageTagSortEntityName = repository.findAll(new PageRequest(0, 10000, new Sort(Direction.ASC, "tagEntity.tagName")));
		assertThat(pageTagSortEntityName.getTotalElements(), is(47L));
		assertThat(pageTagSortEntityName.getContent().size(), is(47));
		// don't check for product values since this can change on each select, due to multi products for a user
		assertThat(pageTagSortEntityName.getContent().get(0).getTagEntity().getTagName(), is("featured"));
		assertThat(pageTagSortEntityName.getContent()
				.stream().filter(productEntity -> productEntity.getTagEntity().getTagName().equalsIgnoreCase("featured"))
				.count(), is(17L));
		assertThat(pageTagSortEntityName.getContent().get(17).getTagEntity().getTagName(), is("promoted"));
		assertThat(pageTagSortEntityName.getContent()
				.stream().filter(productEntity -> productEntity.getTagEntity().getTagName().equalsIgnoreCase("promoted"))
				.count(), is(30L));
		assertThat("the list size should be equals to a distinct list size", pageTagSortEntityName.getContent().size(), is((int) pageTagSortEntityName.getContent().stream().distinct().count()));
	}

	@Test
	public void testFindAllProductEntitiesGroupByProductEntityOrderedByRatingDesc() throws Exception {
		Page<CustomQueryDTO> list = repository.findProductsSumRatingAndAverageRatingGroupByProductEntity(new PageRequest(0, 2000,
				new Sort(
						new Order(Direction.DESC, "avgRating")
				)));
		assertThat(list.getContent().size(), is(9));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		assertThat(list.getContent().get(1).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(1).getSum(), is(42L));

		assertThat(list.getContent().get(2).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(2).getSum(), is(42L));

		assertThat(list.getContent().get(3).getProductEntity().getTotalRating().get(), is(6.5));
		assertThat(list.getContent().get(3).getSum(), is(39L));

		assertThat(list.getContent().get(4).getProductEntity().getTotalRating().get(), is(5.5));
		assertThat(list.getContent().get(4).getSum(), is(33L));

		assertThat(list.getContent().get(5).getProductEntity().getTotalRating().get(), is(5.));
		assertThat(list.getContent().get(5).getSum(), is(15L));

		assertThat(list.getContent().get(6).getProductEntity().getTotalRating().get(), is(4.4));
		assertThat(list.getContent().get(6).getSum(), is(22L));

		assertThat(list.getContent().get(7).getProductEntity().getTotalRating().get(), is(4.2));
		assertThat(list.getContent().get(7).getSum(), is(21L));

		assertThat(list.getContent().get(8).getProductEntity().getTotalRating().get(), is(2.2));
		assertThat(list.getContent().get(8).getSum(), is(11L));

		list = repository.findProductsSumRatingAndAverageRatingGroupByProductEntity(new PageRequest(0, 2000,
				new Sort(
						new Order(Direction.DESC, "sumRating")
				)));
		assertThat(list.getContent().size(), is(9));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		assertThat(list.getContent().get(1).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(1).getSum(), is(42L));

		assertThat(list.getContent().get(2).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(2).getSum(), is(42L));

		assertThat(list.getContent().get(3).getProductEntity().getTotalRating().get(), is(6.5));
		assertThat(list.getContent().get(3).getSum(), is(39L));

		assertThat(list.getContent().get(4).getProductEntity().getTotalRating().get(), is(5.5));
		assertThat(list.getContent().get(4).getSum(), is(33L));

		assertThat(list.getContent().get(5).getProductEntity().getTotalRating().get(), is(4.4));
		assertThat(list.getContent().get(5).getSum(), is(22L));

		assertThat(list.getContent().get(6).getProductEntity().getTotalRating().get(), is(4.2));
		assertThat(list.getContent().get(6).getSum(), is(21L));

		assertThat(list.getContent().get(7).getProductEntity().getTotalRating().get(), is(5.));
		assertThat(list.getContent().get(7).getSum(), is(15L));

		assertThat(list.getContent().get(8).getProductEntity().getTotalRating().get(), is(2.2));
		assertThat(list.getContent().get(8).getSum(), is(11L));
	}

	@Test
	public void testFindProductsSumRatingAndAverageRatingGroupByProductEntity() throws Exception {
		final Pageable page0 = new PageRequest(0, 5, new Sort(Direction.DESC, "avgRating"));
		Page<CustomQueryDTO> list = repository.findProductsSumRatingAndAverageRatingGroupByProductEntity(page0);
		assertThat(list.getContent().size(), is(5));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		assertThat(list.getContent().get(1).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(1).getSum(), is(42L));

		assertThat(list.getContent().get(2).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(2).getSum(), is(42L));

		assertThat(list.getContent().get(3).getProductEntity().getTotalRating().get(), is(6.5));
		assertThat(list.getContent().get(3).getSum(), is(39L));

		assertThat(list.getContent().get(4).getProductEntity().getTotalRating().get(), is(5.5));
		assertThat(list.getContent().get(4).getSum(), is(33L));

		final Pageable page1 = new PageRequest(0, 5, new Sort(Direction.DESC, "avgRating", "name"));
		list = repository.findProductsSumRatingAndAverageRatingGroupByProductEntity(page1);
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		assertThat(list.getContent().get(1).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(1).getSum(), is(42L));

		assertThat(list.getContent().get(2).getProductEntity().getTotalRating().get(), is(7.));
		assertThat(list.getContent().get(2).getSum(), is(42L));

		assertThat(list.getContent().get(3).getProductEntity().getTotalRating().get(), is(6.5));
		assertThat(list.getContent().get(3).getSum(), is(39L));

		assertThat(list.getContent().get(4).getProductEntity().getTotalRating().get(), is(5.5));
		assertThat(list.getContent().get(4).getSum(), is(33L));
	}

	@Test
	public void testFindPageableProductEntitiesGroupByProductEntityLimitByKeywordOrderedByRatingDesc() throws Exception {
		final String jee = "jee";
		final String jpa = "jpa";
		final String spring = "spring";
		final String hibernate = "hibernate";

		final List<String> filterToList = new ArrayList<>();

		final Pageable page = new PageRequest(0, 10000);
		final Pageable ratingPage = new PageRequest(0, 10000, new Sort(Direction.DESC, "avgRating", "creationDate"));

		filterToList.add(jee);
		List<UUID> productUuid = repository.findAllByKeywordEntityNameIn(filterToList, page).getContent().stream().map(ProductEntity::getProductUuid).collect(Collectors.toList());
		assertThat(productUuid.size(), is(4));

		Page<CustomQueryDTO> list = repository.findProductsSumRatingAndAverageRatingByProductUuidsGroupByProductEntity(productUuid, ratingPage);
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));
		assertThat(list.getContent().size(), is(4));

		filterToList.add(jee);
		productUuid = repository.findAllByKeywordEntityNameIn(filterToList, page).getContent().stream().map(ProductEntity::getProductUuid).collect(Collectors.toList());
		assertThat(productUuid.size(), is(4));

		list = repository.findProductsSumRatingAndAverageRatingByProductUuidsGroupByProductEntity(productUuid, ratingPage);
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));
		assertThat(list.getContent().size(), is(4));
		assertThat(list.getContent().get(0).getProductEntity().getName(), is("product  3"));
		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		filterToList.add(hibernate);
		productUuid = repository.findAllByKeywordEntityNameIn(filterToList, page).getContent().stream().map(ProductEntity::getProductUuid).collect(Collectors.toList());
		assertThat(productUuid.size(), is(4));

		list = repository.findProductsSumRatingAndAverageRatingByProductUuidsGroupByProductEntity(productUuid, ratingPage);
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));
		assertThat(list.getContent().size(), is(4));
		assertThat(list.getContent().get(0).getProductEntity().getName(), is("product  3"));
		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));

		filterToList.add(spring);
		filterToList.add(jpa);
		productUuid = repository.findAllByKeywordEntityNameIn(filterToList, page).getContent().stream().map(ProductEntity::getProductUuid).collect(Collectors.toList());
		assertThat(productUuid.size(), is(9));

		list = repository.findProductsSumRatingAndAverageRatingByProductUuidsGroupByProductEntity(productUuid, ratingPage);
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));
		assertThat(list.getContent().size(), is(9));
		assertThat(list.getContent().get(0).getProductEntity().getName(), is("product  3"));
		assertThat(list.getContent().get(0).getProductEntity().getTotalRating().get(), is(8.));
		assertThat(list.getContent().get(0).getSum(), is(48L));
	}

	@Test
	public void testFindAllByTagNameFromTagEntityOrderedByCreationDateAndNumberOfDownloadsDesc() throws Exception {
		final List<ProductEntity> promotedList = repository.findAllByTagNameFromTagEntity("promoted", null).getContent();
		final List<ProductEntity> featuredList = repository.findAllByTagNameFromTagEntity("featured", null).getContent();
		final List<ProductEntity> unknownList = repository.findAllByTagNameFromTagEntity("unknown", null).getContent();

		assertThat(promotedList.size(), is(30));
		assertThat("the list size should be equals to a distinct list size", promotedList.size(), is((int) promotedList.stream().distinct().count()));

		assertThat(featuredList.size(), is(17));
		assertThat("the list size should be equals to a distinct list size", featuredList.size(), is((int) featuredList.stream().distinct().count()));

		assertThat(unknownList.size(), is(0));
		assertThat("the list size should be equals to a distinct list size", unknownList.size(), is((int) unknownList.stream().distinct().count()));
	}

	@Test
	public void testFindPageableByProductNameOrUsernameOrderByNameDesc() throws Exception {
		final Pageable pageable = new PageRequest(0, 10000);
		final String productNameAsSearchTerm = "product";
		Page<ProductEntity> list = repository.findAllByProductNameOrUsernameOrderByNameDesc(productNameAsSearchTerm, pageable);
		assertThat("assert that a search for name 'product' will return all products", list.getContent().size(), is(47));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String userNameAsSearchTerm = "nik";
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(userNameAsSearchTerm, pageable);
		assertThat(list.getContent().size(), is(30));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String productAsSearchTermCaseSensitive = "pRoDuCt";
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(productAsSearchTermCaseSensitive.toLowerCase(), pageable);
		assertThat(list.getContent().size(), is(47));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String product1AsSearchTermCaseSensitive = "pRoDuCt 1";
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(product1AsSearchTermCaseSensitive.toLowerCase(), pageable);
		assertThat(list.getContent().size(), is(14));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String partNameAsSearchTermCaseSensitive = "uCt 2";
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(partNameAsSearchTermCaseSensitive, pageable);
		assertThat(list.getContent().size(), is(4));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String nullValue = null;
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(nullValue, pageable);
		assertThat(list.getContent().size(), is(0));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));

		final String emptyValue = null;
		list = repository.findAllByProductNameOrUsernameOrderByNameDesc(emptyValue, pageable);
		assertThat(list.getContent().size(), is(0));
		assertThat("the list size should be equals to a distinct list size", list.getContent().size(), is((int) list.getContent().stream().distinct().count()));
	}

	@Test
	public void testFindPageableByTagNameFromTagEntity() throws Exception {
		final Pageable pageableSortByDateDesc = new PageRequest(0, 10, new Sort(Direction.DESC, "creationDate", "name"));
		Page<ProductEntity> page0 = repository.findAllByTagNameFromTagEntity("featured", pageableSortByDateDesc);
		assertThat(page0.getContent().size(), is(10));
		assertThat("the list size should be equals to a distinct list size", page0.getContent().size(), is((int) page0.getContent().stream().distinct().count()));

		assertThat(page0.getContent().get(0).getName(), is("PRODUCT 18 PRODUCT  7"));
		assertThat(page0.getContent().get(0).getNumberOfDownloads(), is(54125L));
		assertThat(page0.getContent().get(0).getCreationDate().toString(), is("2017-02-25 18:16:34.372"));

		assertThat(page0.getContent().get(9).getName(), is("product  4"));
		assertThat(page0.getContent().get(9).getNumberOfDownloads(), is(1125L));
		assertThat(page0.getContent().get(9).getCreationDate().toString(), is("2017-02-12 16:34:34.372"));

		final Pageable pageableSortByDateAsc = new PageRequest(0, 10, new Sort(Direction.ASC, "creationDate", "name"));
		Page<ProductEntity> page1 = repository.findAllByTagNameFromTagEntity("featured", pageableSortByDateAsc);
		assertThat("the list size should be equals to a distinct list size", page1.getContent().size(), is((int) page1.getContent().stream().distinct().count()));

		assertThat(page1.getContent().size(), is(10));
		assertThat(page1.getContent().get(0).getName(), is("product  8"));
		assertThat(page1.getContent().get(0).getNumberOfDownloads(), is(99125L));
		assertThat(page1.getContent().get(0).getCreationDate().toString(), is("2014-02-13 19:15:34.372"));

		assertThat(page1.getContent().get(9).getName(), is("product  8"));
		assertThat(page1.getContent().get(9).getNumberOfDownloads(), is(9125L));
		assertThat(page1.getContent().get(9).getCreationDate().toString(), is("2017-02-13 19:15:34.372"));

		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

		final Pageable pageableSortByDownloadsAsc = new PageRequest(0, 10, new Sort(Direction.ASC, "numberOfDownloads", "name"));
		Page<ProductEntity> page2 = repository.findAllByTagNameFromTagEntity("promoted", pageableSortByDownloadsAsc);
		assertThat("the list size should be equals to a distinct list size", page2.getContent().size(), is((int) page2.getContent().stream().distinct().count()));

		assertThat(page2.getContent().size(), is(10));
		assertThat(page2.getContent().get(0).getName(), is("PRODUCT 16 PRODUCT  5"));
		assertThat(page2.getContent().get(0).getNumberOfDownloads(), is(5L));
		assertThat(page2.getContent().get(0).getCreationDate().toString(), is("2017-02-11 17:45:34.372"));

		assertThat(page2.getContent().get(9).getName(), is("PRODUCT 52 PRODUCT  2"));
		assertThat(page2.getContent().get(9).getNumberOfDownloads(), is(25L));
		assertThat(page2.getContent().get(9).getCreationDate().toString(), is("2015-02-13 23:22:34.372"));

		final Pageable pageableSortByDownloadsDesc = new PageRequest(0, 10, new Sort(Direction.DESC, "numberOfDownloads", "name"));
		Page<ProductEntity> page3 = repository.findAllByTagNameFromTagEntity("promoted", pageableSortByDownloadsDesc);
		assertThat("the list size should be equals to a distinct list size", page3.getContent().size(), is((int) page3.getContent().stream().distinct().count()));

		assertThat(page3.getContent().size(), is(10));
		assertThat(page3.getContent().get(0).getName(), is("PRODUCT 31 PRODUCT  8"));
		assertThat(page3.getContent().get(0).getNumberOfDownloads(), is(99123L));
		assertThat(page3.getContent().get(0).getCreationDate().toString(), is("2014-02-13 19:15:34.372"));

		assertThat(page3.getContent().get(9).getName(), is("product 73 product  9"));
		assertThat(page3.getContent().get(9).getNumberOfDownloads(), is(8025L));
		assertThat(page3.getContent().get(9).getCreationDate().toString(), is("2012-02-13 21:12:34.372"));
	}

	@Test
	public void testFindPageableByTagNameFromTagEntityLimitByKeywordsFromKeywordEntity() throws Exception {
		final String jee = "jee";
		final String jpa = "jpa";
		final String spring = "spring";
		final String hibernate = "hibernate";

		final List<String> filterToList = new ArrayList<>();

		final Pageable pageableSortByDateDesc = new PageRequest(0, 10000, new Sort(Direction.DESC, "creationDate", "name"));
		final Pageable pageableSortByDownloadsAsc = new PageRequest(0, 100000, new Sort(Direction.ASC, "numberOfDownloads", "name"));

		Page<ProductEntity> emptyFilter1 = repository.findAllByTagNameFromTagEntityLimitByKeywords("featured", new ArrayList<>(), pageableSortByDateDesc);
		assertThat(emptyFilter1.getContent().size(), is(0));

		Page<ProductEntity> emptyFilter2 = repository.findAllByTagNameFromTagEntityLimitByKeywords("featured", new ArrayList<>(), pageableSortByDateDesc);
		assertThat(emptyFilter2.getContent().size(), is(0));

		filterToList.add(jee);
		Page<ProductEntity> page0 = repository.findAllByTagNameFromTagEntityLimitByKeywords("featured", filterToList, pageableSortByDateDesc);
		assertThat(page0.getContent().size(), is(4));
		assertThat("the list size should be equals to a distinct list size", page0.getContent().size(), is((int) page0.getContent().stream().distinct().count()));

		filterToList.add(jpa);
		Page<ProductEntity> page1 = repository.findAllByTagNameFromTagEntityLimitByKeywords("featured", filterToList, pageableSortByDateDesc);
		assertThat(page1.getContent().size(), is(6));
		assertThat("the list size should be equals to a distinct list size", page1.getContent().size(), is((int) page1.getContent().stream().distinct().count()));

		filterToList.clear();
		filterToList.add(spring);
		filterToList.add(hibernate);
		Page<ProductEntity> page2 = repository.findAllByTagNameFromTagEntityLimitByKeywords("promoted", filterToList, pageableSortByDownloadsAsc);
		assertThat(page2.getContent().size(), is(7));
		assertThat("the list size should be equals to a distinct list size", page2.getContent().size(), is((int) page2.getContent().stream().distinct().count()));

		filterToList.add(jee);
		filterToList.add(jpa);
		Page<ProductEntity> page3 = repository.findAllByTagNameFromTagEntityLimitByKeywords("promoted", filterToList, pageableSortByDownloadsAsc);
		assertThat(page3.getContent().size(), is(9));
		assertThat("the list size should be equals to a distinct list size", page3.getContent().size(), is((int) page3.getContent().stream().distinct().count()));
	}

	/**
	 * @throws Exception {@link Exception}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Test
	public void testFindByIdGetAllKeywords() throws Exception {
		final ProductEntity pe = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));

		assertThat(pe.getKeywordEntityList().size(), is(4));

		assertThat(pe.getKeywordEntityList().get(0).getName(), is("Spring Framework"));
		assertThat(pe.getKeywordEntityList().get(0).getAlias(), is("spring"));

		assertThat(pe.getKeywordEntityList().get(1).getName(), is("Hibernate ORM"));
		assertThat(pe.getKeywordEntityList().get(1).getAlias(), is("hibernate"));

		assertThat(pe.getKeywordEntityList().get(2).getName(), is("Java Enterprise Edition"));
		assertThat(pe.getKeywordEntityList().get(2).getAlias(), is("jee"));

		assertThat(pe.getKeywordEntityList().get(3).getName(), is("Java Persistence API"));
		assertThat(pe.getKeywordEntityList().get(3).getAlias(), is("jpa"));

	}

	/**
	 * select and check if  {@link Sort} options of {@link Pageable} still apply
	 *
	 * @throws Exception {@link Exception}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Test
	public void testFindAllByKeywordEntityNameIn() throws Exception {
		final Sort sortByNumberOfDownloadsDesc = new Sort(Direction.DESC, "numberOfDownloads");
		final Sort sortByCreationDateAsc = new Sort(Direction.ASC, "creationDate");

		final String jpaKeyword = "jpa";
		final String jeeKeyword = "jee";
		final String springKeyword = "spring";
		final String hibernateKeyword = "Hibernate ORM";
		final List<String> keywordList = new ArrayList<>();

		final Page<ProductEntity> emptyPage = repository.findAllByKeywordEntityNameIn(null, null);
		assertThat("the list size should be equals to a distinct list size", emptyPage.getContent().size(), is((int) emptyPage.getContent().stream().distinct().count()));
		assertThat(emptyPage.getTotalPages(), is(1));
		assertThat(emptyPage.getSize(), is(0));
		assertThat(emptyPage.getContent().size(), is(0));

		keywordList.add(jpaKeyword);
		keywordList.add(springKeyword);
		final Page<ProductEntity> page1 = repository.findAllByKeywordEntityNameIn(keywordList, new PageRequest(0, 10000, sortByNumberOfDownloadsDesc));
		assertThat("the list size should be equals to a distinct list size", page1.getContent().size(), is((int) page1.getContent().stream().distinct().count()));
		assertThat(page1.getTotalPages(), is(1));
		assertThat(page1.getSize(), is(10000));
		assertThat(page1.getContent().size(), is(7));
		assertThat(page1.getContent().get(0).getNumberOfDownloads(), is(99125L));
		assertThat(page1.getContent().get(0).getCreationDate().toString(), is("2014-02-13 19:15:34.372"));
		assertThat(page1.getContent().get(0).getName(), is("product  8"));
		assertThat(page1.getContent().get(6).getNumberOfDownloads(), is(5L));
		assertThat(page1.getContent().get(6).getCreationDate().toString(), is("2017-02-11 17:45:34.372"));
		assertThat(page1.getContent().get(6).getName(), is("product  5"));

		keywordList.clear();
		keywordList.add(jeeKeyword);
		keywordList.add(springKeyword);
		keywordList.add(hibernateKeyword);
		final Page<ProductEntity> page2 = repository.findAllByKeywordEntityNameIn(keywordList, new PageRequest(0, 5, sortByCreationDateAsc));
		assertThat("the list size should be equals to a distinct list size", page2.getContent().size(), is((int) page2.getContent().stream().distinct().count()));
		assertThat(page2.getTotalPages(), is(2));
		assertThat(page2.getSize(), is(5));
		assertThat(page2.getContent().size(), is(5));
		assertThat(page2.getContent().get(0).getNumberOfDownloads(), is(8025L));
		assertThat(page2.getContent().get(0).getCreationDate().toString(), is("2012-02-13 21:12:34.372"));
		assertThat(page2.getContent().get(0).getName(), is("product  9"));
		assertThat(page2.getContent().get(4).getNumberOfDownloads(), is(125L));
		assertThat(page2.getContent().get(4).getCreationDate().toString(), is("2016-02-13 03:21:34.372"));
		assertThat(page2.getContent().get(4).getName(), is("product  1"));
	}

	@Test
	public void testFindAllUuidByKeywords() throws Exception {
		final String jpaKeyword = "jpa";
		final String springKeyword = "spring";
		final List<String> keywordList = new ArrayList<>();

		final List<UUID> emptyList = repository.findAllUuidByKeywords(null);
		assertThat(emptyList.size(), is(0));

		keywordList.add(jpaKeyword);
		keywordList.add(springKeyword);
		final List<UUID> list = repository.findAllUuidByKeywords(keywordList);
		assertThat(list.size(), is(8));
	}

	@Test
	public void testSave() throws Exception {
		assertThat(((List) repository.findAll()).size(), is(47));

		final ProductEntity loaded = repository.findOne(UUID.fromString("8650caf1-f023-4808-95f7-322af55fb163"));
		final ProductEntity p = new ProductEntity();
		p.setName("p_1");
		p.setDescription("p_1 description");
		p.setNumberOfDownloads(0L);
		p.setUserEntity(loaded.getUserEntity());

		repository.save(p);
		assertThat(((List) repository.findAll()).size(), is(48));

	}

}