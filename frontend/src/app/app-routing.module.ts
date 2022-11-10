import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { BlogPostPageComponent } from "./blog/blog-post-page/blog-post-page.component";
import { BlogComponent } from "./blog/blog.component";
import { CarrinhoComponent } from "./carrinho/carrinho.component";
import { DestinoPageComponent } from "./destinos/destino-page/destino-page.component";
import { DestinosComponent } from "./destinos/destinos.component";
import { HomepageComponent } from "./homepage/homepage.component";
import { InfoComponent } from "./info/info.component";
import { LoginComponent } from "./login/login.component";
import { PacotePageComponent } from "./pacotes/pacote-page/pacote-page.component";
import { PacotesComponent } from "./pacotes/pacotes.component";

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'blog-post-page', component: BlogPostPageComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'destinos', component: DestinosComponent },
  { path: 'destino-page', component: DestinoPageComponent },
  { path: 'info', component: InfoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'pacotes', component: PacotesComponent },
  { path: 'pacote-page', component: PacotePageComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {

}
