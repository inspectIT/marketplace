package rocks.inspectit.marketplace.dao.repository.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Use this entity for simple categorisation.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Entity
public class TagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID tagUuid;

	@NotEmpty
	private String tagName;

	/**
	 * keep track of changes and updates.
	 */
	@Version
	private Integer version;

	/**
	 * new date to today on insert.
	 * {@link NotNull} makes sure, that you cannot save Entity with empty value,
	 * but since we don't want to update creationDate,
	 * there might be a {@link org.springframework.dao.DataIntegrityViolationException}
	 * therefore we have to leave it out, for now
	 */
	@NotNull
	@CreatedDate
	@Column(name = "CREATION_DATE", updatable = false)
	private Date creationDate = new Date();

	/**
	 * new date to today on modify.
	 * {@link NotNull} makes sure, that you cannot update Entity with empty value,
	 * but since we don't want to insert modifyDate,
	 * there will be a {@link org.springframework.dao.DataIntegrityViolationException},
	 * therefore we have to leave it out, for now
	 */
	// @NotNull
	@LastModifiedDate
	@Column(name = "MODIFY_DATE", insertable = false)
	private Date modifyDate = new Date();

	public UUID getTagUuid() {
		return tagUuid;
	}

	public void setTagUuid(UUID tagUuid) {
		this.tagUuid = tagUuid;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
