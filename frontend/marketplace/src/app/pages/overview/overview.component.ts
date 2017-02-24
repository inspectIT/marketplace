/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";
import {PagedOverviewResultModel} from "../shared/model/paged.overview.result.model";
import {OverviewFilterModel} from "./model/overview.filter.model";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  overviewFilterModel: OverviewFilterModel;
  overviewItemList: PagedOverviewResultModel;

  constructor(private route: ActivatedRoute, private router: Router, private service: ApiService) {
    this.overviewFilterModel = new OverviewFilterModel();
  }

  /**
   * populate primary sort option and request results from REST service.
   */
  ngOnInit() {
    const param = this.route.snapshot.params['tag'];
    this.overviewFilterModel.primarySortOption = param;

    this.service.getOverviewResultItemWithFilter(this.overviewFilterModel.primarySortOption, this.overviewFilterModel.nextPage, this.overviewFilterModel.pageSize).subscribe(
      items => {
        this.overviewItemList = items; //Bind to view
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
    if (this.overviewFilterModel.sortOrder !== sortOrder) {
      this.overviewFilterModel.sortOrder = sortOrder;
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
  addAndUpdateSortOption(sortOptionParam: string) {
    if (this.overviewFilterModel.primarySortOption === sortOptionParam || this.overviewFilterModel.additionalSortOption === sortOptionParam) {
      console.log("sortOptionParam :: do nothing");
    } else {
      this.resetPaging();
      if (this.overviewFilterModel.sortOptionList.indexOf(this.overviewFilterModel.primarySortOption) !== -1) {
        this.overviewFilterModel.primarySortOption = sortOptionParam;
      } else {
        this.overviewFilterModel.additionalSortOption = sortOptionParam;
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
    if (this.overviewFilterModel.limitToArray.indexOf(limitToValue) === -1) {
      this.overviewFilterModel.limitToArray.push(limitToValue);
    } else {
      this.overviewFilterModel.limitToArray.splice(this.overviewFilterModel.limitToArray.indexOf(limitToValue), 1);
    }
    this.updateResults();
  }

  /**
   * populates existing list with values from new request.
   *
   * @param pageNumber {@link number}
   */
  updateResultList(pageNumber: number) {
    this.overviewFilterModel.nextPage = pageNumber + 1;
    this.service.getOverviewResultItemWithFilter(this.overviewFilterModel.primarySortOption, this.overviewFilterModel.nextPage, this.overviewFilterModel.pageSize,
      this.overviewFilterModel.sortOrder, this.overviewFilterModel.additionalSortOption, this.overviewFilterModel.limitToArray)
      .subscribe(
        items => {
          const tmpList = this.overviewItemList.content.concat(items.content);

          this.overviewItemList = items;
          this.overviewItemList.content = tmpList;
        },
        err => {
          // Log errors if any
          console.log(err);
        }
      );
  }

  updateResults() {
    this.logValues();

    this.service.getOverviewResultItemWithFilter(this.overviewFilterModel.primarySortOption, this.overviewFilterModel.nextPage, this.overviewFilterModel.pageSize,
      this.overviewFilterModel.sortOrder, this.overviewFilterModel.additionalSortOption, this.overviewFilterModel.limitToArray)
      .subscribe(
        items => {
          this.overviewItemList = items; //Bind to view
        },
        err => {
          // Log errors if any
          console.log(err);
        }
      );
  }

  /**
   * will be deleted soon
   *
   * todo: delete
   */
  logValues(): void {
    this.overviewFilterModel.consolePrintValues();
  }

  private resetPaging() {
    this.overviewFilterModel.nextPage = 0;
    // this.overviewFilterModel.pageSize = 24;
  }
}
