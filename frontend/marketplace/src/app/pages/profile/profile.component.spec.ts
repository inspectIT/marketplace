/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {TestBed, async, ComponentFixture} from "@angular/core/testing";
import {ProfileComponent} from "./profile.component";

describe('Component: Profile', () => {

  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ProfileComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create an instance', () => {
    expect(component).toBeTruthy();
  });
});
