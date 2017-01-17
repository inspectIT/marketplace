/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
import {Router} from "@angular/router";
import {TestBed, inject} from "@angular/core/testing";
import {AuthManagerService} from "./auth.manager.service";

xdescribe('Service: Auth Manager', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        Router,
        AuthManagerService
      ]
    });
  });

  it('should ...', inject([AuthManagerService], (service: AuthManagerService) => {
    expect(service).toBeTruthy();
  }));
});
