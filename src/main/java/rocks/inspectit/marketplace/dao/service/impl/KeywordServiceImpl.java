package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.KeywordEntityRepository;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;
import rocks.inspectit.marketplace.dao.service.KeywordService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
@Service
public class KeywordServiceImpl implements KeywordService {

	private final KeywordEntityRepository repository;

	@Autowired
	public KeywordServiceImpl(final KeywordEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<KeywordEntity> getKeywordEntityListByAliasList(final List<String> aliasList) {
		return this.repository.findByAliasIn(aliasList);
	}
}
