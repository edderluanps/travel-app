import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { BlogPage } from './home/blog/blog.page';
import { HomepagePage } from './home/homepage/homepage.page';
import { LocaisPage } from './home/locais/locais.page';
import { LoginPage } from './home/login/login.page';
import { PacotePagePage } from './home/pacotes/pacote-page/pacote-page.page';
import { PacotesPage } from './home/pacotes/pacotes.page';
import { SignupPage } from './home/signup/signup.page';

const routes: Routes = [
  { path: 'home', loadChildren: () => import('./home/home.module').then( m => m.HomePageModule) },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginPage },
  { path: 'signup', component: SignupPage },
  { path: 'homepage', component: HomepagePage },
  { path: 'pacotes', component: PacotesPage },
  { path: 'pacote-page', component: PacotePagePage },
  { path: 'blog', component: BlogPage },
  { path: 'blog-post-page', component: BlogPage },
  { path: 'locais', component: LocaisPage }


];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
