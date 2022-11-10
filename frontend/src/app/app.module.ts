import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { MatIconModule } from '@angular/material/icon';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomepageComponent } from './homepage/homepage.component';
import { BlogComponent } from './blog/blog.component';
import { PacotesComponent } from './pacotes/pacotes.component';
import { DestinosComponent } from './destinos/destinos.component';
import { LoginComponent } from './login/login.component';
import { CarrinhoComponent } from './carrinho/carrinho.component';
import { InfoComponent } from './info/info.component';
import { PacotePageComponent } from './pacotes/pacote-page/pacote-page.component';
import { BlogPostPageComponent } from './blog/blog-post-page/blog-post-page.component';
import { DestinoPageComponent } from './destinos/destino-page/destino-page.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BlogComponent,
    PacotesComponent,
    DestinosComponent,
    LoginComponent,
    CarrinhoComponent,
    InfoComponent,
    PacotePageComponent,
    BlogPostPageComponent,
    DestinoPageComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    FontAwesomeModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
