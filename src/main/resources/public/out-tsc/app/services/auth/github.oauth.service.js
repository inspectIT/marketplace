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
import { Http, Headers } from "@angular/http";
export var GithubOAuthService = (function () {
    function GithubOAuthService(http, url) {
        this.http = http;
        this.url = url;
        this.authenticated = false;
        this.applicationUrl = url;
    }
    GithubOAuthService.prototype.isAuthenticated = function () {
        return this.authenticated;
    };
    /**
     * XHR to backend REST Api to authenticate user
     * <p/>
     * on success, get the user_id returned by browser
     */
    GithubOAuthService.prototype.login = function (userCredentials) {
        var _this = this;
        this.authenticated = false;
        var url = this.applicationUrl + "/user";
        var headers = new Headers();
        var credentialsBody = "name=" + userCredentials.username + "&password=" + userCredentials.password;
        headers.append('Content-Type', 'application/X-www-form-urlencoded');
        return new Promise(function (resolve) {
            _this.http.post(url, credentialsBody, { headers: headers }).subscribe(function (data) {
                console.log("post successfull");
                if (data.json().success) {
                    console.log('Login successfull. :::::' + data.json());
                    _this.userId = data.json().userId;
                    _this.authenticated = true;
                }
            }, function (error) {
                // todo add error handling
                console.log('error');
                _this.authenticated = false;
            }, function () {
                console.log('completed :: ' + _this.authenticated);
                resolve(true);
            });
        });
    };
    GithubOAuthService = __decorate([
        Injectable(), 
        __metadata('design:paramtypes', [Http, String])
    ], GithubOAuthService);
    return GithubOAuthService;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/auth/github.oauth.service.js.map