package inspectit.rocks.marketplace.configuration.service.migration.mongobee;

import com.github.mongobee.changeset.ChangeLog;

/**
 * Database changelogs.
 * document schema change and execute on migration.
 * <p>
 * keep in mind that schema changes also may change {@link inspectit.rocks.marketplace.configuration.service.dao.jpa.mongo.document.Configuration} attributes.
 * using {@link org.springframework.data.mongodb.core.MongoTemplate} in combination with {@link inspectit.rocks.marketplace.configuration.service.dao.jpa.mongo.document.Configuration} may cause errors, because attributes can be replaced, deleted or renamed. It may be better to write update/change/queries without relying on {@link inspectit.rocks.marketplace.configuration.service.dao.jpa.mongo.document.Configuration}.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@ChangeLog
public class DatabaseChangeLog {

	/**
	 * no changes yet
	 */
}
