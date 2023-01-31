import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BlogService } from './service/blog.service';
import { HttpClientModule } from '@angular/common/http';
import { PacotesService } from './service/pacotes.service';
import { DestinosService } from './service/destinos.service';
import { CidadeService } from './service/cidade.service';
import { ClienteService } from './service/cliente.service';
import { EstadoService } from './service/estado.service';
import { StorageService } from './service/storage.service';
import { AuthService } from './service/auth.service';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, HttpClientModule],
  providers: [
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    BlogService,
    PacotesService,
    DestinosService,
    CidadeService,
    ClienteService,
    EstadoService,
    StorageService,
    AuthService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
