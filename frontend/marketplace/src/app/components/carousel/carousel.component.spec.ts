/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {TestBed, async, ComponentFixture} from "@angular/core/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {CarouselComponent} from "./carousel.component";
import {LimitCharactersToPipe} from "../../pipes/limit-characters-to.pipe";
import {FormatDownloadNumberPipe} from "../../pipes/format-download-number.pipe";
import {RemoveSpacesPipe} from "../../pipes/remove.spaces.pipe";
import {ItemDetailComponent} from "../../pages/item-detail/item-detail.component";
import {DashboardItemModel} from "../../pages/dashboard/model/dashboard.item.model";

describe('Component: Carousel', () => {
  let fixture: ComponentFixture<CarouselComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        CarouselComponent,
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
    fixture = TestBed.createComponent(CarouselComponent);
  }));

  it('should create an instance', () => {
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should render list with 3 a element; one "a" generated; two "a" for next and previous', async(() => {
    const element = fixture.nativeElement;
    const list: Array<DashboardItemModel> = [{
      id: "1",
      name: "123456789012345678901234567890",
      previewImage: "1",
      author: "Nik",
      rating: 4.1,
      numberDownloads: 123991231,
      creationDate: new Date(2017, 0, 22)
    }];
    fixture.componentInstance.itemList = list;
    fixture.detectChanges();
    expect(element.querySelectorAll('a').length).toBe(3);
  }));
});
