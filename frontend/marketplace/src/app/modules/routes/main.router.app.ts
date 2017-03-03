/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
import {DashboardComponent} from "../../pages/dashboard/dashboard.component";
import {ProfileComponent} from "../../pages/profile/profile.component";
import {ErrorPageComponent} from "../../pages/error-page/error-page.component";
import {LoginComponent} from "../../components/login/login.component";
import {SearchResultComponent} from "../../pages/search-result/search-result.component";
import {ItemDetailComponent} from "../../pages/item-detail/item-detail.component";
import {OverviewComponent} from "../../pages/overview/overview.component";
import {CommentPageComponent} from "../../pages/comment-page/comment-page.component";
import {ProductPageComponent} from "../../pages/product-page/product-page.component";
import {LogoutComponent} from "../../components/logout/logout.component";
import {UserDetailComponent} from "../../pages/user-detail/user-detail.component";

/**
 * generate routes configuration for the marketplace
 * <p/>
 * simple const var containing the route configs
 */
export const APP_ROUTES = [
  {path: 'profile/:name', component: ProfileComponent},
  {path: 'user/:name', component: UserDetailComponent},
  {path: 'addProduct/:userId', component: ProductPageComponent},
  {path: 'search/:param', component: SearchResultComponent},
  {path: 'details/:id', component: ItemDetailComponent},
  {path: 'addComment/:productId/:userId', component: CommentPageComponent},
  {path: 'overview/:tag', component: OverviewComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: '', pathMatch: 'full', redirectTo: '/dashboard'},
  {path: '**', component: ErrorPageComponent}
];
