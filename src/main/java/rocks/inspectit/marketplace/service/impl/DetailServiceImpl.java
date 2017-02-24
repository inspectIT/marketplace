package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.service.DetailService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
@Service
public class DetailServiceImpl implements DetailService {

	private final ProductService service;

	/**
	 * constructor injection.
	 *
	 * @param service {@link ProductService}
	 */
	@Autowired
	public DetailServiceImpl(final ProductService service) {
		this.service = service;
	}

	/**
	 * ## todo : describe
	 *
	 * @param productId {@link UUID}
	 * @return {@link ProductEntity}
	 */
	@Override
	public ProductEntity getProductEntityById(final UUID productId) {
		return this.service.getProductByProductUuid(productId);
	}
}
