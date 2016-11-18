/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {User} from "../domain/user.domain.class";
import {GithubOAuthService} from "../services/auth/github.oauth.service";

// custom

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: User = { username: '', password: '' };

  constructor(private service: GithubOAuthService, private router: Router) { }


  login(credentials): void {
    let userLogin = this.service.login(credentials);
    userLogin.then((res) => {
      if (res) {
        console.log("Login successful. Redirect to Dashboard!");
        this.router.navigate(['/dashboard']);
      }
    }).catch(error => console.log(`An error occured: ${error}`));
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
