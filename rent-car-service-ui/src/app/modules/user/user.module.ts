import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { UserRoutingModule } from "./user-routing.module";
import { ViewMyReservationsComponent } from "./components/view-my-reservations/view-my-reservations.component";

import { OcenaDialogComponent } from './components/ocena-dialog/ocena-dialog.component';
import { MaterialModule } from "src/app/material-module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
	declarations: [ViewMyReservationsComponent, OcenaDialogComponent],
	imports: [
		CommonModule,
		UserRoutingModule,
    ReactiveFormsModule,
    FormsModule,
		MaterialModule
	],
})
export class UserModule {}
