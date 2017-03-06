package rocks.inspectit.marketplace.mvc.app.model;

import java.util.Date;
import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
public class RatingDetailModel {

	private UUID productId;
	private String productName;

	private UUID ratingId;
	private String ratingDescription;
	private Integer rating;
	private Date ratingCreationDate;

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public UUID getRatingId() {
		return ratingId;
	}

	public void setRatingId(UUID ratingId) {
		this.ratingId = ratingId;
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

	public Date getRatingCreationDate() {
		return ratingCreationDate;
	}

	public void setRatingCreationDate(Date ratingCreationDate) {
		this.ratingCreationDate = ratingCreationDate;
	}
}
