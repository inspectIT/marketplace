/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {LimitCharactersToPipe} from "./limit-characters-to.pipe";

describe('LimitCharactersToPipe', () => {

  const pipe = new LimitCharactersToPipe();

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('limit text Lorem ipsum dolor sit amet, to Lorem ips...', () => {
    expect(pipe.transform("Lorem ipsum dolor sit amet,", 12)).toBe("Lorem ips...");
  });

  it('limit "Lorem ipsum" placeholder texts', () => {
    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 1))
      .toBe("...");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 122))
      .toBe("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore mag...");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 255))
      .toBe("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 5))
      .toBe("Lo...");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 3))
      .toBe("...");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", 2))
      .toBe("...");

    expect(pipe.transform("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.", -1))
      .toBe("...");
  });

});
