/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {TestBed, async, ComponentFixture} from "@angular/core/testing";
import {DashboardComponent} from "./dashboard.component";
import {CarouselComponent} from "../../components/carousel/carousel.component";

describe('Component: Dashboard', () => {

  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        DashboardComponent,
        CarouselComponent
      ]
    });
    TestBed.overrideComponent(CarouselComponent, {
      set: {
        template: `
          <h2>fake</h2>
        `
      }
    })
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
  });

  it('should create an instance', () => {
    expect(component).toBeTruthy();
  });

});
