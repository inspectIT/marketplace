/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.9-SNAPSHOT
 */
import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'toDate'
})
export class FormatStringToDatePipe implements PipeTransform {

  /**
   * Simple transformation pipe to create a Date from String.
   *
   * @param value {@link any}
   * @param args {@link any]
   * @returns {Date}
   */
  transform(value: any, args?: any): any {
    return new Date(value);
  }

}
