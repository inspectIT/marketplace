/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {Pipe, PipeTransform} from "@angular/core";
import {CarouselComponent} from "../components/carousel/carousel.component";
import {FormatNumberPurposeEnum} from "../enums/format-number-purpose-enum.enum";

@Pipe({
  name: 'formatNumber'
})
export class FormatDownloadNumberPipe implements PipeTransform {

  /**
   * This pipe transform high Number in readable format, e.g. 125.000.000 will be transformed in 125M.
   * also this pipes rounds the result. 23756 will be transformed to 24K
   *
   * @param value containing the original value
   * @param purpose contains why the number should be transformed, e.g. {@link CarouselComponent}
   * @param args
   * @returns {string}
   */
  transform(value: number, purpose?: FormatNumberPurposeEnum, args?: any): string {
    /**
     * if purpose was not entered, expect to minify number
     */
    if (FormatNumberPurposeEnum.minify == purpose || 1) {
      /**
       * make sure to round entered value, since decimals are possible
       */
      return this.minifyHighDownloadNumber(Math.round(value));
    }
  }

  private minifyHighDownloadNumber(value: number): string {
    const length: number = value.toString().length;
    if (length > 9) {
      return (value / 1000000000.0).toFixed(0) + "B"
    } else if (length > 6) {
      return (value / 1000000.0).toFixed(0) + "M"
    }
    if (length > 3) {
      return (value / 1000.0).toFixed(0) + "K"
    }
    return value.toFixed(0);
  }
}
