/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {ComponentFixture, TestBed, async} from "@angular/core/testing";
import {Http} from "@angular/http";
import {LoginComponent} from "./login.component";

xdescribe('Component: Login', () => {
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        Http
      ]
    });

    TestBed.overrideComponent(LoginComponent, {
      set: {
        template: `
          <h2>Login works</h2>
        `
      }
    });

    fixture = TestBed.createComponent(LoginComponent);
  }));

  it('should create an instance', () => {
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
