var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from "@angular/core";
import { ApiService } from "../../services/api/api.service";
export var CarouselComponent = (function () {
    /**
     *
     * @param maxItemsToDisplay optional parameter, to indicate the carousel size. Default: 6
     */
    function CarouselComponent(service) {
        this.service = service;
        this.carouselItemList = [];
        this.itemsToDisplay = 6;
    }
    /**
     * create and initialize helper vars for carousel
     * not refactored yet, to keep it readable
     * <p/>
     * <h2>Explanation</h2>
     * This component renders a carousel representation of the items.
     * In favour of Bootstrap and jQuery, we decided against an own implementation.
     * To make the Bootstrap multi-frame carousel solution work, you have to group a total of, for example 6, items.
     * To render the next container, you have another group of 6 items, and so on.
     * Because it's currently (angular-cli: 1.0.0-beta.21) not possible to define start and end values for an for-iteration
     * within the template, we had to partition the given {@link DashboardModel}.
     * <p/>
     * First get the total length of the given {@link DashboardItemModel} Array.
     * Then calculate the # of container you have to build for the carousel.
     * After that, you have to partition the {@link DashboardItemModel} Array and add the smaller arrays to the multi array carouselItemList.
     * This will result in having following array:
     * <pre>
     *     0: [item1], [item2], [item3]..[item_n]
     *     1: [item1], [item2], [item3]..[item_n]
     *     2: [item1], [item2], [item3]..[item_n]
     * </pre>
     * This carouselItemList will be used to generate the corresponding carousel within the template
     *
     * @since 1.0.5-SNAPSHOT
     */
    CarouselComponent.prototype.ngOnInit = function () {
        var _this = this;
        console.log("init for : " + this.tag);
        this.service.getDashboardCarouselItem(this.tag).subscribe(function (items) {
            _this.itemList = items; //Bind to view
            console.log(" dashboard items: " + items);
            var size = _this.itemList.length;
            _this.numberOfCarousel = Math.ceil(size / _this.itemsToDisplay);
            for (var i = 0; i < _this.numberOfCarousel; i++) {
                // check if array.length is reached
                var begin = i * _this.itemsToDisplay;
                var limit = ((i + 1) * _this.itemsToDisplay) > size ? size : ((i + 1) * _this.itemsToDisplay);
                // slice elements of original array an safe in tmpList
                var tmpArray = _this.itemList.slice(begin, limit);
                // safe tmp Array in carousel array
                _this.carouselItemList.push(tmpArray);
            }
        }, function (err) {
            // Log errors if any
            console.log(err);
        });
    };
    __decorate([
        Input(), 
        __metadata('design:type', String)
    ], CarouselComponent.prototype, "title", void 0);
    __decorate([
        Input(), 
        __metadata('design:type', String)
    ], CarouselComponent.prototype, "tag", void 0);
    CarouselComponent = __decorate([
        Component({
            selector: 'app-carousel',
            templateUrl: './carousel.component.html',
            styleUrls: ['./carousel.component.scss']
        }), 
        __metadata('design:paramtypes', [ApiService])
    ], CarouselComponent);
    return CarouselComponent;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/components/carousel/carousel.component.js.map