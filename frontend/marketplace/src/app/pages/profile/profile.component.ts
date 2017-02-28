/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router, ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user;

  authenticated: boolean;

  constructor(private route: ActivatedRoute, private router: Router,
    private service: ApiService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['name'];
    console.log("profile component user name: " + param);

    if(param == null){
      this.authenticated = false;
    } else {
      this.authenticated = true;
      this.service.getUser().subscribe(
        data => {
          this.user = data;
        },
        error => console.log(error)
      );
    }
  }
}
