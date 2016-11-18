/* tslint:disable:no-unused-variable */

import {TestBed, inject} from "@angular/core/testing";
import {AuthManagerService} from "./auth.manager.service";

describe('Service: Auth Manager', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthManagerService]
    });
  });

  it('should ...', inject([AuthManagerService], (service: AuthManagerService) => {
    expect(service).toBeTruthy();
  }));
});
