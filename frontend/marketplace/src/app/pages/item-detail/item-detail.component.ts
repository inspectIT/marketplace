/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.scss']
})
export class ItemDetailComponent implements OnInit {

  productDetail: any;

  constructor(private route: ActivatedRoute, private service: ApiService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['id'];
    console.log("get product for id: " + param);

    this.service.getProductDetailById(param).subscribe(
      item => {
        this.productDetail = item; //Bind to view
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

  /**
   * return an array to iterate over.
   *
   * @param value{@link number} determine the size of the array
   * @returns {any[]} generated array
   * @since 1.1.0-SNAPSHOT
   */
  getPopulatedArray(value: number): Array<any> {
    return Array(value).fill(0);
  }

}
