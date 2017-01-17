/* tslint:disable:no-unused-variable */
import {TestBed, inject} from "@angular/core/testing";
import {Http} from "@angular/http";
import {ApiService} from "./api.service";

xdescribe('Service: Api', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ApiService,
        Http
      ]
    });
  });

  it('should ...', inject([ApiService], (service: ApiService) => {
    expect(service).toBeTruthy();
  }));
});
