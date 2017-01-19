import { TestBed, async } from "@angular/core/testing";
import { ErrorPageComponent } from "./error-page.component";
describe('Component: ErrorPage', function () {
    var component;
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [ErrorPageComponent]
        }).compileComponents();
    }));
    beforeEach(function () {
        fixture = TestBed.createComponent(ErrorPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create an instance', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pages/error-page/error-page.component.spec.js.map