import { Router } from "@angular/router";
import { TestBed, inject } from "@angular/core/testing";
import { AuthManagerService } from "./auth.manager.service";
xdescribe('Service: Auth Manager', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [
                Router,
                AuthManagerService
            ]
        });
    });
    it('should ...', inject([AuthManagerService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/auth/auth.manager.service.spec.js.map