import { Component, OnInit, ViewChild, AfterViewInit } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
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

	constructor(private adminService: AdminService) {}

	ngOnInit(): void {
		this.adminService.getRezervacije().subscribe((result) => {
			this.rezervacije = new MatTableDataSource(result);
			this.rezervacije.paginator = this.paginator;
			this.rezervacije.sort = this.sort;
		});
	}
}
