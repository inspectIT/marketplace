/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../domain/user.domain.class";
import {GithubOAuthService} from "../../services/auth/github.oauth.service";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User = {username: '', password: ''};

  constructor(private service: GithubOAuthService, private router: Router, private http: Http) {
  }

  awesomo = [];

  login(credentials) {
    let url = 'http://localhost:8080/login/github';

    var options = 'left=100,top=10,width=400,height=500';

    window.open(url, "nameHere", options)
    /*
     let response = this.http.get(url)
     .map(this.extractData)
     .catch(this.handleError);

     let subscribed = response.subscribe(
     data => console.log(data),
     error => console.log(error)
     );
     */
  }

  getUser() {
    let url = 'http://localhost:8080/user';

    let response = this.http.get(url)
      .map(this.extractData)
      .catch(this.handleError);

    response.subscribe(
      data => {
        console.log(data);
        this.awesomo = data;
      },
      error => console.log(error)
    );
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

  /**
   * @override
   */
  ngOnInit() {
  }

  navigateBack(): void {
    window.history.back();
  }

}
