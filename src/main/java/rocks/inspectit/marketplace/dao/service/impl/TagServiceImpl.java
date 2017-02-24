package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.TagEntityRepository;
import rocks.inspectit.marketplace.dao.service.TagService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
@Service
public class TagServiceImpl implements TagService {

	private final TagEntityRepository repository;

	/**
	 * Constructor injection.
	 *
	 * @param repository {@link TagEntityRepository}
	 */
	@Autowired
	public TagServiceImpl(final TagEntityRepository repository) {
		this.repository = repository;
	}

	/**
	 * ## todo describe.
	 *
	 * @param tagName as {@link String}
	 * @return {@link List} of {@link UUID}
	 */
	@Transactional(readOnly = true)
	@Override
	public List<UUID> getAllProductUuidByTagName(final String tagName) {
		return this.repository.findAllByTagName(tagName);
	}
}
