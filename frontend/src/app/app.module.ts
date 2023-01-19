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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PerfilComponent } from './home/perfil/perfil.component';
import { LoginComponent } from './home/login/login.component';
import { AuthInterceptorProvider } from './interceptor/auth-interceptor';
import { ErrorInterceptorProvider } from './interceptor/error-interceptor';
import { SignupComponent } from './home/signup/signup.component';
import { MatButtonModule } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import { CheckoutComponent } from './home/checkout/checkout.component';


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
    PerfilComponent,
    LoginComponent,
    SignupComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule
  ],
  providers: [AuthInterceptorProvider, ErrorInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
