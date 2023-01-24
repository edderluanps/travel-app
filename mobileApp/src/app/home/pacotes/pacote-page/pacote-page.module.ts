import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PacotePagePageRoutingModule } from './pacote-page-routing.module';

import { PacotePagePage } from './pacote-page.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PacotePagePageRoutingModule
  ],
  declarations: [PacotePagePage]
})
export class PacotePagePageModule {}
