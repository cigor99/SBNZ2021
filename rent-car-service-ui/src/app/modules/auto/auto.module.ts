import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutoRoutingModule } from './auto-routing.module';
import { MaterialModule } from '../../material-module';
import { AdvancedSearchAutoComponent } from './components/advanced-search-auto/advanced-search-auto.component';
import { AddFormAutoComponent } from './components/add-form-auto/add-form-auto.component';
import { AutoReviewComponent } from './components/auto-review/auto-review.component';
import { ViewAllAutoComponent } from './components/view-all-auto/view-all-auto.component';
import { ReservationDialogComponent } from './reservation-dialog/reservation-dialog.component';


@NgModule({
  declarations: [AdvancedSearchAutoComponent, AddFormAutoComponent, AutoReviewComponent, ViewAllAutoComponent, ReservationDialogComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    AutoRoutingModule,
    MaterialModule,

  ],
})
export class AutoModule { }
