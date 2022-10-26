import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './home/header/header.component';
import { FooterComponent } from './home/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { MatIconModule } from '@angular/material/icon';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AreaPacotesComponent } from './home/area-pacotes/area-pacotes.component';
import { AreaBlogComponent } from './home/area-blog/area-blog.component';
import { AreaLocaisComponent } from './home/area-locais/area-locais.component';
import { AreaSobreComponent } from './home/area-sobre/area-sobre.component';
import { BlogPageComponent } from './blog-page/blog-page.component'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    AreaPacotesComponent,
    AreaBlogComponent,
    AreaLocaisComponent,
    AreaSobreComponent,
    BlogPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
