import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {TabModule } from 'angular-tabs-component';




import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './guards/auth.guards';
import { MenuComponent } from './menu/menu.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ShoppingItemComponent } from './shopping-item/shopping-item.component';
import { ProductListComponent } from './product-list/product-list.component';
import { LogOutComponent } from './log-out/log-out.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { UserService } from './service/user.service';
import { HttpClientModule } from '@angular/common/http';
import { AlertService } from './service/alert.service';
import { ProductService } from './service/product.service';
import { VehicleMasterService } from './service/vehiclemaster.service';
import { BuypolicyComponent } from './buypolicy/buypolicy.component';
import { PolicyService } from './service/policy.service';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    HomeComponent,
    AboutComponent,
    RegisterComponent,
    MenuComponent,
    ShoppingCartComponent,
    ShoppingItemComponent,
    ProductListComponent,
    LogOutComponent,
    MenuBarComponent,
    BuypolicyComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    TabModule
  ],
  providers: [AuthGuard,UserService,AlertService,ProductService,VehicleMasterService,PolicyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
