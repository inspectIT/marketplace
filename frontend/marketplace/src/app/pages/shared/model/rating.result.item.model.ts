/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
export class RatingResultItemModel {
  id: string;
  name: string;

  autorId: string;
  author: string;
  description: string;
  authorAvatarLink: string;

  //thumbnailImage: string;
  previewImage: string;

  rating: number;
  creationDate: Date;

  toString(){
    return "id: " + this.id + " \tname: " + this.name;
  }
}
