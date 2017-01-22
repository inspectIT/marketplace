package rocks.inspectit.marketplace.config.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;

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
				mapProductEntityToDashboardModel();
			}

			private void mapProductEntityToDashboardModel() {
				mapping(ProductEntity.class, DashBoardModel.class)
						.fields("productUuid", "id", FieldsMappingOptions.copyByReference())
						.fields("name", "name")
						.fields("UserEntity.name", "author")
						.fields("numberOfDownloads", "numberDownloads")
						.fields("creationDate", "creationDate")
						.exclude("previewImage")
						.exclude("ratingsEntityList");
			}
		};
	}
}