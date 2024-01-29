import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/auth/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { SearchComponent } from './components/public/search/search.component';
import { DetailComponent } from './components/public/detail/detail.component';
import { BranchDetailComponent } from './components/public/branch-detail/branch-detail.component';

import { ProfileComponent } from './components/profile/profile.component';
import { VerifyAccountComponent } from './components/auth/verify-account/verify-account.component';

// const AuthGuard = ():boolean =>{
//   return true
// }

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'search',component: SearchComponent },
  { path: 'detail/:jobId',component: DetailComponent },
  { path: 'branch/:branchId',component: BranchDetailComponent },
  { path: 'profile',component: ProfileComponent},
  { path: 'verifyAccount',component: VerifyAccountComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
