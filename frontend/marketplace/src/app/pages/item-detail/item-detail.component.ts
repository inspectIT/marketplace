/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";
import {ApiUserService} from "../../services/api/api.user.service";

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.scss']
})
export class ItemDetailComponent implements OnInit {

  productDetail: any;
  isUserLoggedIn: boolean = false;
  productId: string;

  constructor(private route: ActivatedRoute, private userService: ApiUserService, private service: ApiService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['productId'];
    this.productId = param;
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

    this.userService.getUser().subscribe(
      data => {
        console.log("get user data: " + data);
        if (data != null) {
          this.isUserLoggedIn = true;
        }
      },
      err => {
        console.log("error: " + err);
        this.isUserLoggedIn = false;
      })
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

  /**
   * increment upload counter
   *
   * @since 1.1.1-SNAPSHOT
   */
  updateProductDownloads(): void {
    this.service.updateProductDownloadCounter(this.productId).subscribe(
      item => {
        this.productDetail = item; //Bind to view
        // download file
        window.location.href = `app/download/product/${this.productDetail.productId}`;
      },
      err => {
        // Log errors if any
        console.log(err);
      }
    );
  }

}
