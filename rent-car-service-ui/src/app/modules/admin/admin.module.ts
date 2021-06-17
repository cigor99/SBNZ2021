import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ViewReservationComponent } from "./components/view-reservation/view-reservation.component";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { AdminRoutingModule } from "./admin-routing.module";
import { CdkColumnDef } from "@angular/cdk/table";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatSortModule } from "@angular/material/sort";
import { MatButtonModule } from "@angular/material/button";

@NgModule({
	declarations: [ViewReservationComponent],
	imports: [
		CommonModule,
		AdminRoutingModule,
		MatTableModule,
		MatPaginatorModule,
		MatFormFieldModule,
		MatSortModule,
		MatButtonModule,
	],
	providers: [CdkColumnDef],
})
export class AdminModule {}
