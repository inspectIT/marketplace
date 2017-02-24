/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.8-SNAPSHOT
 */
import {DynamicLoaderOverviewItemModel} from "./dynamic.loader.overview.item.model";

export class OverviewResultModel {

  totalElements: number;
  totalPages: number;
  numberOfElements: number;
  size: number;
  number: number;

  first: boolean;
  last: boolean;

  // result items
  content: Array<DynamicLoaderOverviewItemModel>;

  toString() {
    return "number: " + this.number + " \tcontent: " + this.content;
  }
}
