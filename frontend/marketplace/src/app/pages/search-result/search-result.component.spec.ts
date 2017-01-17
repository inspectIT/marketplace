/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {SearchResultComponent} from "./search-result.component";
import {LimitCharactersToPipe} from "../../pipes/limit-characters-to.pipe";
import {FormatDownloadNumberPipe} from "../../pipes/format-download-number.pipe";
import {ItemDetailComponent} from "../item-detail/item-detail.component";
import {DynamicloaderComponent} from "../../components/dynamicloader/dynamicloader.component";

describe('SearchResultComponent', () => {
  let component: SearchResultComponent;
  let fixture: ComponentFixture<SearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SearchResultComponent,
        LimitCharactersToPipe,
        FormatDownloadNumberPipe,
        ItemDetailComponent,
        DynamicloaderComponent
      ],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'details/1', component: ItemDetailComponent}
        ])
      ]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
