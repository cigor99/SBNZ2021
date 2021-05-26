import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarSearchPageComponent } from './components/car-search-page/car-search-page.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/car-search-page',
    pathMatch: 'full',
  },
  {
    path: 'car-search-page',
    component: CarSearchPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
