import { TestBed } from "@angular/core/testing";
import { AppComponent } from "./app.component";
describe('App: Marketplace', function () {
    var fixture;
    beforeEach(function () {
        TestBed.configureTestingModule({
            declarations: [AppComponent]
        });
        TestBed.overrideComponent(AppComponent, {
            set: {
                template: "\n          <h2>App works!</h2>\n        "
            }
        });
        fixture = TestBed.createComponent(AppComponent);
    });
    it('should create an instance', function () {
        var component = fixture.componentInstance;
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/app.component.spec.js.map