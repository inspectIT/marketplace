package rocks.inspectit.marketplace.mvc.app.model;

import java.util.Date;
import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public class RatingItemModel {

	private UUID ratingId;
	private UUID userId;

	private String userName;
	private String ratingDescription;

	private Integer rating;

	private Date creationDate;
	private Boolean active;


	public UUID getRatingId() {
		return ratingId;
	}

	public void setRatingId(UUID ratingId) {
		this.ratingId = ratingId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRatingDescription() {
		return ratingDescription;
	}

	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
