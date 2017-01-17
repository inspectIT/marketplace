/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {TestBed, async, ComponentFixture} from "@angular/core/testing";
import {FormsModule} from "@angular/forms";
import {NavigationComponent} from "./navigation.component";
import {RouterTestingModule} from "@angular/router/testing";
import {ItemDetailComponent} from "../../pages/item-detail/item-detail.component";

describe('Component: Navigation', () => {

  let component: NavigationComponent;
  let fixture: ComponentFixture<NavigationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        NavigationComponent,
        ItemDetailComponent
      ],
      imports: [
        FormsModule,
        RouterTestingModule.withRoutes([
          {path: 'details/1', component: ItemDetailComponent}
        ])
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create an instance', () => {
    expect(component).toBeTruthy();
  });
});
