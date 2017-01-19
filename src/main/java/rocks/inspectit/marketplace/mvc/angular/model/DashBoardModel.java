package rocks.inspectit.marketplace.mvc.angular.model;

import java.util.Date;
import java.util.UUID;

/**
 * Displaying the filtered <i>inspectIT Configuration</i> to the dashboard.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public class DashBoardModel {

	/**
	 * update all attributes:
	 *
	 * @since 1.0.4-SNAPSHOT
	 */
	private UUID id;

	private String name;
	private String author;

	private Long numberDownloads;

	private Double rating;

	private Date creationDate;

	private String previewImage;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getNumberDownloads() {
		return numberDownloads;
	}

	public void setNumberDownloads(Long numberDownloads) {
		this.numberDownloads = numberDownloads;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}
}
