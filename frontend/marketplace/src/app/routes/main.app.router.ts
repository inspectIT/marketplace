/**
 * @author Nikita Kolytschew
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */

import {Routes, RouterModule} from "@angular/router";
import {MainComponent} from "../landing-page/main/main.component";
import {LoginComponent} from "../login/login.component";
import {DashboardComponent} from "../dashboard-page/dashboard/dashboard.component";
import {AuthManagerService} from "../services/auth/auth.manager.service";

// Pages

/**
 * generate routes configuration for the marketplace
 * <p/>
 * simple const var containing the route configs
 */
export const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'landing', component: MainComponent },
  { path: 'login', component: LoginComponent, canActivate: [AuthManagerService] },
  { path: 'dashboard', component: DashboardComponent }
];


export const AppRouterProvider = RouterModule.forRoot(appRoutes);
