/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {DynamicLoaderModel, DynamicLoaderModelItemModel} from "../../components/dynamicloader/model/dynamic.loader.model";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  itemList: DynamicLoaderModel;

  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    const param = +this.route.snapshot.params['tag'];

    console.log("search result component search term: " + param);

    this.setFeaturedList();
  }

  private setFeaturedList(): void {
    const overviewItemList: Array<DynamicLoaderModelItemModel> = [
      {id: 1, name: "123456789012345678901234567890", thumbnailImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 2, name: "diese überschrift besteht aus 41 zeichen", thumbnailImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 3, name: "31 Zeichen für diese überschrift", thumbnailImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22)},
      {id: 4, name: "Schiffatsfährenkapitän", thumbnailImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 5, name: "Schiffatsfährenk", thumbnailImage: "5", author: "Nik", rating: 4.0, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22)},
      {id: 6, name: "16 Zeichen Limit", thumbnailImage: "6", author: "Nik", rating: 4.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 7, name: "15 Zeichen okay", thumbnailImage: "7", author: "Nik", rating: 1.5, numberDownloads: 1231231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "14 Zeichen gut", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "16 Limit Zeichen", thumbnailImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "15 okay Zeichen", thumbnailImage: "10", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "14 gut Zeichen", thumbnailImage: "11", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 12", thumbnailImage: "12", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 13", thumbnailImage: "13", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 14", thumbnailImage: "14", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 15", thumbnailImage: "15", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 16", thumbnailImage: "16", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 17", thumbnailImage: "17", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 18", thumbnailImage: "18", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 19", thumbnailImage: "19", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 20", thumbnailImage: "20", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22)},
      {id: 8, name: "feature 21", thumbnailImage: "21", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22)},
    ];
    this.itemList = new DynamicLoaderModel("Search Results", overviewItemList);
  }

}
