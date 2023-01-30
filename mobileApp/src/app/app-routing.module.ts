import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { BlogPostPage } from './home/blog/blog-post/blog-post.page';
import { BlogPage } from './home/blog/blog.page';
import { HomepagePage } from './home/homepage/homepage.page';
import { LocaisPage } from './home/destinos/destinos.page';
import { LoginPage } from './home/login/login.page';
import { PacotePagePage } from './home/pacotes/pacote-page/pacote-page.page';
import { PacotesPage } from './home/pacotes/pacotes.page';
import { SignupPage } from './home/signup/signup.page';
import { PerfilPage } from './home/perfil/perfil.page';

const routes: Routes = [
  { path: 'home', loadChildren: () => import('./home/home.module').then( m => m.HomePageModule) },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: LoginPage },
  { path: 'signup', component: SignupPage },
  { path: 'homepage', component: HomepagePage },
  { path: 'pacotes', component: PacotesPage },
  { path: 'pacote/:id',component: PacotePagePage },
  { path: 'blog', component: BlogPage },
  { path: 'blog-post/:id', component: BlogPostPage },
  { path: 'locais', component: LocaisPage },
  { path: 'perfil', component: PerfilPage }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
