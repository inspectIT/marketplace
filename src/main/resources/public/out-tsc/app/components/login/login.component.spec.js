import { TestBed, async } from "@angular/core/testing";
import { Http } from "@angular/http";
import { LoginComponent } from "./login.component";
import { GithubOAuthService } from "../../services/auth/github.oauth.service";
xdescribe('Component: Login', function () {
    var fixture;
    beforeEach(async(function () {
        TestBed.configureTestingModule({
            declarations: [LoginComponent],
            providers: [
                Http,
                GithubOAuthService
            ]
        });
        TestBed.overrideComponent(LoginComponent, {
            set: {
                template: "\n          <h2>Login works</h2>\n        "
            }
        });
        fixture = TestBed.createComponent(LoginComponent);
    }));
    it('should create an instance', function () {
        var component = fixture.componentInstance;
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/components/login/login.component.spec.js.map