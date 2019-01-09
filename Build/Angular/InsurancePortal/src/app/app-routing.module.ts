import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { RegisterComponent } from './register/register.component';
import { MenuComponent } from 'src/app/menu/menu.component';
import { LogOutComponent } from './log-out/log-out.component';
import {BuypolicyComponent} from './buypolicy/buypolicy.component';
import { QueryComponent } from './query/query.component';
import { ViewQueryComponent } from './view-query/view-query.component';
import {LoginRouteGuard} from './service/loginGuard.service';
import { ViewpolicyComponent } from './viewpolicy/viewpolicy.component';
import { RenewpolicyComponent } from './renewpolicy/renewpolicy.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'admin', component: MenuComponent, canActivate: [LoginRouteGuard]  },
  { path: 'customer', component: MenuComponent, canActivate: [LoginRouteGuard]  },
  { path: 'manager', component: MenuComponent, canActivate: [LoginRouteGuard]  },
  { path: 'operations', component: MenuComponent, canActivate: [LoginRouteGuard]  },
  { path: 'buypolicy', component: BuypolicyComponent, canActivate: [LoginRouteGuard]  },
  { path: 'viewpolicy', component: ViewpolicyComponent, canActivate: [LoginRouteGuard]  },
  { path: 'renewpolicy', component: RenewpolicyComponent, canActivate: [LoginRouteGuard]  },
  { path: 'newclaim', component: BuypolicyComponent, canActivate: [LoginRouteGuard]  },
  { path: 'viewclaim', component: BuypolicyComponent, canActivate: [LoginRouteGuard]  },
  { path: 'addquery', component: QueryComponent, canActivate: [LoginRouteGuard]  },
  { path: 'viewquery', component: ViewQueryComponent, canActivate: [LoginRouteGuard]  },
  { path: 'logout', component: LogOutComponent, canActivate: [LoginRouteGuard]  },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
