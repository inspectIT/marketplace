/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Injectable} from "@angular/core";
import {Router, CanActivate, RouterStateSnapshot, ActivatedRouteSnapshot} from "@angular/router";
import {GithubOAuthService} from "./github.oauth.service";

// custom service

@Injectable()
export class AuthManagerService implements CanActivate {

  constructor(private router: Router, private service: GithubOAuthService) { }

  /**
   * @override canActivate; and give the route to navigate to (next: ActivatedRouteSnapshot) and the current snapshot (state: RouterStateSnapshot)
   * <p/>
   * check if route navigates to login:
   * <ul>
   *  <li>check if auth_key is present in local storage</li>
   *  <li>activate route if user not logged in; else log message</li>
   * </url>
   * <p/>
   * else check if auth_key is present in local storage and
   * activate route to expected page
   * <p>
   * else log error message and route to login page
   */
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let canActivateRoute: boolean = false;
    console.log('Intercept...');

    if (next.url[0].path == 'login') {
      if (this.service.isAuthenticated()){
        console.log('You are already logged in!');
        canActivateRoute = false;
      } else {
        console.log('Activate navigation to login!')
        canActivateRoute = true;
      }
    } else if (this.service.isAuthenticated()) {
      console.log(`User is authenticated. Navigate to ${next.url[0].path}`);
      canActivateRoute = true;
    } else {
      console.log('You are not logged in. Redirect to Login!');
      this.router.navigate(['/login']);
      canActivateRoute = false;
    }

    return canActivateRoute;
  }
}
