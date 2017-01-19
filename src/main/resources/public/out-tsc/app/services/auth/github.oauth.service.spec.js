import { Http } from "@angular/http";
import { TestBed, inject } from "@angular/core/testing";
import { GithubOAuthService } from "./github.oauth.service";
xdescribe('Service: Github OAuth', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [
                GithubOAuthService,
                Http
            ]
        });
    });
    it('should ...', inject([GithubOAuthService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/auth/github.oauth.service.spec.js.map