/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {DynamicLoaderOverviewItemModel} from "./model/dynamic.loader.overview.item.model";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  itemList: DynamicLoaderOverviewItemModel;
  title: string = "Search Results";

  constructor(private route: ActivatedRoute, private router: Router, private service: ApiService) {
  }

  ngOnInit() {
    const param = +this.route.snapshot.params['tag'];

    console.log("search result component search term: " + param);

    this.setFeaturedList();
  }

  private setFeaturedList(): void {
    const param: string = this.route.snapshot.params['param'];
    console.log("search result component search term: " + param);

    this.service.getOverviewResultItem(param).subscribe(
      items => {
        this.itemList = items; //Bind to view
        console.log(" search result items: " + items + " :: items :: " + items);
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

}
