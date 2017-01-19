import { TestBed, async } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { CarouselComponent } from "./carousel.component";
import { LimitCharactersToPipe } from "../../pipes/limit-characters-to.pipe";
import { FormatDownloadNumberPipe } from "../../pipes/format-download-number.pipe";
import { RemoveSpacesPipe } from "../../pipes/remove.spaces.pipe";
import { ItemDetailComponent } from "../../pages/item-detail/item-detail.component";
describe('Component: Carousel', function () {
    var fixture;
    beforeEach(async(function () {
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
                    { path: 'details/1', component: ItemDetailComponent }
                ])
            ],
        });
        fixture = TestBed.createComponent(CarouselComponent);
    }));
    it('should create an instance', function () {
        var component = fixture.componentInstance;
        expect(component).toBeTruthy();
    });
    it('should render list with 3 a element; one "a" generated; two "a" for next and previous', async(function () {
        var element = fixture.nativeElement;
        var list = [{
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
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/components/carousel/carousel.component.spec.js.map