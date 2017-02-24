package rocks.inspectit.marketplace.mvc.app.helper;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.mvc.app.controller.DashBoardController;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingItemModel;

/**
 * ## todo describe
 * <p>
 * use {@link Service} annotation for autowiring
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Service
public class ObjectMapper {
	private static final Logger LOG = LoggerFactory.getLogger(DashBoardController.class);

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
	public List<OverviewItemModel> getListModelFromEntityList(final List<ProductEntity> productEntityList) {
		final List<OverviewItemModel> returnModel = new ArrayList<>();
		productEntityList.forEach(it -> {
			final OverviewItemModel tmpModel = new OverviewItemModel();
			this.mapper.map(it, tmpModel);
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
	public DetailModel getSimpleModelFromEntity(final ProductEntity productEntity) {
		final List<RatingItemModel> ratingList = new ArrayList<>();
		productEntity.getRatingEntityList().forEach(rating -> {
			final RatingItemModel model = this.mapper.map(rating, RatingItemModel.class);
			ratingList.add(model);
		});

		final DetailModel model = this.mapper.map(productEntity, DetailModel.class);
		model.setRatingList(ratingList);
		model.setPreviewImage(this.getBase64BinaryFromBlob(productEntity.getPreviewImage()));

		return model;
	}

	/**
	 * map blob to byte array.
	 *
	 * @param optionalBlob {@link Optional} of {@link Blob}
	 * @return {@link String}
	 */
	private String getBase64BinaryFromBlob(final Optional<Blob> optionalBlob) {
		final byte[] blobAsBytes = optionalBlob
				.map(blob -> {
							byte[] bytes;
							try {
								bytes = blob.getBytes(1, (int) blob.length());
								//release the blob and free up memory. (since JDBC 4.0)
								blob.free();
							} catch (SQLException e) {
								bytes = new byte[0];
								LOG.error(e.getMessage(), e);
							}
							return bytes;
						}
				).orElse(new byte[0]);
		return DatatypeConverter.printBase64Binary(blobAsBytes);
	}
}
