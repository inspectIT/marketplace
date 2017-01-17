/* tslint:disable:no-unused-variable */
import {TestBed, ComponentFixture} from "@angular/core/testing";
import {AppComponent} from "./app.component";

describe('App: Marketplace', () => {
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent]
    });
    TestBed.overrideComponent(AppComponent, {
      set: {
        template: `
          <h2>App works!</h2>
        `
      }
    });
    fixture = TestBed.createComponent(AppComponent);
  });

  it('should create an instance', () => {
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
