import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { BlogPostComponent } from "./home/blog/blog-post/blog-post.component";
import { BlogComponent } from "./home/blog/blog.component";
import { CarrinhoComponent } from "./home/carrinho/carrinho.component";
import { DestinosComponent } from "./home/destinos/destinos.component";
import { HomepageComponent } from "./home/homepage/homepage.component";
import { LoginComponent } from "./home/login/login.component";
import { PacotePageComponent } from "./home/pacotes/pacote-page/pacote-page.component";
import { PacotesComponent } from "./home/pacotes/pacotes.component";

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'blog-post', component: BlogPostComponent },
  { path: 'pacotes', component: PacotesComponent },
  { path: 'pacote-page', component: PacotePageComponent },
  { path: 'destinos', component: DestinosComponent },
  { path: 'carrinho', component: CarrinhoComponent }

];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {

}
