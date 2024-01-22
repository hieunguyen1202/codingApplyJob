import { NgModule } from '@angular/core';
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
registerLocaleData(en);
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CarouselModule } from 'primeng/carousel';
import { TagModule } from 'primeng/tag';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { SearchComponent } from './components/public/search/search.component';

//module prime
import { TooltipModule } from 'primeng/tooltip';
import { DetailComponent } from './components/public/detail/detail.component';
import { BranchDetailComponent } from './components/public/branch-detail/branch-detail.component';

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
    TooltipModule
  ],
  providers: [provideClientHydration(), { provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent],
})
export class AppModule { }
