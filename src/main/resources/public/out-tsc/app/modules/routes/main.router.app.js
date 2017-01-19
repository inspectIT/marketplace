import { DashboardComponent } from "../../pages/dashboard/dashboard.component";
import { ProfileComponent } from "../../pages/profile/profile.component";
import { ErrorPageComponent } from "../../pages/error-page/error-page.component";
import { LoginComponent } from "../../components/login/login.component";
import { AuthManagerService } from "../../services/auth/auth.manager.service";
import { SearchResultComponent } from "../../pages/search-result/search-result.component";
import { ItemDetailComponent } from "../../pages/item-detail/item-detail.component";
import { OverviewComponent } from "../../pages/overview/overview.component";
/**
 * generate routes configuration for the marketplace
 * <p/>
 * simple const var containing the route configs
 */
export var APP_ROUTES = [
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'search/:param', component: SearchResultComponent },
    { path: 'details/:id', component: ItemDetailComponent },
    { path: 'overview/:tag', component: OverviewComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'login', component: LoginComponent, canActivate: [AuthManagerService] },
    { path: '', component: DashboardComponent },
    { path: '**', component: ErrorPageComponent }
];
//# sourceMappingURL=C:/work/projects/git/marketplace/frontend/marketplace/src/app/modules/routes/main.router.app.js.map