/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";
import {ApiService} from "../../services/api/api.service";
import {User} from "../../domain/user.domain.class";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  user: User;

  constructor(private router: Router, private service: ApiService) {
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

}
