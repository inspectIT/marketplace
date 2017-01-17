/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {OverviewComponent} from "./overview.component";
import {DynamicloaderComponent} from "../../components/dynamicloader/dynamicloader.component";
import {LimitCharactersToPipe} from "../../pipes/limit-characters-to.pipe";
import {FormatDownloadNumberPipe} from "../../pipes/format-download-number.pipe";
import {ItemDetailComponent} from "../item-detail/item-detail.component";

describe('OverviewComponent', () => {
  let component: OverviewComponent;
  let fixture: ComponentFixture<OverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        OverviewComponent,
        DynamicloaderComponent,
        ItemDetailComponent,
        LimitCharactersToPipe,
        FormatDownloadNumberPipe
      ],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'details/1', component: ItemDetailComponent}
        ])
      ]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OverviewComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
