package inspectit.rocks.marketplace.rating.service.dao.jpa.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import inspectit.rocks.marketplace.rating.service.dao.jpa.mongo.document.Rating;

/**
 * REST repository for CRUD operation for {@link Rating}.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@RepositoryRestResource(path = "ratings")
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
}