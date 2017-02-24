/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.8-SNAPSHOT
 */
import {OverviewResultItemModel} from "./paged.overview.result.item.model";

export class PagedOverviewResultModel {

  totalElements: number;
  totalPages: number;
  numberOfElements: number;
  size: number;
  number: number;

  first: boolean;
  last: boolean;

  // result items
  content: Array<OverviewResultItemModel>;

  toString() {
    return "number: " + this.number + " \tcontent: " + this.content;
  }
}
