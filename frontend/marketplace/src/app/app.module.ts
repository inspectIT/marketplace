/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.0-SNAPSHOT
 */
import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {Http, HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {AppComponent} from "./app.component";
import {GithubOAuthService} from "./services/auth/github.oauth.service";
import {AuthManagerService} from "./services/auth/auth.manager.service";
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
import {DynamicloaderComponent} from "./components/dynamicloader/dynamicloader.component";
import {OverviewComponent} from "./pages/overview/overview.component";

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
    DynamicloaderComponent,
    OverviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
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
      useFactory: (IS_PROD) => IS_PROD ? 'https://marketplace.inspectit.rocks' : 'http://localhost:8080',
      deps: ['IS_PROD']
    },
    // custom config for conditional DI
    {
      provide: GithubOAuthService,
      useFactory: (IS_PROD, http, APPLICATION_URL) => /** IS_PROD ?: */ new GithubOAuthService(http, APPLICATION_URL),
      deps: ['IS_PROD', Http, 'APPLICATION_URL']
    },
    {
      provide: ApiService,
      useFactory: (IS_PROD, http, APPLICATION_URL) => /** IS_PROD ?: */ new ApiService(http, APPLICATION_URL),
      deps: ['IS_PROD', Http, 'APPLICATION_URL']
    },
    AuthManagerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
