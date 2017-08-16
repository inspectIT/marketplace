package inspectit.rocks.marketplace.configuration.service.dao.jpa.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inspectit.rocks.marketplace.configuration.service.dao.jpa.mongo.document.Configuration;

/**
 * REST repository for CRUD operation for {@link Configuration}.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@RepositoryRestResource(path = "configurations")
public interface ConfigurationRepository extends MongoRepository<Configuration, ObjectId> {

}
