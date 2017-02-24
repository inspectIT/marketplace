/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ApiService} from "../../services/api/api.service";

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.scss']
})
export class ItemDetailComponent implements OnInit {

  constructor(private route: ActivatedRoute, private service: ApiService) {
  }

  ngOnInit() {
    const param: string = this.route.snapshot.params['id'];
    console.log("get product for id: " + param);
  }

}
