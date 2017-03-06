package rocks.inspectit.marketplace.mvc.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public class UserDetailModel {

	private UUID id;

	private String userName;
	private String avatarUrl;
	private String userEmail;
	private String userCompany;
	private String userLocation;

	private String ip;

	private String role;

	private Date loginDate = new Date();

	private List<ProductDetailModel> productItemList = new ArrayList<>();
	private List<RatingDetailModel> ratingItemList = new ArrayList<>();

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public List<ProductDetailModel> getProductItemList() {
		return productItemList;
	}

	public void setProductItemList(List<ProductDetailModel> productItemList) {
		this.productItemList = productItemList;
	}

	public List<RatingDetailModel> getRatingItemList() {
		return ratingItemList;
	}

	public void setRatingItemList(List<RatingDetailModel> ratingItemList) {
		this.ratingItemList = ratingItemList;
	}
}
