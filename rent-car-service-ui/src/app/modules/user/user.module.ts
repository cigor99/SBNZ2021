import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { UserRoutingModule } from "./user-routing.module";
import { ViewMyReservationsComponent } from "./components/view-my-reservations/view-my-reservations.component";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatSortModule } from "@angular/material/sort";
import { MatTableModule } from "@angular/material/table";

@NgModule({
	declarations: [ViewMyReservationsComponent],
	imports: [
		CommonModule,
		UserRoutingModule,
		MatPaginatorModule,
		MatSortModule,
		MatTableModule,
	],
})
export class UserModule {}
