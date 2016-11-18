package rocks.inspectit.marketplace.mvc.angular.model;

import java.time.LocalDateTime;

/**
 * Displaying the filtered <i>inspectIT Configuration</i> to the dashboard.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public class DashBoardModel {
	private String name;
	private String description;
	private String creationUser;

	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;

	private Double rating;

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

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
