import { TestBed, inject } from "@angular/core/testing";
import { Http } from "@angular/http";
import { ApiService } from "./api.service";
xdescribe('Service: Api', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [
                ApiService,
                Http
            ]
        });
    });
    it('should ...', inject([ApiService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/services/api/api.service.spec.js.map