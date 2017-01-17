/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {TestBed, async, ComponentFixture} from "@angular/core/testing";
import {ErrorPageComponent} from "./error-page.component";

describe('Component: ErrorPage', () => {

  let component: ErrorPageComponent;
  let fixture: ComponentFixture<ErrorPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ErrorPageComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create an instance', () => {
    expect(component).toBeTruthy();
  });

});
