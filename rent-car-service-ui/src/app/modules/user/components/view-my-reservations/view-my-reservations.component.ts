import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { Rezervacija } from "src/app/modules/admin/rezervacija";
import { UserService } from "../../user.service";
import { OcenaRequest } from "../../ocena";
import { MatDialog } from "@angular/material/dialog";
import { OcenaDialogComponent } from "../ocena-dialog/ocena-dialog.component";

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

  selectedGrade: number;
  ocene: number[] = [1,2,3,4,5];

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;

	constructor(private userService: UserService, public dialog: MatDialog) {}

	ngOnInit(): void {
    this.getIznajmljivanja();
	}

  getIznajmljivanja(): void{
    this.userService.getIznajmljivanja().subscribe((result) => {
			this.iznajmljivanja = new MatTableDataSource(result);
			this.iznajmljivanja.paginator = this.paginator;
			this.iznajmljivanja.sort = this.sort;
		});
  }

  oceni(row): void{
    // const request: OcenaRequest = {

    // };
    const dialogRef = this.dialog.open(OcenaDialogComponent, {
      width: '300px',
      panelClass : 'mat-elevation-z8',
      data: {}
    });

    dialogRef.afterClosed()
             .subscribe(vrednostOcene => {
               if(vrednostOcene){
                const request : OcenaRequest = {
                  vrednost : vrednostOcene,
                  datum : new Date(),
                  autoId : row.autoId
                }
                this.userService.oceni(request, row.id).subscribe(res => {
                  this.getIznajmljivanja();
                });
              }
             });

    console.log(row);
  }
}
