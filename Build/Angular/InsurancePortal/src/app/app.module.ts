import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './guards/auth.guards';
import { MenuComponent } from './menu/menu.component';
import { LogOutComponent } from './log-out/log-out.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { UserService } from './service/user.service';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './service/product.service';
import { VehicleMasterService } from './service/vehiclemaster.service';
import { BuypolicyComponent } from './buypolicy/buypolicy.component';
import { PolicyService } from './service/policy.service';
import { QueryComponent } from './query/query.component';
import { ViewQueryComponent } from './view-query/view-query.component';
import { LoginRouteGuard } from './service/loginGuard.service';
import {QueryService} from './service/query.service';
import {SharedDataService} from './service/sharedData.service';
import {UserLoginService} from './service/userLogin.service';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { ViewpolicyComponent } from './viewpolicy/viewpolicy.component';
import {MasterDataService} from './service/masterDataservice';
import { RenewpolicyComponent } from './renewpolicy/renewpolicy.component';
import { ClaimComponent } from './claim/claim.component';
import {EmailValidator} from './validators/emailValidator';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    HomeComponent,
    AboutComponent,
    RegisterComponent,
    MenuComponent,
    LogOutComponent,
    MenuBarComponent,
    BuypolicyComponent,
    QueryComponent,
    ViewQueryComponent,
    ViewpolicyComponent,
    RenewpolicyComponent,
    ClaimComponent,
    EmailValidator
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [AuthGuard,UserService,ProductService,VehicleMasterService,
    PolicyService,QueryService,SharedDataService,UserLoginService,LoginRouteGuard,MasterDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
