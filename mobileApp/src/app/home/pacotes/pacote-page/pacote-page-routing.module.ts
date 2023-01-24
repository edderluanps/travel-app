import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PacotePagePage } from './pacote-page.page';

const routes: Routes = [
  {
    path: '',
    component: PacotePagePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PacotePagePageRoutingModule {}
