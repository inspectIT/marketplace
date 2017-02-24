/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import "rxjs/add/operator/switchMap";
import {ApiService} from "../../services/api/api.service";
import {PagedOverviewResultModel} from "../shared/model/paged.overview.result.model";

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent implements OnInit {

  itemList: PagedOverviewResultModel;

  constructor(private route: ActivatedRoute, private router: Router,
    private service: ApiService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['param'];
    console.log("search result component search term: " + param);

    this.service.getSearchResultItem(param).subscribe(
      items => {
        this.itemList = items; //Bind to view
        console.log(" search result items: " + items + " :: items :: " + items.content);
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

}
