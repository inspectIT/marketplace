package rocks.inspectit.marketplace.dao.repository.jpa.entity.helper;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * Helper class for custom queries.
 * Instead of Object[] array, it's possible to create a Custom Object.
 * Create additional constructor if needed.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public class CustomQueryDTO {

	private ProductEntity productEntity;
	private Long sum;

	/**
	 * Constructor for ProductEntity with sum.
	 *
	 * @param productEntity as {@link ProductEntity}
	 * @param sum           as {@link Long}
	 */
	public CustomQueryDTO(final ProductEntity productEntity, final Long sum) {
		this.productEntity = productEntity;
		this.sum = sum;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}
}
