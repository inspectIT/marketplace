package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.RatingEntityRepository;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.service.RatingService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
@Service
public class RatingServiceImpl implements RatingService {

	private final RatingEntityRepository repository;

	@Autowired
	public RatingServiceImpl(final RatingEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean ratingExistForUserAndProduct(final String userName, final UUID productId) {
		return this.repository.findAllByUserEntityNameAndProductEntityProductUuid(userName, productId).size() > 0;
	}

	@Override
	public RatingEntity persistRatingEntity(final RatingEntity entity) {
		return repository.save(entity);
	}
}
