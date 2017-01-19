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
export var LimitCharactersToPipe = (function () {
    function LimitCharactersToPipe() {
    }
    /**
     *
     * @param value
     * @param maxChars
     * @param args
     * @returns {string}
     */
    LimitCharactersToPipe.prototype.transform = function (value, limit, args) {
        var trail = '...';
        return value.length > limit ? value.substring(0, limit - trail.length) + trail : value;
    };
    LimitCharactersToPipe = __decorate([
        Pipe({
            name: 'limitTo'
        }), 
        __metadata('design:paramtypes', [])
    ], LimitCharactersToPipe);
    return LimitCharactersToPipe;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pipes/limit-characters-to.pipe.js.map