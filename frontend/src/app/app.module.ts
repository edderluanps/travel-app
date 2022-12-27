import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { BlogComponent } from './home/blog/blog.component';

import { HttpClientModule } from '@angular/common/http';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './AppRoutingModule';
import { PacotesComponent } from './home/pacotes/pacotes.component';
import { DestinosComponent } from './home/destinos/destinos.component';
import { BlogPostComponent } from './home/blog/blog-post/blog-post.component';
import { PacotePageComponent } from './home/pacotes/pacote-page/pacote-page.component';
import { CarrinhoComponent } from './home/carrinho/carrinho.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './home/login/login.component';
import { AuthService } from './service/auth.service';
import { StorageService } from './service/storage.service';
import { PerfilComponent } from './home/perfil/perfil.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BlogComponent,
    PacotesComponent,
    DestinosComponent,
    BlogPostComponent,
    PacotePageComponent,
    CarrinhoComponent,
    LoginComponent,
    PerfilComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, StorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
