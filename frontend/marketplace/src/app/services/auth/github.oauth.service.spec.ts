/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
import {Http} from "@angular/http";
import {TestBed, inject} from "@angular/core/testing";
import {GithubOAuthService} from "./github.oauth.service";

xdescribe('Service: Github OAuth', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        GithubOAuthService,
        Http
      ]
    });
  });

  it('should ...', inject([GithubOAuthService], (service: GithubOAuthService) => {
    expect(service).toBeTruthy();
  }));
});
