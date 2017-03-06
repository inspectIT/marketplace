/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Http, HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {AppComponent} from "./app.component";
import {ApiService} from "./services/api/api.service";
import {environment} from "../environments/environment";
import {FooterComponent} from "./components/footer/footer.component";
import {NavigationComponent} from "./components/navigation/navigation.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ErrorPageComponent} from "./pages/error-page/error-page.component";
import {LoginComponent} from "./components/login/login.component";
import {APP_ROUTES} from "./modules/routes/main.router.app";
import {CarouselComponent} from "./components/carousel/carousel.component";
import {FormatDownloadNumberPipe} from "./pipes/format-download-number.pipe";
import {LimitCharactersToPipe} from "./pipes/limit-characters-to.pipe";
import {SearchResultComponent} from "./pages/search-result/search-result.component";
import {ItemDetailComponent} from "./pages/item-detail/item-detail.component";
import {RemoveSpacesPipe} from "./pipes/remove.spaces.pipe";
import {OverviewComponent} from "./pages/overview/overview.component";
import {CommentPageComponent} from "./pages/comment-page/comment-page.component";
import {ProductPageComponent} from "./pages/product-page/product-page.component";
import {LogoutComponent} from "./components/logout/logout.component";
import {UserDetailComponent} from "./pages/user-detail/user-detail.component";
import {HelperService} from "./services/api/helper/helper.service";
import {ApiUserService} from "./services/api/api.user.service";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    FooterComponent,
    NavigationComponent,
    ProfileComponent,
    DashboardComponent,
    ErrorPageComponent,
    LoginComponent,
    CarouselComponent,
    FormatDownloadNumberPipe,
    LimitCharactersToPipe,
    SearchResultComponent,
    ItemDetailComponent,
    RemoveSpacesPipe,
    OverviewComponent,
    CommentPageComponent,
    ProductPageComponent,
    LogoutComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(APP_ROUTES)
  ],
  providers: [
    // if dev; change behavior
    {
      provide: 'IS_PROD',
      useValue: environment.production
    },
    {
      provide: 'APPLICATION_URL',
      useFactory: (IS_PROD) => IS_PROD ? '' : 'http://localhost:8080',
      deps: ['IS_PROD']
    },
    {
      provide: ApiService,
      useFactory: (IS_PROD, http, APPLICATION_URL, helperService) => /** IS_PROD ?: */ new ApiService(http, APPLICATION_URL, helperService),
      deps: ['IS_PROD', Http, 'APPLICATION_URL', HelperService]
    },
    {
      provide: ApiUserService,
      useFactory: (IS_PROD, http, APPLICATION_URL, helperService) => /** IS_PROD ?: */ new ApiUserService(http, APPLICATION_URL, helperService),
      deps: ['IS_PROD', Http, 'APPLICATION_URL', HelperService]
    },
    HelperService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
