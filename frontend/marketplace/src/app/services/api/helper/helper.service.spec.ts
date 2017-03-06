/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
import {TestBed, inject} from "@angular/core/testing";
import {HelperService} from "./helper.service";

describe('HelperServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HelperService]
    });
  });

  it('should ...', inject([HelperService], (service: HelperService) => {
    expect(service).toBeTruthy();
  }));
});
