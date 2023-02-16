import { NgModule, LOCALE_ID, DEFAULT_CURRENCY_CODE } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';

import { AppComponent } from './app.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { BlogComponent } from './home/blog/blog.component';

import { HttpClientModule } from '@angular/common/http';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing-module';
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
import { MatButtonModule, MatCardModule, MatMenuModule, MatIconModule, MatTabsModule } from '@angular/material';
import { CheckoutComponent } from './home/checkout/checkout.component';
import { ConfirmacaoComponent } from './home/confirmacao/confirmacao.component';

registerLocaleData(localePt, 'pt');

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
    CheckoutComponent,
    ConfirmacaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatTabsModule
  ],
  providers: [AuthInterceptorProvider, ErrorInterceptorProvider,
    {
      provide: LOCALE_ID,
      useValue: 'pt'
    },
    {
      provide: DEFAULT_CURRENCY_CODE,
      useValue: 'BRL'
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
