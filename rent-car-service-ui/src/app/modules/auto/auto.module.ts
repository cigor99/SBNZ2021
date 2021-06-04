import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutoRoutingModule } from './auto-routing.module';
import { MaterialModule } from '../../material-module';
import { AdvancedSearchAutoComponent } from './components/advanced-search-auto/advanced-search-auto.component';

@NgModule({
  declarations: [AdvancedSearchAutoComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    AutoRoutingModule,
    MaterialModule
  ]
})
export class AutoModule { }
