package rocks.inspectit.marketplace.mvc.app.helper;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.mvc.app.controller.DashBoardController;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;

/**
 * ## todo describe
 *
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
	public List<OverviewItemModel> getModelFromEntity(final List<ProductEntity> productEntityList) {
		final List<OverviewItemModel> returnModel = new ArrayList<>();
		productEntityList.forEach(it -> {
			final OverviewItemModel tmpModel = this.mapper.map(it, OverviewItemModel.class);
			tmpModel.setRating(it.getTotalRating());

			// map blob to byte array
			final byte[] blobAsBytes = it.getPreviewImage()
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
			tmpModel.setPreviewImage(DatatypeConverter.printBase64Binary(blobAsBytes));
			returnModel.add(tmpModel);
		});
		return returnModel;
	}
}
