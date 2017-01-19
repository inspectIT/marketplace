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
export var RemoveSpacesPipe = (function () {
    function RemoveSpacesPipe() {
    }
    /**
     * Simple Trim Pipe. Remove whitespaces from String.
     * @param value
     * @returns string
     */
    RemoveSpacesPipe.prototype.transform = function (value) {
        if (!value) {
            return '';
        }
        return value.replace(/[\s]/g, '');
    };
    RemoveSpacesPipe = __decorate([
        Pipe({
            name: 'removeSpaces'
        }), 
        __metadata('design:paramtypes', [])
    ], RemoveSpacesPipe);
    return RemoveSpacesPipe;
}());
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pipes/remove.spaces.pipe.js.map