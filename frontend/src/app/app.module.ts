import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { BlogComponent } from './home/blog/blog.component';

import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './AppRoutingModule';
import { PacotesComponent } from './home/pacotes/pacotes.component';
import { DestinosComponent } from './home/destinos/destinos.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    BlogComponent,
    PacotesComponent,
    DestinosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
