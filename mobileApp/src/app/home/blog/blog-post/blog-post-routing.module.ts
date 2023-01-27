import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BlogPostPage } from './blog-post.page';

const routes: Routes = [
  {
    path: '',
    component: BlogPostPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BlogPostPageRoutingModule {}
