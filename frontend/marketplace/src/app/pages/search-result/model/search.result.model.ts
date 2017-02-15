/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
import {DynamicLoaderSearchItemModel} from "./dynamic.loader.search.item.model";


export class SearchResultModel {

  totalElements: number;
  totalPages: number;
  numberOfElements: number;
  size: number;
  number: number;

  first: boolean;
  last: boolean;

  // result items
  content: Array<DynamicLoaderSearchItemModel>;


  toString(){
    return "number: " + this.number + " \tcontent: " + this.content;
  }
}
