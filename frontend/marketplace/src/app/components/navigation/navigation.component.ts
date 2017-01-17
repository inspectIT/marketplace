/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import "rxjs/add/operator/debounceTime";
import "rxjs/add/operator/distinctUntilChanged";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  searchForItem(param: string): void {
    console.log(param);
    this.router.navigate(['/search', param]);
  }
}
