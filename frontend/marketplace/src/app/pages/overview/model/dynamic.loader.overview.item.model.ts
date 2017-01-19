/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.5-SNAPSHOT
 */
export class DynamicLoaderOverviewItemModel {
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
