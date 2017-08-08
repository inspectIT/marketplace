package rocks.inspectit.marketplace.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import rocks.inspectit.marketplace.config.security.helper.ClientResources;

/**
 * Custom Security Configuration, to override default Spring-Security behavior.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@EnableOAuth2Client
@EnableAuthorizationServer
@Configuration
@Profile("!locale")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * use to create a custom authentication filter, which will be added to the security configuration.
	 */
	private final OAuth2ClientContext oauth2ClientContext;

	/**
	 * Constructor injection.
	 *
	 * @param oauth2ClientContext {@link OAuth2ClientContext}
	 */
	@Autowired
	public WebSecurityConfig(final OAuth2ClientContext oauth2ClientContext) {
		this.oauth2ClientContext = oauth2ClientContext;
	}

	/**
	 * Security Config, to allow following requests without authorization.
	 * <ul>
	 * <li>show index.html Landing page</li>
	 * <li>allow loading of compiled JS and CSS</li>
	 * <li>allow loading of files in assets folder, e.g. BootsTrap CSS and BootsTrap or jQuery JS</li>
	 * <li>API requests</li>
	 * </ul>
	 *
	 * @param http {@link HttpSecurity}
	 * @throws Exception {@link Exception} if something goes wrong
	 * @since 1.1.1-SNAPSHOT
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers(
						"/", // allow request to root
						"/login**", // allow login request
						"/app/get/**",  // allow default "get" requests
						"/app/update/product/**/download", // allow updates to product, if it gets downloaded
						"/app/download/product/**", // allow product downloads
						"/index.html", "/**.js", "/**.css", "/**.woff", "/**.woff2", "/**.ttf", "/assets/**", // static resources
						"/api**").permitAll()
				.anyRequest().authenticated()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers("/nocsrf", "/console/**")
				.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
				.and().headers().frameOptions().disable()

				/*
				 * limit access to amazonaws domain
				 */
				//				.addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM amazonaws.com"))
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	/**
	 * Use {@link ConfigurationProperties} Annotation to inject configuration with prefix <i>github.client</i>, <i>github.resource</i>.
	 * <p/>
	 * It's not necessary to keep this <b>data object</b> within this configuration class.
	 * If you want to reuse this Bean, you can make this Bean public and inject it with the {@link Autowired} annotation.
	 *
	 * @return {@link ClientResources}
	 * @since 1.1.0-SNAPSHOT
	 */
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
		return new ClientResources();
	}

	/**
	 * Explicitly support redirects to GitHub.
	 * <p/>
	 * Since we set the order to a very low value, we ensure that this filter will be ensured before the main Spring Security Filter.
	 *
	 * @param filter {@link OAuth2ClientContextFilter}
	 * @return FilterRegistrationBean {@link FilterRegistrationBean}
	 */
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(final OAuth2ClientContextFilter filter) {
		final FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	/**
	 * Create custom SSO Filter for oauth providers, like GitHub.
	 *
	 * @return Filter {@link Filter}
	 */
	private Filter ssoFilter() {
		final CompositeFilter filter = new CompositeFilter();
		final List<Filter> filters = new ArrayList<>();
		filters.add(customSSOFilter(github(), "/login/github"));
		filter.setFilters(filters);
		return filter;
	}

	/**
	 * ## todo : describe.
	 *
	 * @param client     {@link ClientResources}
	 * @param processUrl {@link String}
	 * @return {@link Filter}
	 */
	private Filter customSSOFilter(final ClientResources client, final String processUrl) {
		final OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(processUrl);

		final OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);

		final UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);

		return filter;
	}

}
