import { TestBed, async } from "@angular/core/testing";
import { DashboardComponent } from "./dashboard.component";
import { CarouselComponent } from "../../components/carousel/carousel.component";
describe('Component: Dashboard', function () {
    var component;
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [
                DashboardComponent,
                CarouselComponent
            ]
        });
        TestBed.overrideComponent(CarouselComponent, {
            set: {
                template: "\n          <h2>fake</h2>\n        "
            }
        });
    }));
    beforeEach(function () {
        fixture = TestBed.createComponent(DashboardComponent);
        component = fixture.componentInstance;
    });
    it('should create an instance', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pages/dashboard/dashboard.component.spec.js.map