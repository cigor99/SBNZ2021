import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdvancedSearchAutoComponent } from './components/advanced-search-auto/advanced-search-auto.component';

const routes: Routes = [
  {
    path: 'advanced-search',
    component: AdvancedSearchAutoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutoRoutingModule { }
