/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {DynamicloaderComponent} from "./dynamicloader.component";
import {LimitCharactersToPipe} from "../../pipes/limit-characters-to.pipe";
import {FormatDownloadNumberPipe} from "../../pipes/format-download-number.pipe";
import {DynamicLoaderModelItemModel, DynamicLoaderModel} from "./model/dynamic.loader.model";
import {RemoveSpacesPipe} from "../../pipes/remove.spaces.pipe";
import {ItemDetailComponent} from "../../pages/item-detail/item-detail.component";

describe('DynamicloaderComponent', () => {
  let fixture: ComponentFixture<DynamicloaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        DynamicloaderComponent,
        LimitCharactersToPipe,
        FormatDownloadNumberPipe,
        RemoveSpacesPipe,
        ItemDetailComponent
      ],
      imports: [
        RouterTestingModule.withRoutes([
          {path: 'details/1', component: ItemDetailComponent}
        ])
      ],
    });
    fixture = TestBed.createComponent(DynamicloaderComponent);
  }));

  it('should create an instance', () => {
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should render 1 ul', async(() => {
    const element = fixture.nativeElement;
    const list: Array<DynamicLoaderModelItemModel> = [{
      id: 1,
      name: "123456789012345678901234567890",
      thumbnailImage: "1",
      author: "Nik",
      rating: 4.1,
      numberDownloads: 123991231,
      creationDate: new Date(2017, 0, 22)
    }];
    fixture.componentInstance.itemList = new DynamicLoaderModel("something", list);
    fixture.detectChanges();
    expect(element.querySelectorAll('ul').length).toBe(1);
  }));
});
