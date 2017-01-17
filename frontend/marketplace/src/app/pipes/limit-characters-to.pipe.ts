/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'limitTo'
})
export class LimitCharactersToPipe implements PipeTransform {

  /**
   *
   * @param value
   * @param maxChars
   * @param args
   * @returns {string}
   */
  transform(value: any, limit: number, args?: any): any {
    const trail = '...';
    return value.length > limit ? value.substring(0, limit - trail.length) + trail : value;
  }

}
