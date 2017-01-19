import { async, TestBed } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { OverviewComponent } from "./overview.component";
import { DynamicloaderComponent } from "../../components/dynamicloader/dynamicloader.component";
import { LimitCharactersToPipe } from "../../pipes/limit-characters-to.pipe";
import { FormatDownloadNumberPipe } from "../../pipes/format-download-number.pipe";
import { ItemDetailComponent } from "../item-detail/item-detail.component";
describe('OverviewComponent', function () {
    var component;
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [
                OverviewComponent,
                DynamicloaderComponent,
                ItemDetailComponent,
                LimitCharactersToPipe,
                FormatDownloadNumberPipe
            ],
            imports: [
                RouterTestingModule.withRoutes([
                    { path: 'details/1', component: ItemDetailComponent }
                ])
            ]
        });
    }));
    beforeEach(function () {
        fixture = TestBed.createComponent(OverviewComponent);
        component = fixture.componentInstance;
    });
    it('should create', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pages/overview/overview.component.spec.js.map