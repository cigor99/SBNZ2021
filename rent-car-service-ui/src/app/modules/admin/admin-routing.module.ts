import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ViewReservationComponent } from "./components/view-reservation/view-reservation.component";

const routes: Routes = [
	{
		path: "",
		component: ViewReservationComponent,
	},

	{
		path: "view-reservations",
		component: ViewReservationComponent,
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class AdminRoutingModule {}
