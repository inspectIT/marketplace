package inspectit.rocks.marketplace.rating.service.dao.jpa.mongo.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a MongoDB Document.
 * Using Lombok to reduce boilerplate.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@Document
public class Rating {

	@Id
	@Field
	private ObjectId id;

	@Indexed
	@Field
	private String email;

	@Field
	private String userName;

	@Field
	private Integer rating;

	@Field
	private String description;

	/**
	 * enable auditing
	 */
	@CreatedDate
	private LocalDate createdDate;

	@LastModifiedDate
	private LocalDate lastModifiedDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;

	/**
	 * optimistic locking.
	 */
	@Version
	@Field
	private int version;
}
