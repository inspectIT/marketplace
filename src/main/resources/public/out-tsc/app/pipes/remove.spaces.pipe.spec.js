import { RemoveSpacesPipe } from "./remove.spaces.pipe";
describe('RemoveSpacesPipe', function () {
    var pipe = new RemoveSpacesPipe();
    it('create an instance', function () {
        expect(pipe).toBeTruthy();
    });
    it('remove whitespaces from string', function () {
        expect(pipe.transform("Lorem ipsum dolor sit amet")).toBe("Loremipsumdolorsitamet");
        expect(pipe.transform(" ")).toBe("");
        expect(pipe.transform(" 1 2 3 5")).toBe("1235");
    });
});
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/pipes/remove.spaces.pipe.spec.js.map