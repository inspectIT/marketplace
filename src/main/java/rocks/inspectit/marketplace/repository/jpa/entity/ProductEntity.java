package rocks.inspectit.marketplace.repository.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Blob;
import java.sql.Clob;
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
import javax.validation.constraints.NotNull;

/**
 * Thanks to fancy spring boot we don't need a persistence.xml
 * <p/>
 * this is not the final implementation:
 * TODO: create relationship between UserEntity and RatingsEntity
 * TODO: make sure, to upload images or other media types, especially JSON/XML files
 * <p/>
 *
 * @author NKO
 * @version %I%, %G%
 * @OneToOne
 * @JoinColumn(name = "user_entity_uuid")
 * private UserEntity userEntity;
 * <p/>
 * <p/>
 * @since 1.0.4-SNAPSHOT
 */
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID productUuid;

	@Version
	private Integer version;

	@NotNull
	private String name;

	@NotNull
	@Length(max = 2000)
	private String description;

	/**
	 * new date to today on insert
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
	 * new date to today on modify;
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
	private Boolean active = true;

	private Long numberOfDownloads;

	private Blob previewImage;
	private Clob productItem;

	/**
	 * user relationship ...
	 * one user can have one or many products
	 */
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "userUuid", referencedColumnName = "userUuid")
	private UserEntity userEntity;

	/**
	 * todo: create tag entity to choose from ...
	 * use simple string for 'TAG's to distinct the items
	 *
	 * NOTE: please use only one of the following
	 * 'rated' - for the highest rated products
	 * 'promoted' - for the products promoted by someone
	 *
	 */
	@NotNull
	private String tag;
	@NotNull
	private Double totalRating;

	public UUID getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(UUID productUuid) {
		this.productUuid = productUuid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(Long numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	public Blob getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(Blob previewImage) {
		this.previewImage = previewImage;
	}

	public Clob getProductItem() {
		return productItem;
	}

	public void setProductItem(Clob productItem) {
		this.productItem = productItem;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}
}
