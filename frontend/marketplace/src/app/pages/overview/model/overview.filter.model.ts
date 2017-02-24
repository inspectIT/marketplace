/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.9-SNAPSHOT
 */

export class OverviewFilterModel {

  /**
   * use array to define sort options for filter.
   * make it read only.
   *
   * todo: check if it makes sense to get values from REST
   *
   * @type {[string,string,string]}
   */
  readonly sortOptionList: Array<string> = ["NAME", 'RECENT', 'DOWNLOADS'];
  readonly limitToList: Array<string> = ["JEE", 'Spring', 'JPA', 'Hibernate'];
  readonly sortOrderList: Array<string> = ["ASC", "DESC"];

  /**
   * primary sort option, like rating or tagname
   */
  primarySortOption: string;

  /**
   * additional sort options, like name, date, downloads
   */
  additionalSortOption: string;

  /**
   * sort direction like, asc or desc
   * default: desc
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

  /**
   * will be deleted soon
   *
   * todo: delete
   */
  consolePrintValues(): void {
    console.log("log values \n order :: " + this.sortOrder +
      "\nprimary sort :: " + this.primarySortOption +
      "\nadditional sort :: " + this.additionalSortOption +
      "\nlimit to :: " + this.limitToArray +
      "\npage number :: " + this.nextPage +
      "\npage size :: " + this.pageSize)
  }

}


