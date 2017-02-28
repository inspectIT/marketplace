/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router, private service: ApiService) {
  }

  ngOnInit() {
    console.log("logout and redirect to home");
    this.service.logout();

    this.redirectToHome();
  }

  private redirectToHome(): void {
    this.router.navigateByUrl('/');
  }
}
