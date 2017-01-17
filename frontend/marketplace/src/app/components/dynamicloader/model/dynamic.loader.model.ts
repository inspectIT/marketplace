/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */

export class DynamicLoaderModel {
  title: string;
  itemList: Array<DynamicLoaderModelItemModel>;

  constructor(title: string, itemList: Array<DynamicLoaderModelItemModel>) {
    this.title = title;
    this.itemList = itemList;
  };
}

export class DynamicLoaderModelItemModel {
  id: number;

  name: string;
  thumbnailImage: string;
  author: string;

  rating: number;
  numberDownloads: number;
  creationDate: Date;

  toString() {
    return "id: " + this.id + " \tname: " + this.name;
  }
}
