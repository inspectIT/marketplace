package rocks.inspectit.marketplace.mvc.domain;

import rocks.inspectit.marketplace.enums.ItemSize;
import rocks.inspectit.marketplace.enums.OrderBy;
import rocks.inspectit.marketplace.enums.SortBy;

/**
 * Modify selects with predicates.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public class ResultFilter {

	private final ItemSize resultSize;
	private final OrderBy order;
	private final SortBy sort;

	/**
	 * Simple Constructor.
	 *
	 * @param resultSize {@link ItemSize}
	 * @param order {@link OrderBy}
	 * @param sort {@link SortBy}
	 */
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


	/**
	 * Simple Builder.
	 * Supress all CheckStyle Warnings.
	 *
	 */
	// NOCHKALL
	public static final class Builder {

		private ItemSize size = ItemSize.SMALL;
		private OrderBy order = OrderBy.DATE;
		private SortBy sort = SortBy.ASC;

		public Builder resultSize(final ItemSize size) {
			this.size = size;
			return this;
		}

		public Builder resultSize(final String size) {
			this.size = ItemSize.valueOf(size.toUpperCase());
			return this;
		}

		public Builder orderBy(final OrderBy order) {
			this.order = order;
			return this;
		}

		public Builder orderBy(final String order) {
			this.order = OrderBy.getEnumByString(order);
			return this;
		}

		public Builder sortBy(final SortBy sort) {
			this.sort = sort;
			return this;
		}

		public Builder sortBy(final String sort) {
			this.sort = SortBy.getEnumByString(sort);
			return this;
		}

		public ResultFilter create() {
			return new ResultFilter(size, order, sort);
		}
	}
}
