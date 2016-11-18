package rocks.inspectit.marketplace.mvc.domain;

import rocks.inspectit.marketplace.enums.ItemSize;
import rocks.inspectit.marketplace.enums.OrderBy;
import rocks.inspectit.marketplace.enums.SortBy;

/**
 * Modify selects with predicates
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public class ResultFilter {

	private final ItemSize resultSize;
	private final OrderBy order;
	private final SortBy sort;

	public ResultFilter(final ItemSize resultSize, final OrderBy order, final SortBy sort) {
		this.resultSize = resultSize;
		this.order = order;
		this.sort = sort;
	}

	public ItemSize getResultSize() {
		return resultSize;
	}

	public OrderBy getOrder() {
		return order;
	}

	public SortBy getSort() {
		return sort;
	}

	public static final class Builder {

		private ItemSize resultSize = ItemSize.SMALL;
		private OrderBy order = OrderBy.DATE;
		private SortBy sort = SortBy.ASC;

		public Builder resultSize(final ItemSize resultSize) {
			this.resultSize = resultSize;
			return this;
		}

		public Builder resultSize(final String resultSize) {
			this.resultSize = ItemSize.valueOf(resultSize.toUpperCase());
			return this;
		}

		public Builder order(final OrderBy order) {
			this.order = order;
			return this;
		}

		public Builder order(final String order) {
			this.order = OrderBy.getEnumByString(order);
			return this;
		}

		public Builder sort(final SortBy sort) {
			this.sort = sort;
			return this;
		}

		public Builder sort(final String sort) {
			this.sort = SortBy.getEnumByString(sort);
			return this;
		}

		public ResultFilter create() {
			return new ResultFilter(resultSize, order, sort);
		}
	}
}
