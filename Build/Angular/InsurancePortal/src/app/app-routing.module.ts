import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { RegisterComponent } from './register/register.component';
import { MenuComponent } from 'src/app/menu/menu.component';
import { LogOutComponent } from './log-out/log-out.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ShoppingItemComponent } from './shopping-item/shopping-item.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import {BuypolicyComponent} from './buypolicy/buypolicy.component';
import { AuthGuard } from './guards/auth.guards';
import { QueryComponent } from './query/query.component';
import { ViewQueryComponent } from './view-query/view-query.component';
import { QueryDetailsComponent } from './query-details/query-details.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]  },
  { path: 'about', component: AboutComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'menu', component: MenuComponent, canActivate: [AuthGuard]  },
  { path: 'logout', component: LogOutComponent, canActivate: [AuthGuard]  },
  { path: 'productlist', component: ProductListComponent, canActivate: [AuthGuard]  },
  { path: 'shoppingitem', component: ShoppingItemComponent, canActivate: [AuthGuard] },
  { path: 'addquery', component: QueryComponent, canActivate: [AuthGuard]  },
  { path: 'buypolicy', component: BuypolicyComponent, canActivate: [AuthGuard]  },
  { path: 'viewquery', component: ViewQueryComponent, canActivate: [AuthGuard]  },
  { path: 'queryDetails', component: QueryDetailsComponent, canActivate: [AuthGuard]  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
