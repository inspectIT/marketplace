/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../domain/user.domain.class";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
import {HelperService} from "./helper/helper.service";

@Injectable()
export class ApiUserService {

  private applicationUrl: string;

  constructor(private http: Http, private url: string, private helperService: HelperService) {
    this.applicationUrl = url;
  }

  getUser(): Observable<User> {
    const url = `${this.applicationUrl}/app/get/user`;
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  getUserDetailByUserName(userName: string): Observable<User> {
    const url = `${this.applicationUrl}/app/get/user/${userName}/detail`;
    console.log("get user from " + url);
    return this.http.get(url)
    // ...and calling .json() on the response to return data
      .map((res: Response) => this.helperService.extractData(res))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

  login() {
    const url = `${this.applicationUrl}//login/github`;
    this.http.get(url).subscribe(
      () => console.log("login successfull"),
      err => this.helperService.handleError(err)
    );

  }

  logout() {
    const url = `${this.applicationUrl}/logout`;
    return this.http.post(url, {})// ...and calling .json() on the response to return data
      .map(()=> console.log("logout"))
      //...errors if any
      .catch((error: any) => this.helperService.handleError(error));
  }

}
