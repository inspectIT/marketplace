/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Component, OnInit, Input} from "@angular/core";
import {DynamicLoaderModel} from "./model/dynamic.loader.model";

@Component({
  selector: 'app-dynamicloader',
  templateUrl: './dynamicloader.component.html',
  styleUrls: ['./dynamicloader.component.scss']
})
export class DynamicloaderComponent implements OnInit {

  /**
   * Contains the dashboard items, which will be rendered
   */
  @Input()
  itemList: DynamicLoaderModel;

  constructor() {
  }

  ngOnInit() {
  }

}
