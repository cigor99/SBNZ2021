import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { Rezervacija } from "src/app/modules/admin/rezervacija";
import { UserService } from "../../user.service";

@Component({
	selector: "app-view-my-reservations",
	templateUrl: "./view-my-reservations.component.html",
	styleUrls: ["./view-my-reservations.component.scss"],
})
export class ViewMyReservationsComponent implements OnInit {
	iznajmljivanja: MatTableDataSource<Rezervacija>;
	displayedColumns: string[] = [
		"pocetakRezervacije",
		"krajRezervacije",
		"autoMarka",
		"autoModel",
		"iznos",
		"oceni",
	];

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	constructor(private userService: UserService) {}

	ngOnInit(): void {
		this.userService.getIznajmljivanja().subscribe((result) => {
			this.iznajmljivanja = new MatTableDataSource(result);
			this.iznajmljivanja.paginator = this.paginator;
			this.iznajmljivanja.sort = this.sort;
		});
	}
}
