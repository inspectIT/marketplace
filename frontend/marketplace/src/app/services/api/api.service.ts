/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.5-SNAPSHOT
 */
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {PagedOverviewResultModel} from "../../pages/shared/model/paged.overview.result.model";
import {DashboardItemModel} from "../../pages/dashboard/model/dashboard.item.model";
import {User} from "../../domain/user.domain.class";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";

@Injectable()
export class ApiService {
  private applicationUrl: string;

  constructor(private http: Http, private url: string) {
    this.applicationUrl = url;
  }

  /**
   * return dashboard items by parameter
   *  uri for localhost: <a href="http://localhost:8080/app/get/dashboard/simple/NAME">get top 20 Products ordered by name</a>
   *
   * @param tag {@link string}
   * @returns {Promise<TResult|T>|Promise<T>}
   */
  getDashboardCarouselItem(tag: string): Observable<Array<DashboardItemModel>> {
    const url: string = `${this.applicationUrl}/app/get/dashboard/simple/${tag}`;
    // ...using get request
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  /**
   * return paged search results by search term.
   *  uri for localhost: <a href="http://localhost:8080/app/get/search/product">get all products, whose name or author contains 'product'</a>
   *
   *
   * @param searchTerm {@link string}
   * @returns {Observable<PagedOverviewResultModel>}
   */
  getSearchResultItem(searchTerm: string): Observable<PagedOverviewResultModel> {
    const url: string = `${this.applicationUrl}/app/get/search/${searchTerm}`;
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
    let url: string = `${this.applicationUrl}/app/get/overview/${tag}?sortOrder=${orderBy || 'DESC'}&page=${page}&size=${size}`;

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
    const url = `${this.applicationUrl}/app/get/product/${productId}`;
    console.log("getProductDetailById :: " + url);

    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  getPagedOverviewResultFromRESTResource(url: string): Observable<PagedOverviewResultModel> {
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  persistProduct(username: string, imagesToUpload: Array<File>, productsToUpload: Array<File>, productName: string, productDescription: string, limitToArray: Array<string>) {
    const url = `${this.applicationUrl}/app/add/product`;
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
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  getUser(): Observable<User> {
    const url = `${this.applicationUrl}/app/get/user`;
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  login() {
    const url = `${this.applicationUrl}//login/github`;
    this.http.get(url).subscribe(
      () => console.log("login successfull"),
      err => this.handleError(err),
      () => console.log('Fetching complete for Server Metrics')
    );

  }

  logout() {
    const url = `${this.applicationUrl}/logout`;
    this.http.post(url, {}).subscribe(
      () => console.log("logout successfull"),
      err => this.handleError(err),
      () => console.log('Fetching complete for Server Metrics')
    );
  }

  private extractData(data: Response) {
    let body = data.json();
    return body || {};
  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
