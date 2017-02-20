import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
import {DashboardItemModel} from "../../pages/dashboard/model/dashboard.item.model";
import {SearchResultModel} from "../../pages/search-result/model/search.result.model";
import {DynamicLoaderOverviewItemModel} from "../../pages/overview/model/dynamic.loader.overview.item.model";

@Injectable()
export class ApiService {
  private applicationUrl: string;

  constructor(private http: Http, private url: string) {
    this.applicationUrl = url;
  }

  getResultFromRESTResource(url: string) {
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  getDashboardCarouselItem(tag: string): Observable<Array<DashboardItemModel>> {
    const url: string = `${this.applicationUrl}/app/get/dashboard/simple/${tag}`;
    // ...using get request
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.extractData(res))
      //...errors if any
      .catch((error: any) => this.handleError(error));
  }

  getSearchResultItem(searchTerm: string): Observable<SearchResultModel> {
    const url: string = `${this.applicationUrl}/app/get/search/${searchTerm}`;
    console.log("getSearchResultItem :: " + url);

    return this.getResultFromRESTResource(url);
  }

  getOverviewResultItem(tag: string): Observable<DynamicLoaderOverviewItemModel> {
    const url: string = `${this.applicationUrl}/list?sortOption=${tag}`;
    console.log("getSearchResultItem :: " + url);

    return this.getResultFromRESTResource(url);
  }

  getSearchResultItemWithFilter(tag: string, orderBy: string, limitTo: Array<string>, page: number | 0, size: number | 2000000): Observable<DynamicLoaderOverviewItemModel> {
    const url: string = `${this.applicationUrl}/list?sortOption=${tag}&orderBy=${orderBy}&limitTo=${limitTo}`;
    console.log("getSearchResultItem :: " + url);

    return this.getResultFromRESTResource(url);
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
