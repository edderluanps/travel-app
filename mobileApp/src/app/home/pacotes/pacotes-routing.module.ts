import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PacotesPage } from './pacotes.page';

const routes: Routes = [
  {
    path: '',
    component: PacotesPage
  },
  {
    path: 'pacote-page',
    loadChildren: () => import('./pacote-page/pacote-page.module').then( m => m.PacotePagePageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PacotesPageRoutingModule {}
