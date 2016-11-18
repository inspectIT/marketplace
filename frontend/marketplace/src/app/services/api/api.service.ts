import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
// custom

@Injectable()
export class ApiService {
  private applicationUrl: string;

  constructor(private http: Http, private url: string) {
    this.applicationUrl = url;
  }


  getDashboardOverview(filter?): Observable<any> {
    let url: string = `${this.applicationUrl}/get/dashboard/simple/small/date/asc`
    let response = this.http.get(url)
      .map(this.extractData)
      .catch(this.handleError);
    console.log("return response: " + response);
    return response;

  }

  private extractData(data: Response) {
    console.log(`get successfull; pass json: ${data}`);
    let body = data.json();
    return body || {};
  }

  private handleError(error: Response | any) {
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
