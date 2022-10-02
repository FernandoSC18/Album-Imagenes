import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppAlbumsComponent } from './app-albums/app-albums.component';
import { AppImagesComponent } from './app-images/app-images.component';

const routes: Routes = [
  { path: '', component: AppAlbumsComponent, pathMatch: 'full' },
  { path: 'images/:album_id', component: AppImagesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
