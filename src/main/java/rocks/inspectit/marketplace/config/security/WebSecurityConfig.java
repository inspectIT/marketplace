package rocks.inspectit.marketplace.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;

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
	 * </ul>
	 *
	 * @param http {@link HttpSecurity}
	 * @throws Exception {@link Exception}
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.antMatcher("/**").addFilterBefore(customSSOFilter(), BasicAuthenticationFilter.class).authorizeRequests()
				.antMatchers("/**", "/login**", "/index.html", "/**.js", "/assets/**", "/api**").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
				.and()
				.logout().logoutSuccessUrl("/").permitAll()
				.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.headers().frameOptions().sameOrigin();
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
	 * Create custom SSO Filter for GitHub.
	 *
	 * @return Filter {@link Filter}
	 */
	private Filter customSSOFilter() {
		final OAuth2ClientAuthenticationProcessingFilter gitHubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
		gitHubFilter.setRestTemplate(new OAuth2RestTemplate(getGitHubResourceDetails(), oauth2ClientContext));
		gitHubFilter.setTokenServices(new UserInfoTokenServices(getGitHubResourceServerProperties().getUserInfoUri(), getGitHubResourceDetails().getClientId()));
		return gitHubFilter;
	}

	/**
	 * Use {@link ConfigurationProperties} Annotation to inject configuration with prefix <i>github.client</i>
	 * <p/>
	 * It's not necessary to keep this <b>data object</b> within this configuration class.
	 * If you want to reuse this Bean, you can make this Bean public and inject it with the {@link Autowired} annotation.
	 *
	 * @return AuthorizationCodeResourceDetails {@link AuthorizationCodeResourceDetails}
	 */
	@Bean
	@ConfigurationProperties("github.client")
	public AuthorizationCodeResourceDetails getGitHubResourceDetails() {
		return new AuthorizationCodeResourceDetails();
	}

	/**
	 * Use {@link ConfigurationProperties} Annotation to inject configuration with prefix <i>github.resource</i>.
	 *
	 * @return ResourceServerProperties {@link ResourceServerProperties}
	 */
	@Bean
	@ConfigurationProperties("github.resource")
	public ResourceServerProperties getGitHubResourceServerProperties() {
		return new ResourceServerProperties();
	}
}
