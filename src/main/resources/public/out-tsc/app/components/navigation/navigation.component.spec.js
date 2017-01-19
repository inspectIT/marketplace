import { TestBed, async } from "@angular/core/testing";
import { FormsModule } from "@angular/forms";
import { NavigationComponent } from "./navigation.component";
import { RouterTestingModule } from "@angular/router/testing";
import { ItemDetailComponent } from "../../pages/item-detail/item-detail.component";
describe('Component: Navigation', function () {
    var component;
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [
                NavigationComponent,
                ItemDetailComponent
            ],
            imports: [
                FormsModule,
                RouterTestingModule.withRoutes([
                    { path: 'details/1', component: ItemDetailComponent }
                ])
            ]
        }).compileComponents();
    }));
    beforeEach(function () {
        fixture = TestBed.createComponent(NavigationComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create an instance', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/components/navigation/navigation.component.spec.js.map