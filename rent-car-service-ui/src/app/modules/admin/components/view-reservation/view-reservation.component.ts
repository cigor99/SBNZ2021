import { Component, OnInit, ViewChild, ChangeDetectorRef } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { SnackBarComponent } from "src/app/core/components/snack-bar/snack-bar.component";
import { AdminService } from "../../admin.service";
import { Rezervacija } from "../../rezervacija";

@Component({
	selector: "app-view-reservation",
	templateUrl: "./view-reservation.component.html",
	styleUrls: ["./view-reservation.component.scss"],
})
export class ViewReservationComponent implements OnInit {
	rezervacije: MatTableDataSource<Rezervacija>;
	displayedColumns: string[] = [
		"id",
		"pocetakRezervacije",
		"krajRezervacije",
		"autoMarka",
		"autoModel",
		"iznos",
		"korisnikEmail",
		"odobri",
		"odbij",
	];

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	constructor(
		private adminService: AdminService,
		private changeDetectorRefs: ChangeDetectorRef,
		private snackBar: SnackBarComponent
	) {}

	ngOnInit(): void {
		this.refresh();
	}

	refresh(): void {
		this.adminService.getRezervacije().subscribe((result) => {
			this.rezervacije = new MatTableDataSource(result);
			this.rezervacije.paginator = this.paginator;
			this.rezervacije.sort = this.sort;
			this.changeDetectorRefs.detectChanges();
		});
	}

	odobriRezervaciju(id: number) {
		this.adminService.odobriRezervaciju(id).subscribe(() => {
			this.refresh();
			this.snackBar.openSnackBar(
				"Uspesno odobreno",
				"",
				"green-snackbar"
			);
		});
	}

	odbijRezervaciju(id: number) {
		this.adminService.odbijRezervaciju(id).subscribe(() => {
			this.refresh();
			this.snackBar.openSnackBar(
				"Uspesno odbijeno",
				"",
				"green-snackbar"
			);
		});
	}
}
