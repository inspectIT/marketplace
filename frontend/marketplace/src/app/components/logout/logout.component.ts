/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ApiUserService} from "../../services/api/api.user.service";

/**
 * @deprecated - delete soon
 */
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router, private service: ApiUserService) {
  }

  ngOnInit() {

  }
}
