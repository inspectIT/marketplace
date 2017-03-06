/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
import {TestBed, inject} from "@angular/core/testing";
import {ApiUserService} from "./api.user.service";

describe('Api.UserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApiUserService]
    });
  });

  it('should ...', inject([ApiUserService], (service: ApiUserService) => {
    expect(service).toBeTruthy();
  }));
});
