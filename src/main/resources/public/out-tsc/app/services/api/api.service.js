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
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
export var ApiService = (function () {
    function ApiService(http, url) {
        this.http = http;
        this.url = url;
        this.applicationUrl = url;
    }
    ApiService.prototype.getDashboardCarouselItem = function (tag) {
        var _this = this;
        var url = this.applicationUrl + "/get/dashboard/simple/" + tag;
        // ...using get request
        return this.http.get(url)
            .map(function (res) { return _this.extractData(res); })
            .catch(function (error) { return _this.handleError(error); });
    };
    ApiService.prototype.extractData = function (data) {
        var body = data.json();
        return body || {};
    };
    ApiService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        var errMsg;
        if (error instanceof Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    };
    ApiService = __decorate([
        Injectable(), 
        __metadata('design:paramtypes', [Http, String])
    ], ApiService);
    return ApiService;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/api/api.service.js.map