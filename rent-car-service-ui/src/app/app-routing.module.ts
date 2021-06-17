import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
	{
		path: "auth",
		loadChildren: () =>
			import("./modules/authentication/authentication.module").then(
				(m) => m.AuthenticationModule
			),
	},
	{
		path: "auto",
		loadChildren: () =>
			import("./modules/auto/auto.module").then((m) => m.AutoModule),
	},
	{
		path: "admin",
		loadChildren: () =>
			import("./modules/admin/admin.module").then((m) => m.AdminModule),
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
