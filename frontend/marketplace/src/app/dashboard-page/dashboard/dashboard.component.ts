/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Component, OnInit} from "@angular/core";
import {ApiService} from "../../services/api/api.service";

// custom

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  dashboard: any = [];

  constructor(private service: ApiService) {
  }

  getSimpleDashboardOVerview(): any {
    let errorMessage;

    this.service.getDashboardOverview().subscribe(
      dashboard => this.dashboard = dashboard,
      error => errorMessage = <any>error);
    console.log(this.dashboard + " :: " + errorMessage)

    return this.dashboard;
  }

  /**
   * @override
   */
  ngOnInit() {
    this.getSimpleDashboardOVerview();
  }

  navigateBack(): void {
    window.history.back();
  }

}
