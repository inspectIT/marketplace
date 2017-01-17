/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/map");
require("rxjs/add/operator/catch");
// custom
var LoginComponent = (function () {
    function LoginComponent(service, router, http) {
        this.service = service;
        this.router = router;
        this.http = http;
        this.user = { username: '', password: '' };
        this.awesomo = [];
    }
    LoginComponent.prototype.login = function (credentials) {
        var url = 'http://localhost:8080/login/github';
        var options = 'left=100,top=10,width=400,height=500';
        window.open(url, "nameHere", options);
        /*
            let response = this.http.get(url)
              .map(this.extractData)
              .catch(this.handleError);
        
            let subscribed = response.subscribe(
              data => console.log(data),
              error => console.log(error)
            );
        */
    };
    LoginComponent.prototype.getUser = function () {
        var _this = this;
        var url = 'http://localhost:8080/user';
        var response = this.http.get(url)
            .map(this.extractData)
            .catch(this.handleError);
        response.subscribe(function (data) {
            console.log(data);
            _this.awesomo = data;
        }, function (error) { return console.log(error); });
    };
    LoginComponent.prototype.extractData = function (data) {
        console.log("get successfull; pass json: " + data);
        var body = data.json();
        return body || {};
    };
    LoginComponent.prototype.handleError = function (error) {
        var errMsg;
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable_1.Observable.throw(errMsg);
    };
    /**
     * @override
     */
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.navigateBack = function () {
        window.history.back();
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.scss']
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
