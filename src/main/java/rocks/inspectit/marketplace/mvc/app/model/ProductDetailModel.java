package rocks.inspectit.marketplace.mvc.app.model;

import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public class ProductDetailModel {

	private UUID productId;

	private String productName;
	private String productPreviewImage;
	private String productDescription;
	private Double rating;
	private Long numberOfDownloads;

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

	public String getProductPreviewImage() {
		return productPreviewImage;
	}

	public void setProductPreviewImage(String productPreviewImage) {
		this.productPreviewImage = productPreviewImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Long getNumberOfDownloads() {
		return numberOfDownloads;
	}

	public void setNumberOfDownloads(Long numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}
}
