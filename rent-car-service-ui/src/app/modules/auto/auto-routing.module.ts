import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "src/app/core/guards/auth.guard";
import { AddFormAutoComponent } from "./components/add-form-auto/add-form-auto.component";
import { AdvancedSearchAutoComponent } from "./components/advanced-search-auto/advanced-search-auto.component";
import { AutoReviewComponent } from "./components/auto-review/auto-review.component";
import { ViewAllAutoComponent } from "./components/view-all-auto/view-all-auto.component";

const routes: Routes = [
	{
		path: "",
		component: AutoReviewComponent,
	},
	{
		path: "advanced-search",
		component: AdvancedSearchAutoComponent,
	},
	{
		path: "add-form",
		component: AddFormAutoComponent,
		canActivate: [AuthGuard],
		data: { roles: ["ROLE_ADMIN"] },
	},
	{
		path: "view-auto",
		component: ViewAllAutoComponent,
	},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule],
})
export class AutoRoutingModule {}
