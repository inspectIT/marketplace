/* tslint:disable:no-unused-variable */

import {TestBed, inject} from "@angular/core/testing";
import {GithubOAuthService} from "./github.oauth.service";

describe('Service: Github OAuth', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GithubOAuthService]
    });
  });

  it('should ...', inject([GithubOAuthService], (service: GithubOAuthService) => {
    expect(service).toBeTruthy();
  }));
});
