import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { BlogComponent } from "./home/blog/blog.component";
import { DestinosComponent } from "./home/destinos/destinos.component";
import { HomepageComponent } from "./home/homepage/homepage.component";
import { PacotesComponent } from "./home/pacotes/pacotes.component";

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'pacotes', component: PacotesComponent },
  { path: 'destinos', component: DestinosComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {

}
