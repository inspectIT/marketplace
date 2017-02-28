/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
import {OverviewResultItemModel} from "../pages/shared/model/paged.overview.result.item.model";
import {RatingResultItemModel} from "../pages/shared/model/rating.result.item.model";

export class User {

  id: string;
  username: string;
  avatarUrl: string;
  userCompany: string;
  userLocation: string;

  productItemList: Array<OverviewResultItemModel>;
  ratingItemList: Array<RatingResultItemModel>;

  constructor() {
  }

  public printValues(): string {
    return "id: " + this.id +
      " :: username: " + this.username +
      " :: avatar: " + this.avatarUrl +
      " :: company: " + this.userCompany +
      " :: location:" + this.userLocation;
  }
}
