import {MarketplacePage} from "./app.po";

describe('marketplace App', ()=> {
  let page: MarketplacePage;

  beforeEach(() => {
    page = new MarketplacePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
