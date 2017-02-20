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

	private String userName;
	private String ratingDescription;

	private Date creationDate;


	public UUID getRatingId() {
		return ratingId;
	}

	public void setRatingId(UUID ratingId) {
		this.ratingId = ratingId;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
