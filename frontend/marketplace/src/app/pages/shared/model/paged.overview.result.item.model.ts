/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.5-SNAPSHOT
 */
export class OverviewResultItemModel {
  id: string;
  name: string;
  author: string;
  description: string;

  //thumbnailImage: string;
  previewImage: string;

  rating: number;
  numberDownloads: number;
  creationDate: Date;

  toString(){
    return "id: " + this.id + " \tname: " + this.name;
  }
}
