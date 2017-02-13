package rocks.inspectit.marketplace.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Config Swagger for API documentation.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private static final String BASE_PACKAGE_API = "rocks.inspectit.marketplace.mvc.api";
	private static final String BASE_PACKAGE_APP = "rocks.inspectit.marketplace.mvc.app";

	/**
	 * Build a Docket, which select all accessible Request.
	 * Access API information on localhost under <a href="http://localhost:8080/v2/api-docs">Swagger API Docs</a>
	 *
	 * @return Docket of {@link Docket}
	 */
	@Bean
	public Docket all() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * Build a Docket, which select all accessible Request under package "**\/app".
	 * Access API information on localhost under <a href="http://localhost:8080/v2/api-docs?group=APP">Swagger API Docs</a>
	 *
	 * @return Docket of {@link Docket}
	 */
	@Bean
	public Docket app() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("APP")
				.select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_APP))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * Build a Docket, which selects all accessible Request under package "**\/api".
	 * Access API information on localhost under <a href="http://localhost:8080/v2/api-docs?group=API">Swagger API Docs</a>
	 *
	 * @return Docket of {@link Docket}
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API")
				.select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_API))
				.paths(PathSelectors.any())
				.build();
	}
}
