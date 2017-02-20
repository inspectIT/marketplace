package rocks.inspectit.marketplace.dao.repository.jpa.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
@Entity
public class KeywordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID keywordUuid;

	/**
	 * keep track of changes and updates.
	 */
	@Version
	private Integer version;

	@NotNull
	private String name;

	@NotNull
	private String alias;

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
	 * {@link CreatedBy} annotation identifies the creator from {@link java.security.Principal}.
	 */
	@NotNull
	@CreatedBy
	private String creationUser;

	@NotNull
	private Boolean active = true;

	@ManyToMany(mappedBy = "keywordEntityList")
	private List<ProductEntity> productEntityList = new ArrayList<>();


	public UUID getKeywordUuid() {
		return keywordUuid;
	}

	public void setKeywordUuid(UUID keywordUuid) {
		this.keywordUuid = keywordUuid;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ProductEntity> getProductEntityList() {
		return productEntityList;
	}

	public void setProductEntityList(List<ProductEntity> productEntityList) {
		this.productEntityList = productEntityList;
	}
}
