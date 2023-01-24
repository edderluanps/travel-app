import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PacotesPageRoutingModule } from './pacotes-routing.module';

import { PacotesPage } from './pacotes.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PacotesPageRoutingModule
  ],
  declarations: [PacotesPage]
})
export class PacotesPageModule {}
