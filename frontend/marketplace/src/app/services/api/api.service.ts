/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.5-SNAPSHOT
 */
import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {PagedOverviewResultModel} from "../../pages/shared/model/paged.overview.result.model";
import {DashboardItemModel} from "../../pages/dashboard/model/dashboard.item.model";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
import {PermissionModel} from "../../pages/comment-page/model/permission.model";
import {HelperService} from "./helper/helper.service";

@Injectable()
export class ApiService {
  private applicationUrl: string;

  constructor(private http: Http, private url: string, private helperService: HelperService) {
    this.applicationUrl = url;
  }

  /**
   * return dashboard items by parameter
   *  uri for localhost: <a href="http://localhost:8080/app/dashboard/simple/NAME">get top 20 Products ordered by name</a>
   *
   * @param tag {@link string}
   * @returns {Promise<TResult|T>|Promise<T>}
   */
  getDashboardCarouselItem(tag: string): Observable<Array<DashboardItemModel>> {
    const url: string = `${this.applicationUrl}/app/dashboard/simple/${tag}`;
    // ...using get request
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  /**
   * return paged search results by search term.
   *  uri for localhost: <a href="http://localhost:8080/app/search/product">get all products, whose name or author contains 'product'</a>
   *
   *
   * @param searchTerm {@link string}
   * @returns {Observable<PagedOverviewResultModel>}
   */
  getSearchResultItem(searchTerm: string): Observable<PagedOverviewResultModel> {
    const url: string = `${this.applicationUrl}/app/search/${searchTerm}`;
    console.log("getSearchResultItem :: " + url);

    return this.getPagedOverviewResultFromRESTResource(url);
  }

  /**
   * return overview of products selected by "TAG". also add "filter" for additional sorting like sortOrder, additional sortOption and reduce result list to specific keywords.
   *
   * @param tag {@link string}
   * @param page {@link number}
   * @param size {@link number}
   * @param orderBy {@link string}
   * @param sortOption {@link string}
   * @param limitTo {@link Array} of {@link string}
   * @returns {Observable<PagedOverviewResultModel>}
   */
  getOverviewResultItemWithFilter(tag: string, page: number, size: number, orderBy?: string, sortOption?: string, limitTo?: Array<string>): Observable<PagedOverviewResultModel> {
    // default url; set sort direction to desc if undefined
    let url: string = `${this.applicationUrl}/app/overview/${tag}?sortOrder=${orderBy || 'DESC'}&page=${page}&size=${size}`;

    // populate params
    if (sortOption && sortOption !== '') {
      url += `&additionSortOption=${sortOption}`;
    }
    if (limitTo && limitTo.length > 0) {
      url += `&limitTo=${limitTo}`;
    }

    console.log("getOverviewResultItemWithFilter :: " + url);
    // ...using get request
    return this.getPagedOverviewResultFromRESTResource(url);
  }

  /**
   * get product details by its id.
   *
   * @param productId {@link string}
   * @returns {Promise<TResult|T>|Promise<T>}
   */
  getProductDetailById(productId: string) {
    const url = `${this.applicationUrl}/app/product/${productId}`;
    console.log("getProductDetailById :: " + url);

    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  getPagedOverviewResultFromRESTResource(url: string): Observable<PagedOverviewResultModel> {
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  persistProduct(username: string, imagesToUpload: Array<File>, productsToUpload: Array<File>, productName: string, productDescription: string, limitToArray: Array<string>) {
    const url = `${this.applicationUrl}/app/product`;
    const formData: any = new FormData();

    // set user
    formData.append('username', username);
    for (let i = 0; i < imagesToUpload.length; i++) {
      formData.append("images", imagesToUpload[i], imagesToUpload[i].name);
    }
    for (let i = 0; i < productsToUpload.length; i++) {
      formData.append("products", productsToUpload[i], productsToUpload[i].name);
    }
    // set form values
    formData.append('name', productName);
    formData.append('description', productDescription);

    // set keywords
    formData.append('keywords', limitToArray);

    return this.http.post(url, formData)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  updateProductDownloadCounter(productId: string) {
    const url = `${this.applicationUrl}/app/update/product/${productId}/download`;
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  persistComment(username: string, productId: string, formValueJson: string) {
    const url = `${this.applicationUrl}/app/rating/${productId}/${username}`;
    const body = formValueJson;
    const headers = new Headers({'Content-Type': 'application/json'});
    const options = new RequestOptions({headers: headers});

    return this.http.post(url, body, options)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  /**
   * check if user is allowed to add comment to product
   *
   * @param userName {@link string}
   * return true if user is allowed to comment; else false
   */
  userHasPermissionToComment(userName: string, productId: string): Observable<PermissionModel> {
    const url = `${this.applicationUrl}/app/userHasPermission/${userName}/${productId}/comment`;
    console.log("get user permission for comment by url " + url);
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

}
