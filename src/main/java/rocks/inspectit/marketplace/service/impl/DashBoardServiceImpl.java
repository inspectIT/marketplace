package rocks.inspectit.marketplace.service.impl;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(DashBoardServiceImpl.class);

	private final ProductEntityRepository repository;
	private final DozerBeanMapper mapper;

	/**
	 * use constructor injection.
	 *
	 * @param repository {@link ProductEntityRepository}
	 * @param mapper     {@link DozerBeanMapper}
	 * @since 1.0.3-SNAPSHOT
	 */
	@Autowired
	public DashBoardServiceImpl(final ProductEntityRepository repository, final DozerBeanMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	/**
	 * Return simple Mock List.
	 * Filter not used, yet.
	 *
	 * @param filter {@link ResultFilter}
	 * @return {@link DashBoardModel} as list
	 */
	@Override
	public List<DashBoardModel> getFilteredDashBoardOverview(final ResultFilter filter) {
		// filter not used yet
		return null; // this.repository.getMockedDashBoardValues();
	}

	/**
	 * FIXME
	 * there are currently different methods returning exactly what the front-end expects, due to missing entities. please implement entities and rewrite functions properly
	 * maybe use pagination instead of "topX"
	 * TODO:
	 * add lo
	 */
	@Override
	public List<DashBoardModel> getSimpleDashboardOverviewByType(final String tag, final boolean limit) {
		final List<DashBoardModel> returnModel = new ArrayList<>();
		if (limit) {
			this.repository.findAll()
					.forEach(it -> {

						final DashBoardModel tmpModel = this.mapper.map(it, DashBoardModel.class);
						tmpModel.setRating(it.getTotalRating());

						// map blob to string
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
		}
		return returnModel;
	}
}
