var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Pipe } from "@angular/core";
import { FormatNumberPurposeEnum } from "../enums/format-number-purpose-enum.enum";
export var FormatDownloadNumberPipe = (function () {
    function FormatDownloadNumberPipe() {
    }
    /**
     * This pipe transform high Number in readable format, e.g. 125.000.000 will be transformed in 125M.
     * also this pipes rounds the result. 23756 will be transformed to 24K
     *
     * @param value containing the original value
     * @param purpose contains why the number should be transformed, e.g. {@link CarouselComponent}
     * @returns {string}
     */
    FormatDownloadNumberPipe.prototype.transform = function (value, purpose, args) {
        /**
         * if purpose was not entered, expect to minify number
         */
        if (FormatNumberPurposeEnum.minify == purpose || 1) {
            /**
             * make sure to round entered value, since decimals are possible
             */
            return this.minifyHighDownloadNumber(Math.round(value));
        }
    };
    FormatDownloadNumberPipe.prototype.minifyHighDownloadNumber = function (value) {
        var length = value.toString().length;
        if (length > 9) {
            return (value / 1000000000.0).toFixed(0) + "B";
        }
        else if (length > 6) {
            return (value / 1000000.0).toFixed(0) + "M";
        }
        if (length > 3) {
            return (value / 1000.0).toFixed(0) + "K";
        }
        return value.toFixed(0);
    };
    FormatDownloadNumberPipe = __decorate([
        Pipe({
            name: 'formatNumber'
        }), 
        __metadata('design:paramtypes', [])
    ], FormatDownloadNumberPipe);
    return FormatDownloadNumberPipe;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pipes/format-download-number.pipe.js.map