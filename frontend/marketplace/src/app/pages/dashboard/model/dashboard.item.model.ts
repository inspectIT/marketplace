/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
export class DashboardItemModel {
  id: string;
  name: string;
  author: string;

  //thumbnailImage: string;
  previewImage: string;


  rating: number;
  numberDownloads: number;
  creationDate: Date;

  toString(){
    return "id: " + this.id + " \tname: " + this.name;
  }
}
