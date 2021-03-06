/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {FormatDownloadNumberPipe} from "./format-download-number.pipe";

describe('Pipe: FormatDownloadNumberPipe', () => {
  const pipe = new FormatDownloadNumberPipe();

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('transforms high numbers to readable numbers', () => {
    expect(pipe.transform(123456789012)).toBe("123B");
    expect(pipe.transform(123456789)).toBe("123M");
    expect(pipe.transform(23545678)).toBe("24M");
    expect(pipe.transform(123456)).toBe("123K");
    expect(pipe.transform(23756)).toBe("24K");
    expect(pipe.transform(4556)).toBe("5K");
    expect(pipe.transform(4256)).toBe("4K");
    expect(pipe.transform(789)).toBe("789");
  });

  it('transform high float numbers to readable numbers', () => {
    expect(pipe.transform(123456789.225)).toBe("123M");
    expect(pipe.transform(123.225)).toBe("123");
  });

});


