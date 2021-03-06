import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { SnackBarComponent } from 'src/app/core/components/snack-bar/snack-bar.component';
import { Auto } from '../../auto';
import { AutoService } from '../../auto.service';
import { KorisnickiUnos } from '../../korisnickiUnos';
import { ReservationDialogComponent } from '../../reservation-dialog/reservation-dialog.component';

@Component({
  selector: 'app-advanced-search-auto',
  templateUrl: './advanced-search-auto.component.html',
  styleUrls: ['./advanced-search-auto.component.scss']
})
export class AdvancedSearchAutoComponent implements OnInit {

  searchForm: FormGroup;
  ekoCheckbox = new FormControl(false);
  ocene: number[] = [1, 2, 3, 4, 5];
  selectedOcena: number;

  automobili: Auto[];

  svrhaArray: any[] = [
    {value:'GRADSKA_VOZNJA', viewValue:'Gradska voznja'},
    {value: 'DUZA_PUTOVANJA', viewValue: 'Duza putovanja'},
    {value: 'ZABAVA', viewValue: 'Zabava'},
    {value: 'BIZNIS', viewValue: 'Biznis'},
    {value: 'OFFROAD', viewValue: 'Offroad'}
  ];

  dodatnaOpremaArray: any[] = [
    {value:'AUTOMATSKI_MENJAC', viewValue:'Automatski menjac'},
    {value: 'PARKING_SENZORI', viewValue: 'Parking senzori'},
    {value: 'AUTONOMNA_VOZNJA', viewValue: 'Autonomna voznja'},
  ];

  dodaciZaUdobnostArray: any[] = [
    {value:'GREJACI_SEDISTA', viewValue:'Grejaci sedista'},
    {value: 'MULTIMEDIJALNI_SISTEM', viewValue: 'Multimedijalni sistem'},
    {value: 'DRZACI_ZA_CASE', viewValue: 'Drzaci za case'},
  ];

  budzetArray: any[] = [
    {value:'NISKI', viewValue:'Niski'},
    {value: 'SREDNJI', viewValue: 'Srednji'},
    {value:'VISOK', viewValue:'Visok'},
  ];

  constructor(private formBuilder:FormBuilder,
              private autoService: AutoService,
              public snackBar: SnackBarComponent,
              public dialog: MatDialog )
  {
    this.searchForm = this.formBuilder.group({
      svrha:['', Validators.required],
      dodatnaOprema: new FormControl(),
      dodaciZaUdobnost: new FormControl(),
      brojPutnika:['', Validators.required],
      ekoloskaVoznja: this.ekoCheckbox,
      budzet:['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  search(){
    if(this.searchForm.value.dodatnaOprema == null){
      this.searchForm.value.dodatnaOprema = [];
    }
    if(this.searchForm.value.dodaciZaUdobnost == null){
      this.searchForm.value.dodaciZaUdobnost = [];
    }
    let unos: KorisnickiUnos = this.searchForm.value;
    this.autoService.autoNaprednaPretraga(unos).subscribe(data => this.automobili = data.slice(0,4));
  }

  rent(auto: Auto){
    const dialogRef = this.dialog.open(ReservationDialogComponent, {
      width: '300px',
      panelClass : 'mat-elevation-z8',
      data: auto
    });

    dialogRef.afterClosed()
              .subscribe(data =>{
                if(data){
                  console.log(data);
                  this.autoService
                      .rezervisi(data)
                      .subscribe(res => {
                        this.snackBar.openSnackBar('Zahtev prosledjen adminu', '', 'green-snackbar');
                      });
                }
              })

    // console.log(auto);
  }

}

