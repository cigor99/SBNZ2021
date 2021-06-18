import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "src/app/core/guards/auth.guard";
import { ViewMyReservationsComponent } from "./components/view-my-reservations/view-my-reservations.component";

const routes: Routes = [
	{
		path: "view-my-reservations",
		component: ViewMyReservationsComponent,
		canActivate: [AuthGuard],
		data: { roles: ["ROLE_USER"] },
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class UserRoutingModule {}
