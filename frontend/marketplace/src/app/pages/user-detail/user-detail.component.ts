/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ApiUserService} from "../../services/api/api.user.service";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {

  user;

  authenticated: boolean;

  constructor(private route: ActivatedRoute, private service: ApiUserService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['name'];
    console.log("user detail component user name: " + param);

    this.service.getUserDetailByUserName(param).subscribe(
      data => {
        this.user = data;
        this.authenticated = true;
      },
      error => {
        console.log(error);
        this.authenticated = false;
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
