package rocks.inspectit.marketplace.mvc.app.model;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
public class RatingModel {

	private Integer rating;
	private String comment;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
