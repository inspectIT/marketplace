package rocks.inspectit.marketplace.dao.repository.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID userUuid;

	/**
	 * username is unique.
	 */
	@NotEmpty
	@Column(unique = true)
	private String name;

	/**
	 * email is unique.
	 */
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotEmpty
	private String ip;

	private String avatarUrl;
	private String company;
	private String location;

	/**
	 * new date to today on insert.
	 * {@link NotNull} makes sure, that you cannot save Entity with empty value,
	 * but since we don't want to update creationDate,
	 * there might be a {@link org.springframework.dao.DataIntegrityViolationException}
	 * therefore we have to leave it out, for now
	 */
	@NotNull
	@CreatedDate
	@Column(name = "CREATION_DATE", nullable = false, updatable = false)
	private Date creationDate = new Date();

	/**
	 * new date to today on modify.
	 * {@link NotNull} makes sure, that you cannot update Entity with empty value,
	 * but since we don't want to insert modifyDate,
	 * there will be a {@link org.springframework.dao.DataIntegrityViolationException},
	 * therefore we have to leave it out, for now
	 */
	//	@NotNull
	@LastModifiedDate
	@Column(name = "LAST_LOGIN_DATE", insertable = false)
	private Date lastLoginDate = new Date();

	/**
	 * keep track of changes and updates.
	 */
	@Version
	private Integer version;

	@NotNull
	private boolean active = true;

	/**
	 * one user can have one or many products.
	 */
	@OneToMany(mappedBy = "userEntity", targetEntity = ProductEntity.class)
	private List<ProductEntity> productEntityList;

	/**
	 * one user can have one or many products.
	 */
	@OneToMany(mappedBy = "userEntity", targetEntity = RatingEntity.class)
	private List<RatingEntity> ratingEntityList;

	/**
	 * one user has one role.
	 */
	@OneToOne
	@JoinColumn(name = "roleUuid", referencedColumnName = "roleUuid")
	private RoleEntity roleEntity;

	public UUID getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(UUID userUuid) {
		this.userUuid = userUuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ProductEntity> getProductEntityList() {
		return productEntityList;
	}

	public void setProductEntityList(List<ProductEntity> productEntityList) {
		this.productEntityList = productEntityList;
	}

	public List<RatingEntity> getRatingEntityList() {
		return ratingEntityList;
	}

	public void setRatingEntityList(List<RatingEntity> ratingEntityList) {
		this.ratingEntityList = ratingEntityList;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
}
