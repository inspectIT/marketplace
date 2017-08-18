package inspectit.rocks.marketplace.rating.service.config.database;

import com.github.mongobee.Mongobee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple MongoBee configuration class.
 * set Database URI, Database name and path to ChangeLog.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@Configuration
public class MongoBeeConfig {

	private final String databaseUri;
	private final String databaseName;
	private final String basePath;
	private final Boolean isEnabled;

	public MongoBeeConfig(@Value("${spring.data.mongodb.uri}") String databaseUri,
			@Value("${spring.data.mongodb.database}") String databaseName,
			@Value("${application.property.mongobee.changelog.path}") String basePath,
			@Value("${application.property.mongobee.changelog.enabled}") Boolean isEnabled) {
		this.databaseUri = databaseUri;
		this.databaseName = databaseName;
		this.basePath = basePath;
		this.isEnabled = isEnabled;
	}

	@Bean
	public Mongobee getMongoBee() {
		final Mongobee runner = new Mongobee(databaseUri);
		runner.setDbName(databaseName);                    // host must be set if not set in URI
		runner.setChangeLogsScanPackage(basePath);         // package to scan for changesets
		runner.setEnabled(isEnabled);                      // optional: default is true

		return runner;
	}
}
