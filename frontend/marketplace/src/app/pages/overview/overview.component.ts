/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";
import {OverviewResultModel} from "./model/overview.result.model";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  /**
   * use array to define sort options for filter.
   *
   * todo: check if it makes sense to get values from REST
   *
   * @type {[string,string,string]}
   */
  sortOptionList: Array<string> = ['NAME', 'RECENT', 'DOWNLOADS'];
  limitToList: Array<string> = ['JEE', 'Spring', 'JPA', 'Hibernate'];

  overviewItemList: OverviewResultModel;

  /**
   * primary sort option, like rating or tagname
   */
  primarySortOption: string;
  /**
   * additional sort options, like name, date, downloads
   */
  additionalSortOption: string;
  /**
   * sort directtion like, asc or desc
   */
  sortOrder: string = "DESC";
  /**
   * limit values, like jee, jpa, hibernate...
   */
  limitToArray: Array<string> = [];

  /**
   * paging info, like size and page
   */
  nextPage: number = 0;
  pageSize: number = 24;

  constructor(private route: ActivatedRoute, private router: Router, private service: ApiService) {
  }

  /**
   * populate primary sort option and request results from REST service.
   */
  ngOnInit() {
    const param = this.route.snapshot.params['tag'];
    this.primarySortOption = param;
    console.log("overview component overview for param: " + this.primarySortOption);

    this.service.getOverviewResultItem(this.primarySortOption, this.nextPage, this.pageSize).subscribe(
      items => {
        this.overviewItemList = items; //Bind to view
        console.log(" search result items: " + items + " :: items :: " + items);
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

  /**
   * execute request only if sort order changes.
   * reset paging because of other sorting.
   *
   * set sort order to 'DESC' or 'ASC' and update list from api.
   *
   * @param sortOrder {@link string}
   */
  updateSortOrder(sortOrder: string) {
    if (this.sortOrder !== sortOrder) {
      this.sortOrder = sortOrder;

      this.resetPaging();

      this.updateResults();
    } else {
      console.log("updateSortOrder :: do nothing!");
    }
  }

  /**
   * execute only if primarySortOption or additionalSortOption changes.
   * reset paging, because of new order of objects.
   *
   * Check if primarySortOption is one of the possible Sort options.
   * if yes: don't add sort option to request; instead replace primary sort option with new value.
   * else: add additional sort option to request.
   *
   * @param sortOptionParam {@link string}
   */
  addSortOption(sortOptionParam: string) {
    if (this.primarySortOption === sortOptionParam || this.additionalSortOption === sortOptionParam) {
      console.log("sortOptionParam :: do nothing");
    } else {
      this.resetPaging();
      if (this.sortOptionList.indexOf(this.primarySortOption) !== -1) {
        this.primarySortOption = sortOptionParam;
      } else {
        this.additionalSortOption = sortOptionParam;
      }

      this.updateResults();
    }
  }

  /**
   * populate limit to array with values and request list update from api.
   * reset paging, because of order and new objects.
   *
   * @param limitToValue {@link string}
   */
  updateLimitToArray(limitToValue: string) {
    this.resetPaging();
    if (this.limitToArray.indexOf(limitToValue) === -1) {
      this.limitToArray.push(limitToValue);
    } else {
      this.limitToArray.splice(this.limitToArray.indexOf(limitToValue), 1);
    }
    this.updateResults();
  }

  /**
   * populates existing list with values from new request.
   *
   * @param pageNumber {@link number}
   */
  updateList(pageNumber: number) {
    console.log("page: " + pageNumber);
    this.nextPage = pageNumber + 1;
    this.service.getOverviewResultItemWithFilter(this.primarySortOption, this.additionalSortOption, this.sortOrder, this.limitToArray, this.nextPage, this.pageSize).subscribe(
      items => {
        const tmpList = this.overviewItemList.content.concat(items.content);

        this.overviewItemList = items;
        this.overviewItemList.content = tmpList;

        console.log(" search result items: " + items + " :: items :: " + items);
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

  updateResults() {
    this.logValues();

    this.service.getOverviewResultItemWithFilter(this.primarySortOption, this.additionalSortOption, this.sortOrder, this.limitToArray, this.nextPage, this.pageSize).subscribe(
      items => {
        this.overviewItemList = items; //Bind to view
        console.log(" search result items: " + items + " :: items :: " + items);
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

  logValues() {
    console.log("log values \n order :: " + this.sortOrder +
      "\nprimary sort :: " + this.primarySortOption +
      "\nadditional sort :: " + this.additionalSortOption +
      "\nlimit to :: " + this.limitToArray +
      "\npage number :: " + this.nextPage +
      "\npage size :: " + this.pageSize)
  }

  private resetPaging() {
    this.nextPage = 0;
    // this.pageSize = 24;
  }
}
