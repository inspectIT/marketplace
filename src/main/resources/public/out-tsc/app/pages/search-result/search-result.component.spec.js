import { async, TestBed } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { SearchResultComponent } from "./search-result.component";
import { LimitCharactersToPipe } from "../../pipes/limit-characters-to.pipe";
import { FormatDownloadNumberPipe } from "../../pipes/format-download-number.pipe";
import { ItemDetailComponent } from "../item-detail/item-detail.component";
import { DynamicloaderComponent } from "../../components/dynamicloader/dynamicloader.component";
describe('SearchResultComponent', function () {
    var component;
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [
                SearchResultComponent,
                LimitCharactersToPipe,
                FormatDownloadNumberPipe,
                ItemDetailComponent,
                DynamicloaderComponent
            ],
            imports: [
                RouterTestingModule.withRoutes([
                    { path: 'details/1', component: ItemDetailComponent }
                ])
            ]
        });
    }));
    beforeEach(function () {
        fixture = TestBed.createComponent(SearchResultComponent);
        component = fixture.componentInstance;
    });
    it('should create', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pages/search-result/search-result.component.spec.js.map