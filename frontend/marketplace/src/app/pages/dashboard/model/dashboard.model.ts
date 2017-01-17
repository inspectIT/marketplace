/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */

export class DashboardModel {
  title: string;
  itemList: Array<DashboardItemModel>;
  tag: string;


  constructor(title: string, tag: string, itemList: Array<DashboardItemModel>){
    this.title = title;
    this.tag = tag;
    this.itemList = itemList;
  };
}

export class DashboardItemModel {
  id: number;

  name: string;
  thumbnailImage: string;
  author: string;

  rating: number;
  numberDownloads: number;
  creationDate: Date;

  toString(){
    return "id: " + this.id + " \tname: " + this.name;
  }
}
