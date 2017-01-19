import {DashboardItemModel} from "./dashboard.item.model";
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */

/**
 * obsolete
 * @since 1.0.5-SNAPSHOT
 */
export class DashboardModel {
  title: string;
  itemList: Array<DashboardItemModel>;
  tag: string;

  constructor(title: string, tag: string, itemList: Array<DashboardItemModel>) {
    this.title = title;
    this.tag = tag;
    this.itemList = itemList;
  };
}
