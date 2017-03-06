/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import {User} from "../../domain/user.domain.class";
import {ApiUserService} from "../../services/api/api.user.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  user: User;

  constructor(private router: Router, private service: ApiUserService) {
  }

  ngOnInit(): void {
    this.getUser();
  }

  searchForItem(param: string): void {
    console.log(`NavigationComponent:searchForItem - param: ${param}`);
    this.router.navigate(['/search', param]);
  }

  getUser() {
    this.service.getUser().subscribe(
      data => {
        this.user = data;
      },
      error => console.log(error)
    );
  }

  logout() {
    console.log("logout and redirect to home");
    this.service.logout().subscribe(
      () => {
        console.log("logout successful.\ncallback: ");
        this.user = null;
        this.router.navigateByUrl('/');
      },
      err => {
        console.log("error:" + err);
        this.user = null;
        this.router.navigateByUrl('/');
      }
    );
  }

}
