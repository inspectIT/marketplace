/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { GithubOAuthService } from "./github.oauth.service";
// custom service
export var AuthManagerService = (function () {
    function AuthManagerService(router, service) {
        this.router = router;
        this.service = service;
    }
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
    AuthManagerService.prototype.canActivate = function (next, state) {
        var canActivateRoute = false;
        console.log('Intercept...');
        if (next.url[0].path == 'login') {
            if (this.service.isAuthenticated()) {
                console.log('You are already logged in!');
                canActivateRoute = false;
            }
            else {
                console.log('Activate navigation to login!');
                canActivateRoute = true;
            }
        }
        else if (this.service.isAuthenticated()) {
            console.log("User is authenticated. Navigate to " + next.url[0].path);
            canActivateRoute = true;
        }
        else {
            console.log('You are not logged in. Redirect to Login!');
            this.router.navigate(['/login']);
            canActivateRoute = false;
        }
        return canActivateRoute;
    };
    AuthManagerService = __decorate([
        Injectable(), 
        __metadata('design:paramtypes', [Router, GithubOAuthService])
    ], AuthManagerService);
    return AuthManagerService;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/auth/auth.manager.service.js.map