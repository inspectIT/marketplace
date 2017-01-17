/* tslint:disable:no-unused-variable */
/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {RemoveSpacesPipe} from "./remove.spaces.pipe";

describe('RemoveSpacesPipe', () => {

  const pipe = new RemoveSpacesPipe();

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('remove whitespaces from string', () => {
    expect(pipe.transform("Lorem ipsum dolor sit amet")).toBe("Loremipsumdolorsitamet");
    expect(pipe.transform(" ")).toBe("");
    expect(pipe.transform(" 1 2 3 5")).toBe("1235");
  });

});
