package rocks.inspectit.marketplace.mvc.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public class DetailModel {

	private String authorId;
	private String author;
	private String username;
	private String previewImage;

	private Double totalRating;

	private List<RatingItemModel> ratingList = new ArrayList<>();


	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}

	public Double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}

	public List<RatingItemModel> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<RatingItemModel> ratingList) {
		this.ratingList = ratingList;
	}
}
