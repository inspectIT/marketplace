/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'removeSpaces'
})
export class RemoveSpacesPipe implements PipeTransform {

  /**
   * Simple Trim Pipe. Remove whitespaces from String.
   * @param value
   * @returns string
   */
  transform(value: string): string {
    if (!value) {
      return '';
    }
    return value.replace(/[\s]/g, '');
  }
}
