var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import "rxjs/add/operator/switchMap";
export var SearchResultComponent = (function () {
    function SearchResultComponent(route, router) {
        this.route = route;
        this.router = router;
    }
    SearchResultComponent.prototype.ngOnInit = function () {
        var param = +this.route.snapshot.params['param'];
        console.log("search result component search term: " + param);
        this.setFeaturedList();
    };
    SearchResultComponent.prototype.setFeaturedList = function () {
        this.itemList = [
            { id: "1", name: "123456789012345678901234567890", previewImage: "1", author: "Nik", rating: 4.1, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22) },
            { id: "2", name: "diese überschrift besteht aus 41 zeichen", previewImage: "2", author: "Nik", rating: 3.7, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22) },
            { id: "3", name: "31 Zeichen für diese überschrift", previewImage: "3", author: "Nik", rating: 2.7, numberDownloads: 12391231, creationDate: new Date(2017, 0, 22) },
            { id: "4", name: "Schiffatsfährenkapitän", previewImage: "4", author: "Nik", rating: 1.7, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22) },
            { id: "5", name: "Schiffatsfährenk", previewImage: "5", author: "Nik", rating: 4.0, numberDownloads: 1239991231, creationDate: new Date(2017, 0, 22) },
            { id: "6", name: "16 Zeichen Limit", previewImage: "6", author: "Nik", rating: 4.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22) },
            { id: "7", name: "15 Zeichen okay", previewImage: "7", author: "Nik", rating: 1.5, numberDownloads: 1231231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "14 Zeichen gut", previewImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "16 Limit Zeichen", previewImage: "8", author: "Nik", rating: 1.3, numberDownloads: 123991231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "15 okay Zeichen", previewImage: "10", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "14 gut Zeichen", previewImage: "11", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 12", previewImage: "12", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 13", previewImage: "13", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 14", previewImage: "14", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 15", previewImage: "15", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 16", previewImage: "16", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 17", previewImage: "17", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 18", previewImage: "18", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 19", previewImage: "19", author: "Nik", rating: 3.3, numberDownloads: 1231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 20", previewImage: "20", author: "Nik", rating: 4.3, numberDownloads: 1239231, creationDate: new Date(2017, 0, 22) },
            { id: "8", name: "feature 21", previewImage: "21", author: "Nik", rating: 5.0, numberDownloads: 12, creationDate: new Date(2017, 0, 22) },
        ];
    };
    SearchResultComponent = __decorate([
        Component({
            selector: 'app-search-result',
            templateUrl: './search-result.component.html',
            styleUrls: ['./search-result.component.scss']
        }), 
        __metadata('design:paramtypes', [ActivatedRoute, Router])
    ], SearchResultComponent);
    return SearchResultComponent;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pages/search-result/search-result.component.js.map