package rocks.inspectit.marketplace.service;

import java.util.List;

import rocks.inspectit.marketplace.service.dto.GitHubEmailDto;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface GitHubRestService {
	List<GitHubEmailDto> getUserEmailFromGithubByToken(String tokenValue);
}
