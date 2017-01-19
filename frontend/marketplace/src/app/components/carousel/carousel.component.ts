/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit, Input} from "@angular/core";
import {DashboardItemModel} from "../../pages/dashboard/model/dashboard.item.model";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {

  /**
   * Contains the dashboard items, which will be rendered
   */
  itemList: Array<DashboardItemModel>;

  @Input()
  title: string;

  @Input()
  tag: string;

  // helper
  itemsToDisplay: number;

  numberOfCarousel: number;
  carouselItemList: Array<Array<DashboardItemModel>> = [];

  /**
   *
   * @param maxItemsToDisplay optional parameter, to indicate the carousel size. Default: 6
   */
  constructor(private service: ApiService) {
    this.itemsToDisplay = 6;
  }

  /**
   * create and initialize helper vars for carousel
   * not refactored yet, to keep it readable
   * <p/>
   * <h2>Explanation</h2>
   * This component renders a carousel representation of the items.
   * In favour of Bootstrap and jQuery, we decided against an own implementation.
   * To make the Bootstrap multi-frame carousel solution work, you have to group a total of, for example 6, items.
   * To render the next container, you have another group of 6 items, and so on.
   * Because it's currently (angular-cli: 1.0.0-beta.21) not possible to define start and end values for an for-iteration
   * within the template, we had to partition the given {@link DashboardModel}.
   * <p/>
   * First get the total length of the given {@link DashboardItemModel} Array.
   * Then calculate the # of container you have to build for the carousel.
   * After that, you have to partition the {@link DashboardItemModel} Array and add the smaller arrays to the multi array carouselItemList.
   * This will result in having following array:
   * <pre>
   *     0: [item1], [item2], [item3]..[item_n]
   *     1: [item1], [item2], [item3]..[item_n]
   *     2: [item1], [item2], [item3]..[item_n]
   * </pre>
   * This carouselItemList will be used to generate the corresponding carousel within the template
   *
   * @since 1.0.5-SNAPSHOT
   */
  ngOnInit() {
    console.log("init for : " + this.tag);
    this.service.getDashboardCarouselItem(this.tag).subscribe(
      items => {
        this.itemList = items; //Bind to view
        console.log(" dashboard items: " + items);
        const size = this.itemList.length;
        this.numberOfCarousel = Math.ceil(size / this.itemsToDisplay);

        for (let i = 0; i < this.numberOfCarousel; i++) {
          // check if array.length is reached
          const begin = i * this.itemsToDisplay;
          const limit = ((i + 1) * this.itemsToDisplay) > size ? size : ((i + 1) * this.itemsToDisplay);

          // slice elements of original array an safe in tmpList
          const tmpArray: Array<DashboardItemModel> = this.itemList.slice(begin, limit);

          // safe tmp Array in carousel array
          this.carouselItemList.push(tmpArray);
        }
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

}
