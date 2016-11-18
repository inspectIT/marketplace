/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {User} from "../../domain/user.domain.class";

// custom

@Injectable()
export class GithubOAuthService {
  private authenticated: boolean = false;
  private userId: number;
  private applicationUrl: string;


  constructor(private http: Http, private url: string) {
    this.applicationUrl = url;
  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }


  /**
   * XHR to backend REST Api to authenticate user
   * <p/>
   * on success, get the user_id returned by browser
   */
  login(userCredentials: User): Promise<any> {
    this.authenticated = false;
    let url = `${this.applicationUrl}/user`;
    let headers = new Headers();
    let credentialsBody: string = `name=${userCredentials.username}&password=${userCredentials.password}`;

    headers.append('Content-Type', 'application/X-www-form-urlencoded');

    return new Promise((resolve) => {
      this.http.post(url, credentialsBody, { headers: headers }).subscribe(
        (data) => {
          console.log("post successfull")
          if (data.json().success) {
            console.log('Login successfull. :::::' + data.json());
            this.userId = data.json().userId;
            this.authenticated = true;
          }
        },
        error => {
          // todo add error handling
          console.log('error');
          this.authenticated = false;
        },
        () => {
          console.log('completed :: ' + this.authenticated);
          resolve(true);
        }
      );
    });
  }

}
