/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  loggedIn: boolean = false;

  constructor() { }

  ngOnInit() {
  }

}
