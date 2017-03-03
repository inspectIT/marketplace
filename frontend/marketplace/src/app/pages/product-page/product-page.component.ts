/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Http} from "@angular/http";
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

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {

  username: string;

  readonly limitToList: Array<string> = ["JEE", 'Spring', 'JPA', 'Hibernate'];
  limitToArray: Array<string> = [];

  // use array, since multi file uploads are possible
  imagesToUpload: Array<File> = [];
  productsToUpload: Array<File> = [];

  productForm: FormGroup;

  productNameCtrl: FormControl;
  descriptionCtrl: FormControl;
  previewImageCtrl: FormControl;
  productItemCtrl: FormControl;

  constructor(private route: ActivatedRoute, private http: Http, private service: ApiService, builder: FormBuilder) {
    this.productNameCtrl = builder.control('', [Validators.required, Validators.minLength(3)]);
    this.descriptionCtrl = builder.control('', [Validators.required, Validators.minLength(5)]);
    this.productItemCtrl = builder.control('', [Validators.required]);
    this.previewImageCtrl = builder.control('', [Validators.required]);
    this.productItemCtrl = builder.control('', [Validators.required]);

    this.productForm = builder.group({
      productName: this.productNameCtrl,
      description: this.descriptionCtrl,
      previewImage: this.previewImageCtrl,
      productItem: this.productItemCtrl
    });

  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['name'];
    console.log("product component user name: " + param);
    this.username = param;
  }

  /**
   * populate limit to array with values and request list update from api.
   * reset paging, because of order and new objects.
   *
   * @param limitToValue {@link string}
   */
  updateLimitToArray(limitToValue: string) {
    console.log("value : " + limitToValue + " :: " + this.limitToArray);
    if (this.limitToArray.indexOf(limitToValue) === -1) {
      this.limitToArray.push(limitToValue);
    } else {
      this.limitToArray.splice(this.limitToArray.indexOf(limitToValue), 1);
    }
  }

  saveProduct(): void {
    console.log(this.productForm.value);
    this.upload();
  }

  /**
   * use simple mime type checking, without check on server.
   * assert that images are only "image/jpeg" and product are only "text/xml".
   * simple file ending check is enough for prototype.
   *
   * @param fileInput {@link any}
   */
  checkAndAddImageFileOnChangeEvent(fileInput: any) {
    const fileArray = <Array<File>> fileInput.target.files;

    console.log("checkAndAddImageFileOnChangeEvent" + fileArray);
    // check if array contains file
    for (var i = 0; i < fileArray.length; i++) {
      console.log(fileArray[i].type + " :: " + fileArray[i].type === "image/jpeg");
      if (fileArray[i].type === "image/jpeg") {
        console.log("checkAndAddImageFileOnChangeEvent  :: true");
        this.imagesToUpload.push(fileArray[i])
      } else {
        this.previewImageCtrl.setValue('');
      }
    }
  }

  /**
   * use simple mime type checking, without check on server.
   * assert that images are only "image/jpeg" and product are only "text/xml".
   * simple file ending check is enough for prototype.
   *
   * @param fileInput {@link any}
   */
  checkAndAddXmlFileOnChangeEvent(fileInput: any) {
    const fileArray = <Array<File>> fileInput.target.files;

    console.log("checkAndAddXmlFileOnChangeEvent" + fileArray);
    // check if array contains file
    for (var i = 0; i < fileArray.length; i++) {
      console.log(fileArray[i].type + " :: " + fileArray[i].type === "text/xml");
      if (fileArray[i].type === "text/xml") {
        console.log("checkAndAddXmlFileOnChangeEvent  :: true");
        this.productsToUpload.push(fileArray[i])
      } else {
        this.productItemCtrl.setValue('');
      }
    }
  }

  reset(): void {
    this.productNameCtrl.setValue('');
    this.descriptionCtrl.setValue('');
    this.previewImageCtrl.setValue('');
    this.productItemCtrl.setValue('');
  }

  upload() {
    this.service.persistProduct(this.username, this.imagesToUpload, this.productsToUpload, JSON.stringify(this.productNameCtrl.value), JSON.stringify(this.descriptionCtrl.value), this.limitToArray)
      .subscribe(() => console.log("login successfull"));
  }

}
