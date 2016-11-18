import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {Http, HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {AppComponent} from "./app.component";
import {NavigationBarComponent} from "./navigation-bar/navigation-bar.component";
import {SubscribeNewsletterComponent} from "./landing-page/subscribe-newsletter/subscribe-newsletter.component";
import {MarketplaceMainFeaturesComponent} from "./landing-page/marketplace-main-features/marketplace-main-features.component";
import {DashboardComponent} from "./dashboard-page/dashboard/dashboard.component";
import {LoginComponent} from "./login/login.component";
import {GithubOAuthService} from "./services/auth/github.oauth.service";
import {AuthManagerService} from "./services/auth/auth.manager.service";
import {ApiService} from "./services/api/api.service";
import {environment} from "../environments/environment";
import {AppRouterProvider} from "./routes/main.app.router";
import {MainComponent} from "./landing-page/main/main.component";

// custom components

// services

// environment

// custom routes

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    SubscribeNewsletterComponent,
    MarketplaceMainFeaturesComponent,
    DashboardComponent,
    LoginComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,

    AppRouterProvider
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
