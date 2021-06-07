import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddFormAutoComponent } from './components/add-form-auto/add-form-auto.component';
import { AdvancedSearchAutoComponent } from './components/advanced-search-auto/advanced-search-auto.component';

const routes: Routes = [
  {
    path: 'advanced-search',
    component: AdvancedSearchAutoComponent
  },
  {
    path: 'add-form',
    component: AddFormAutoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutoRoutingModule { }
