package rocks.inspectit.marketplace.mvc.app.helper;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RoleEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;
import rocks.inspectit.marketplace.mvc.app.model.ProductDetailModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingDetailModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingItemModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingModel;
import rocks.inspectit.marketplace.mvc.app.model.UserDetailModel;
import rocks.inspectit.marketplace.service.dto.GitHubEmailDto;

/**
 * ## todo describe.
 * <p>
 * use {@link Service} annotation for autowiring
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Service
public class ObjectMapper {
	private final DozerBeanMapper mapper;

	/**
	 * constructor injection.
	 *
	 * @param mapper {@link DozerBeanMapper}
	 */
	@Autowired
	public ObjectMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * keep service clean and avoid leaking model information into service.
	 * map from entity to model here.
	 *
	 * @param productEntityList as {@link List} of {@link ProductEntity}
	 * @return {@link List} of {@link OverviewItemModel}
	 */
	public List<OverviewItemModel> getOverviewItemModelListFromProductEntityList(final List<ProductEntity> productEntityList) {
		final List<OverviewItemModel> returnModel = new ArrayList<>();
		productEntityList.forEach(it -> {
			final OverviewItemModel tmpModel = this.mapper.map(it, OverviewItemModel.class);
			tmpModel.setRating(it.getTotalRating().orElse(0.));
			tmpModel.setPreviewImage(getBase64BinaryFromBlob(it.getPreviewImage()));
			returnModel.add(tmpModel);
		});
		return returnModel;
	}

	/**
	 * ## todo : describe.
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return {@link DetailModel}
	 */
	public DetailModel getDetailModelFromProductEntity(final ProductEntity productEntity) {
		final List<RatingItemModel> ratingList = new ArrayList<>();
		productEntity.getRatingEntityList().forEach(rating -> {
			ratingList.add(this.mapper.map(rating, RatingItemModel.class));
		});

		final DetailModel model = this.mapper.map(productEntity, DetailModel.class);
		model.setRatingList(ratingList);
		model.setRating(productEntity.getTotalRating().orElse(0.));
		model.setProductPreviewImage(this.getBase64BinaryFromBlob(productEntity.getPreviewImage()));

		return model;
	}

	/**
	 * ## todo : describe.
	 *
	 * @param userEntity {@link UserEntity}
	 * @return {@link UserDetailModel}
	 */
	public UserDetailModel getUserDetailModelFromUserEntity(final UserEntity userEntity) {
		final List<ProductDetailModel> productDetailModelList = new ArrayList<>();
		userEntity.getProductEntityList().forEach(it -> {
			final ProductDetailModel tmpModel = this.mapper.map(it, ProductDetailModel.class);
			tmpModel.setRating(it.getTotalRating().orElse(0.));
			tmpModel.setProductPreviewImage(getBase64BinaryFromBlob(it.getPreviewImage()));
			productDetailModelList.add(tmpModel);
		});

		final List<RatingDetailModel> ratingDetailModelList = new ArrayList<>();
		userEntity.getRatingEntityList().forEach(it -> {
			final RatingDetailModel tmpModel = this.mapper.map(it, RatingDetailModel.class);
			tmpModel.setRatingCreationDate(it.getModifyDate().orElse(it.getCreationDate()));
			ratingDetailModelList.add(tmpModel);
		});

		final UserDetailModel model = this.mapper.map(userEntity, UserDetailModel.class);

		model.setProductItemList(productDetailModelList);
		model.setRatingItemList(ratingDetailModelList);
		return model;
	}

	/**
	 * Map manually for prototype.
	 * <p>
	 * todo: add map configuration to dozer
	 *
	 * @param auth      {@link Authentication}
	 * @param emailList {@link List} of {@link GitHubEmailDto}
	 * @return {@link UserDetailModel}
	 */
	public UserDetailModel getUserDetailModelFromOAuth2Authentication(final Authentication auth, final List<GitHubEmailDto> emailList) {
		final UserDetailModel model = new UserDetailModel();

		model.setUserName(auth.getName());

		model.setAvatarUrl(this.getValueFromDetailsByString(auth, "avatar_url"));
		model.setUserCompany(this.getValueFromDetailsByString(auth, "company"));
		model.setUserLocation(this.getValueFromDetailsByString(auth, "location"));

		// populate email
		final String userEmail = Optional
				.ofNullable(this.getValueFromDetailsByString(auth, "email"))
				.orElse(emailList.stream().filter(GitHubEmailDto::isPrimary).findFirst().get().getEmail());
		model.setUserEmail(userEmail);

		// set ip from {@link OAuth2AuthenticationDetails}
		model.setIp(((OAuth2AuthenticationDetails) auth.getDetails()).getRemoteAddress());

		// default role: user
		// todo: check against user of organisation "inspectit" and if organisation has user; set role to "admin" or "mod"
		model.setRole("user");

		return model;
	}

	/**
	 * map blob to byte array.
	 *
	 * @param optionalBlob {@link Optional} of {@link Blob}
	 * @return {@link String}
	 */
	private String getBase64BinaryFromBlob(final Optional<byte[]> optionalBlob) {
		final byte[] blobAsBytes = optionalBlob.orElse(new byte[0]);
		return DatatypeConverter.printBase64Binary(blobAsBytes);
	}

	/**
	 * cast auth to {@link OAuth2Authentication} to <em>getUserAuthentication</em>.
	 * cast getDetails to {@link LinkedHashMap}.
	 * cast value to {@link String}.
	 *
	 * @param auth {@link Authentication}
	 * @param key  {@link String}
	 * @return {@link String}
	 */
	private String getValueFromDetailsByString(final Authentication auth, final String key) {
		return (String) ((LinkedHashMap) ((OAuth2Authentication) auth).getUserAuthentication().getDetails()).get(key);
	}

	/**
	 * update {@link UserEntity} with values from {@link UserDetailModel}.
	 *
	 * @param userEntity {@link Optional} of {@link UserEntity}
	 * @param role       {@link RoleEntity}
	 * @param user       {@link UserDetailModel}
	 * @return {@link UserEntity}
	 */
	public UserEntity getUpdatedUserEntityFromUserDetailModel(final Optional<UserEntity> userEntity, final RoleEntity role, final UserDetailModel user) {
		// use option for one line
		final UserEntity entity = userEntity.orElse(new UserEntity());
		entity.setRoleEntity(role);

		this.mapper.map(user, entity);
		return entity;
	}

	/**
	 * Simple mapper.
	 *
	 * @param rating {@link RatingModel}
	 * @return {@link RatingEntity}
	 * @since 1.1.1-SNAPSHOT
	 */
	public RatingEntity getRatingEntityFromRatingModel(final RatingModel rating) {
		return this.mapper.map(rating, RatingEntity.class);
	}
}
