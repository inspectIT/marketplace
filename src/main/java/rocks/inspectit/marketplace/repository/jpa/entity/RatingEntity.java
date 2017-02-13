package rocks.inspectit.marketplace.repository.jpa.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Entity
public class RatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID ratingUuid;

	@Version
	private Integer version;

	@NotEmpty
	@Length(max = 2000)
	private String ratingDescription;

	@NotNull
	@Min(1)
	@Max(10)
	private Integer ratingAsNumber;

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

	@NotNull
	private Boolean active;

	/**
	 * user relationship.
	 * user is parent
	 * one user can create one or many products
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userUuid", referencedColumnName = "userUuid")
	private UserEntity userEntity;

	/**
	 * product relationship.
	 * product is parent
	 * one product can have one or many rating
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productUuid", referencedColumnName = "productUuid")
	private ProductEntity productEntity;

	public UUID getRatingUuid() {
		return ratingUuid;
	}

	public void setRatingUuid(UUID ratingUuid) {
		this.ratingUuid = ratingUuid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRatingDescription() {
		return ratingDescription;
	}

	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}

	public Integer getRatingAsNumber() {
		return ratingAsNumber;
	}

	public void setRatingAsNumber(Integer ratingAsNumber) {
		this.ratingAsNumber = ratingAsNumber;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
}
