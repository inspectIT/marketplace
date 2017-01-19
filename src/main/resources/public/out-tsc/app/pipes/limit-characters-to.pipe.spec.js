import { LimitCharactersToPipe } from "./limit-characters-to.pipe";
describe('LimitCharactersToPipe', function () {
    var pipe = new LimitCharactersToPipe();
    it('create an instance', function () {
        expect(pipe).toBeTruthy();
    });
    it('limit text Lorem ipsum dolor sit amet, to Lorem ips...', function () {
        expect(pipe.transform("Lorem ipsum dolor sit amet,", 12)).toBe("Lorem ips...");
    });
    it('limit "Lorem ipsum" placeholder texts', function () {
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
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pipes/limit-characters-to.pipe.spec.js.map