package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import rocks.inspectit.marketplace.service.GitHubRestService;
import rocks.inspectit.marketplace.service.dto.GitHubEmailDto;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
@Service
public class GitHubRestServiceImpl implements GitHubRestService {

	private final String userEmailUri;
	private final RestTemplate restTemplate;

	/**
	 * Use constructor injection.
	 *
	 * @param userEmailUri {@link String}
	 * @param builder      {@link RestTemplateBuilder} - injects fully configured RestTemplate
	 */
	@Autowired
	public GitHubRestServiceImpl(@Value("${security.oauth2.resource.user-email.uri}") final String userEmailUri, final RestTemplateBuilder builder) {
		this.userEmailUri = userEmailUri;
		this.restTemplate = builder.build();
	}

	/**
	 * ## todo : describe.
	 *
	 * @param tokenValue {@link String}
	 * @return {@link List} of {@link GitHubEmailDto}
	 */
	@Override
	public List<GitHubEmailDto> getUserEmailFromGithubByToken(final String tokenValue) {
		final GitHubEmailDto[] responseEntity = restTemplate.getForObject(String.format("%s?access_token=%s", userEmailUri, tokenValue), GitHubEmailDto[].class);
		return Arrays.asList(responseEntity);
	}
}
