package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.service.SearchService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Service
public class SearchServiceImpl implements SearchService {

	private final ProductService service;

	/**
	 * use constructor injection.
	 *
	 * @param service {@link ProductService}
	 */
	@Autowired
	public SearchServiceImpl(final ProductService service) {
		this.service = service;
	}

	/**
	 * ## TODO.
	 *
	 * @param searchTerm {@link String}
	 * @param pageable   {@link Pageable}
	 * @return {@link List} of {@link ProductEntity}
	 */
	@Override
	public Page<ProductEntity> getAllProductEntitiesBySearchTerm(final String searchTerm, final Pageable pageable) {
		return this.service.getAllProductsBySearchTerm(searchTerm, pageable);
	}
}
