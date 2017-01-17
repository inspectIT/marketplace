/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {DashboardModel, DashboardItemModel} from "./model/dashboard.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  featuredList: DashboardModel;
  recentList: DashboardModel;
  ratedList: DashboardModel;
  promotedList: DashboardModel;

  constructor() {
  }

  ngOnInit() {
    console.log("execute initialisation");
    this.populateLists();
  }

  populateLists(): void {
    this.setFeaturedList();
    this.setRecentList();
    this.setRatedList();
    this.setPromotedList();
  }

  private setFeaturedList(): void {
    const featureItemList: Array<DashboardItemModel> = [
      {id: 1, name: "123456789012345678901234567890", thumbnailImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 2, name: "diese überschrift besteht aus 41 zeichen", thumbnailImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 3, name: "31 Zeichen für diese überschrift", thumbnailImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22)},
      {id: 4, name: "Schiffatsfährenkapitän", thumbnailImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 5, name: "Schiffatsfährenk", thumbnailImage: "5", author: "Nik", rating: 4.0, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 6, name: "16 Zeichen Limit", thumbnailImage: "6", author: "Nik", rating: 4.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 7, name: "15 Zeichen okay", thumbnailImage: "7", author: "Nik", rating: 1.5, numberDownloads: 1231231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "14 Zeichen gut", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 9, name: "16 Limit Zeichen", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 10, name: "15 okay Zeichen", thumbnailImage: "10", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 11, name: "14 gut Zeichen", thumbnailImage: "11", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 12, name: "feature 12", thumbnailImage: "12", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 13, name: "feature 13", thumbnailImage: "13", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 81, name: "feature 14", thumbnailImage: "14", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 82, name: "feature 15", thumbnailImage: "15", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 83, name: "feature 16", thumbnailImage: "16", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 84, name: "feature 17", thumbnailImage: "17", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 85, name: "feature 18", thumbnailImage: "18", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 86, name: "feature 19", thumbnailImage: "19", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 87, name: "feature 20", thumbnailImage: "20", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 88, name: "feature 21", thumbnailImage: "21", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
    ];
    this.featuredList = new DashboardModel("Featured", "featured,promoted", featureItemList);
  }

  private setRecentList(): void {
    const recentItemList: Array<DashboardItemModel> = [
      {id: 1, name: "Recent 1", thumbnailImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 2, name: "Recent 2", thumbnailImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 3, name: "Recent 3", thumbnailImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22)},
      {id: 4, name: "Recent 4", thumbnailImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 5, name: "Recent 5", thumbnailImage: "5", author: "Nik", rating: 4.0, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 6, name: "Recent 6", thumbnailImage: "6", author: "Nik", rating: 4.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 7, name: "Recent 7", thumbnailImage: "7", author: "Nik", rating: 1.5, numberDownloads: 1231231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "Recent 8", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
    ];
    this.recentList = new DashboardModel("Most Recent", "recent", recentItemList);
  }

  private setRatedList(): void {
    const ratedItemList: Array<DashboardItemModel> = [
      {id: 1, name: "Top Rated 1", thumbnailImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 2, name: "Top Rated 2", thumbnailImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 3, name: "Top Rated 3", thumbnailImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22)},
      {id: 4, name: "Top Rated 4", thumbnailImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 5, name: "Top Rated 5", thumbnailImage: "5", author: "Nik", rating: 4.0, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 6, name: "Top Rated 6", thumbnailImage: "6", author: "Nik", rating: 4.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 7, name: "Top Rated 7", thumbnailImage: "7", author: "Nik", rating: 1.5, numberDownloads: 1231231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "Top Rated 8", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 28, name: "Top Rated 9", thumbnailImage: "9", author: "Nik", rating: 2.3, numberDownloads: 91231, creationDate: new Date(2017, 0, 22)},
      {id: 38, name: "Top Rated 10", thumbnailImage: "10", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 48, name: "Top Rated 11", thumbnailImage: "11", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 58, name: "Top Rated 12", thumbnailImage: "12", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
    ];
    this.ratedList = new DashboardModel("Top Rated", "rated", ratedItemList);
  }

  private setPromotedList() {
    const promotedItemList: Array<DashboardItemModel> = [
      {id: 1, name: "Promoted 1", thumbnailImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 2, name: "Promoted 2", thumbnailImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 3, name: "Promoted 3", thumbnailImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22)},
      {id: 4, name: "Promoted 4", thumbnailImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
    ];
    this.promotedList = new DashboardModel("Promoted By", "promoted", promotedItemList);
  }

}
