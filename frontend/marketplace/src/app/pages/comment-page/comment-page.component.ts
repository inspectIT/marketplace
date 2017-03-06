/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, FormControl, Validators} from "@angular/forms";
import {ApiService} from "../../services/api/api.service";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import "rxjs/add/observable/of";
import "rxjs/add/observable/throw";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/do";
import "rxjs/add/operator/filter";
import "rxjs/add/operator/map";
import "rxjs/add/operator/switchMap";
import {PermissionModel} from "./model/permission.model";
import {ApiUserService} from "../../services/api/api.user.service";

@Component({
  selector: 'app-comment-page',
  templateUrl: './comment-page.component.html',
  styleUrls: ['./comment-page.component.scss']
})
export class CommentPageComponent implements OnInit {

  isPermitted: PermissionModel;
  hasError: boolean;
  userName: string;
  productId: string;
  ratingRangeArray: Array<number> = [];
  currentRating: number = 0;

  commentForm: FormGroup;

  ratingCtrl: FormControl;
  commentCtrl: FormControl;

  constructor(private route: ActivatedRoute, private router: Router, private service: ApiService, private userService: ApiUserService, builder: FormBuilder) {
    this.ratingCtrl = builder.control('', [Validators.required]);
    this.commentCtrl = builder.control('', [Validators.required, Validators.minLength(10)]);

    this.commentForm = builder.group({
      rating: this.ratingCtrl,
      comment: this.commentCtrl
    });

  }

  ngOnInit() {
    const productId: string = this.route.snapshot.params['productId'];
    console.log("comment component productId name: " + productId);
    this.productId = productId;

    this.userService.getUser().subscribe(
      data => {
        this.userName = data.userName;
        if(this.userName)
        this.populatePermission();
      },
      error => {
        this.hasError = error;
        console.log("get user error in  comment component: " + error);
      }
    );

    /**
     * if not permitted, populate data
     */
    this.ratingRangeArray = Array(10).fill(0).map((e, i) => i + 1);
  }

  updateRating(ratingValue: number): void {
    this.currentRating = ratingValue;
    this.ratingCtrl.setValue(this.currentRating);
  }

  saveComment(): void {
    this.service.persistComment(this.userName, this.productId, JSON.stringify(this.commentForm.value))
      .subscribe(data => {
        console.log("persist successful: " + data + " :: " + JSON.stringify(data));
        // redirect to product detail
        this.router.navigate(['/details', this.productId]);
      });
  }

  private populatePermission() {
    this.service.userHasPermissionToComment(this.userName, this.productId).subscribe(
      data => {
        this.isPermitted = data;
        console.log("isPermitted: " + this.isPermitted.ratingExists);
      },
      err => this.isPermitted = null
    );
  }
}
