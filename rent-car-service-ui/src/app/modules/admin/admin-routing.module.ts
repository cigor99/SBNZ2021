import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "src/app/core/guards/auth.guard";
import { ViewReservationComponent } from "./components/view-reservation/view-reservation.component";

const routes: Routes = [
	{
		path: "view-reservations",
		component: ViewReservationComponent,
		canActivate: [AuthGuard],
		data: { roles: ["ROLE_ADMIN"] },
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class AdminRoutingModule {}
