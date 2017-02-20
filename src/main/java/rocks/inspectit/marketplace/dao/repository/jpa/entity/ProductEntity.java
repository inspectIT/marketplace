package rocks.inspectit.marketplace.dao.repository.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Thanks to fancy spring boot we don't need a persistence.xml.
 * <p/>
 * this is not the final implementation:
 * TODO: make sure, to upload images or other media types, especially JSON/XML files
 * <p/>
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID productUuid;

	/**
	 * keep track of changes and updates.
	 */
	@Version
	private Integer version;

	@NotNull
	private String name;

	@NotNull
	@Length(max = 2000)
	private String description;

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
	private Boolean active = true;

	private Long numberOfDownloads;

	private Blob previewImage;
	private Clob productItem;

	/**
	 * user relationship.
	 * user is parent
	 * one user can create one or many products
	 */
	@ManyToOne
	@JoinColumn(name = "userUuid", referencedColumnName = "userUuid")
	private UserEntity userEntity;

	/**
	 * rating relationship.
	 * product is parent
	 * one product can have one or many ratings
	 */
	@OneToMany(mappedBy = "productEntity", targetEntity = RatingEntity.class)
	private List<RatingEntity> ratingEntityList;

	/**
	 * tag relationship.
	 * product is parent
	 * one product has one tag
	 */
	@OneToOne
	@JoinColumn(name = "tagUuid", referencedColumnName = "tagUuid")
	private TagEntity tagEntity;

	/**
	 * keyword relationship.
	 *
	 * @since 1.0.7-SNAPSHOT
	 */
	@ManyToMany
	@JoinTable(name = "keyword_product",
			joinColumns = @JoinColumn(name = "productUuid", referencedColumnName = "productUuid"),
			inverseJoinColumns = @JoinColumn(name = "keywordUuid", referencedColumnName = "keywordUuid"))
	private List<KeywordEntity> keywordEntityList;

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

	public Optional<Blob> getPreviewImage() {
		return Optional.ofNullable(previewImage);
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

	public List<RatingEntity> getRatingEntityList() {
		return ratingEntityList;
	}

	public void setRatingEntityList(List<RatingEntity> ratingEntityList) {
		this.ratingEntityList = ratingEntityList;
	}

	public TagEntity getTagEntity() {
		return tagEntity;
	}

	public void setTagEntity(TagEntity tagEntity) {
		this.tagEntity = tagEntity;
	}

	public List<KeywordEntity> getKeywordEntityList() {
		return keywordEntityList;
	}

	public void setKeywordEntityList(List<KeywordEntity> keywordEntityList) {
		this.keywordEntityList = keywordEntityList;
	}

	/**
	 * ## todo describe.
	 *
	 * @return
	 */
	public Optional<Double> getTotalRating() {
		if (this.ratingEntityList.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(this.ratingEntityList.stream().mapToDouble(RatingEntity::getRatingAsNumber).sum() / this.ratingEntityList.size());
	}
}
