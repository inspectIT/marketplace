package rocks.inspectit.marketplace.mvc.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public class DetailModel {

	/**
	 * product information.
	 */
	private UUID productId;
	private String productName;
	private String productDescription;
	private String productPreviewImage;
	private String productCreationDate;
	private Long numberOfDownloads;

	/**
	 * user information.
	 */
	private UUID userId;
	private String userName;

	/**
	 * rating information.
	 */
	private Double rating;
	private List<RatingItemModel> ratingList = new ArrayList<>();

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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPreviewImage() {
		return productPreviewImage;
	}

	public void setProductPreviewImage(String productPreviewImage) {
		this.productPreviewImage = productPreviewImage;
	}

	public String getProductCreationDate() {
		return productCreationDate;
	}

	public void setProductCreationDate(String productCreationDate) {
		this.productCreationDate = productCreationDate;
	}

	public Long getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(Long numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<RatingItemModel> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<RatingItemModel> ratingList) {
		this.ratingList = ratingList;
	}
}
