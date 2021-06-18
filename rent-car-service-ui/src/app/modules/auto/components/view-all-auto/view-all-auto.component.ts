import { ChangeDetectorRef, Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { Auto } from "../../auto";
import { AutoService } from "../../auto.service";

@Component({
	selector: "app-view-all-auto",
	templateUrl: "./view-all-auto.component.html",
	styleUrls: ["./view-all-auto.component.scss"],
})
export class ViewAllAutoComponent implements OnInit {
	auti: MatTableDataSource<Auto>;
	displayedColumns: string[] = [
		"marka",
		"model",
		"godiste",
		"karoserija",
		"tipGoriva",
		"brojSedista",
		"zapreminaGepeka",
		"zapreminaRezervoara",
		"distanca",
		"ubrzanje",
		"maksimalnaBrzina",
		"cena",
	];

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	constructor(
		private autoService: AutoService,
		private changeDetectorRefs: ChangeDetectorRef
	) {}

	ngOnInit(): void {
		this.autoService.getAutomobili().subscribe((result) => {
			this.auti = new MatTableDataSource(result);
			this.auti.paginator = this.paginator;
			this.auti.sort = this.sort;
		});
	}

	applySearch(event: Event) {
		const searchValue = (event.target as HTMLInputElement).value;
		if (!searchValue) {
			return;
		}
		this.autoService.getAutomobiliMarka(searchValue).subscribe((result) => {
			this.auti = new MatTableDataSource(result);
			this.auti.paginator = this.paginator;
			this.auti.sort = this.sort;
			this.changeDetectorRefs.detectChanges();
		});
	}
}
