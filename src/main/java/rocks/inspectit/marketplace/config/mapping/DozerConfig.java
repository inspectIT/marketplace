package rocks.inspectit.marketplace.config.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;
import rocks.inspectit.marketplace.mvc.app.model.ProductDetailModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingItemModel;
import rocks.inspectit.marketplace.mvc.app.model.UserDetailModel;

/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Configuration
public class DozerConfig {

	/**
	 * Custom Mapper to Map from Domain-Object to DTO and from DTO to Entity-Object.
	 * Right now, it's only not possible to map from List&lt;T&gt; to List&lt;U&gt;, therefore you have
	 * to iterate through your list and map it in every iteration.
	 * Also we're not using DTO's, yet
	 *
	 * @param builder custom builder information
	 * @return Dozer Bean Mapper
	 */
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean(final BeanMappingBuilder builder) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(builder);
		return mapper;
	}

	@Bean
	public BeanMappingBuilder getBeanMappingBuilder() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapProductEntityToOverviewItemModel();
				mapProductEntityToDetailItemModel();
				mapRatingEntityToRatingItemModel();
				mapUserEntityToUserDetailModel();
				mapProductEntityToProductDetailModel();
			}

			/**
			 * ## todo: describe
			 */
			private void mapProductEntityToOverviewItemModel() {
				mapping(ProductEntity.class, OverviewItemModel.class)
						.fields("productUuid", "id", FieldsMappingOptions.copyByReference())
						.fields("name", "name")
						.fields("UserEntity.name", "author")
						.fields("numberOfDownloads", "numberDownloads")
						.fields("creationDate", "creationDate")
						.exclude("previewImage")
						.exclude("ratingsEntityList");
			}

			/**
			 * ## todo: describe
			 */
			private void mapProductEntityToDetailItemModel() {
				mapping(ProductEntity.class, DetailModel.class)
						.fields("productUuid", "productId", FieldsMappingOptions.copyByReference())
						.fields("name", "productName")
						.fields("description", "productDescription")
						// .fields("previewImage", "productPreviewImage") // exclude because we are mapping blob to string manually
						.fields("creationDate", "productCreationDate")
						.fields("numberOfDownloads", "numberOfDownloads")

						.fields("userEntity.userUuid", "userId", FieldsMappingOptions.copyByReference())
						.fields("UserEntity.name", "userName")
						.exclude("previewImage")
						.exclude("ratingsEntityList");
			}

			/**
			 * ## todo: describe
			 */
			private void mapRatingEntityToRatingItemModel() {
				mapping(RatingEntity.class, RatingItemModel.class)
						.fields("ratingUuid", "ratingId", FieldsMappingOptions.copyByReference())
						.fields("ratingDescription", "ratingDescription")
						.fields("ratingAsNumber", "rating")
						.fields("creationDate", "creationDate")
						.fields("active", "active")

						.fields("UserEntity.userUuid", "userId", FieldsMappingOptions.copyByReference())
						.fields("UserEntity.name", "userName")
						.fields("UserEntity.avatarUrl", "userAvatarUrl");
			}

			/**
			 * ## todo: describe
			 */
			private void mapUserEntityToUserDetailModel() {
				mapping(UserEntity.class, UserDetailModel.class)
						//.fields("userUuid", "id", FieldsMappingOptions.copyByReference())
						.fields("name", "userName")
						.fields("avatarUrl", "avatarUrl")
						.fields("email", "userEmail")
						.fields("company", "userCompany")
						.fields("location", "userLocation")
						.fields("ip", "ip")
						.fields("RoleEntity.role", "role")
						.fields("lastLoginDate", "loginDate");
			}

			/**
			 * ## todo : describe.
			 */
			private void mapProductEntityToProductDetailModel() {
				mapping(ProductEntity.class, ProductDetailModel.class)
						.fields("productUuid", "productId", FieldsMappingOptions.copyByReference())
						.fields("name", "productName")
						.fields("description", "productDescription")
						.fields("numberOfDownloads", "numberOfDownloads")

						.exclude("previewImage") // exclude and map blob to string manually
						.exclude("ratingsEntityList"); // exclude and map manually

			}

		};
	}
}