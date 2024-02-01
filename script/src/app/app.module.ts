import { NgModule, isDevMode } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/auth/home/home.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { SiteNgZorroAntdModule } from './DemoNgZorroAndModule';
import { SignupComponent } from './components/auth/signup/signup.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CarouselModule } from 'primeng/carousel';
import { TagModule } from 'primeng/tag';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { SearchComponent } from './components/public/search/search.component';
import { ToastModule } from 'primeng/toast';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { MegaMenuModule } from 'primeng/megamenu';
import { BadgeModule } from 'primeng/badge';
import { NzModalModule } from 'ng-zorro-antd/modal';

//module prime
import { TooltipModule } from 'primeng/tooltip';
import { DetailComponent } from './components/public/detail/detail.component';
import { BranchDetailComponent } from './components/public/branch-detail/branch-detail.component';
import { FileUploadModule } from 'primeng/fileupload';
import { MessageService } from 'primeng/api';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { storeUserReducer } from './shared/login.reducer';
import { VerifyAccountComponent } from './components/auth/verify-account/verify-account.component';
registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    NavbarComponent,
    SearchComponent,
    DetailComponent,
    BranchDetailComponent,
    ProfileComponent,
    VerifyAccountComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    SiteNgZorroAntdModule,
    ButtonModule,
    CalendarModule,
    CarouselModule,
    TagModule,
    SlickCarouselModule,
    TooltipModule,
    FileUploadModule,
    NzPopoverModule,
    MegaMenuModule,
    BadgeModule,
    NzModalModule,
    StoreModule.forRoot({ storeUser: storeUserReducer }),
    StoreDevtoolsModule.instrument({
      maxAge: 25, // Retains last 25 states
      logOnly: !isDevMode(), // Restrict extension to log-only mode
      autoPause: true, // Pauses recording actions and state changes when the extension window is not open
      trace: false, //  If set to true, will include stack trace for every dispatched action, so you can see it in trace tab jumping directly to that part of code
      traceLimit: 75, // maximum stack trace frames to be stored (in case trace option was provided as true)
      connectInZone: true, // If set to true, the connection is established within the Angular zone
    }),
  ],
  providers: [
    provideClientHydration(),
    { provide: NZ_I18N, useValue: en_US },
    MessageService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
