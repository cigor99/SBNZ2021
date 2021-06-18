import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SnackBarComponent } from 'src/app/core/components/snack-bar/snack-bar.component';
import { Auto } from '../../auto';
import { AutoService } from '../../auto.service';

@Component({
  selector: 'app-add-form-auto',
  templateUrl: './add-form-auto.component.html',
  styleUrls: ['./add-form-auto.component.scss']
})
export class AddFormAutoComponent implements OnInit {

  addForm: FormGroup;

  karoserijaArray: any[]= [
    {value:'LIMUNZINA', viewValue:'Limuzina', key:0},
    {value:'HECBEK', viewValue:'Hecbek', key:1},
    {value:'KARAVAN', viewValue:'Karavan', key:2},
    {value:'KUPE', viewValue:'Kupe', key:3},
    {value:'KABRIOLET', viewValue:'Kabriolet', key:4},
    {value:'MINIVAN', viewValue:'Minivan', key:5},
    {value:'SUV', viewValue:'SUV', key:6},
    {value:'PIKAP', viewValue:'Pikap'},
  ];
  tipGorivaArray: any[] = [
    {value:'BENZIN', viewValue:'Benzin', key:0},
    {value:'DIZEL', viewValue:'Dizel', key:1},
    {value:'HIBRID', viewValue:'Hibrid', key:2},
    {value:'ELEKTRICNI', viewValue:'Elektricni', key:3},
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

  constructor(private formBuilder:FormBuilder, private autoService: AutoService, private snackBar: SnackBarComponent) {
    this.addForm = this.formBuilder.group({
      marka: ['', Validators.required],
      model: ['', Validators.required],
      godiste: ['', Validators.required],
      karoserija: new FormControl(Validators.required),
      tipGoriva: new FormControl([Validators.required]),
      duzina: ['', Validators.required],
      sirina: ['', Validators.required],
      visina: ['', Validators.required],
      brojSedista: ['', Validators.required],
      zapreminaGepeka: ['', Validators.required],
      zapreminaRezervoara: ['', Validators.required],
      distanca: [''],
      ubrzanje: ['', Validators.required],
      maksimalnaBrzina: ['', Validators.required],
      cena: ['', Validators.required],
      dodatnaOprema: new FormControl([Validators.required]),
      dodaciZaUdobnost: new FormControl([Validators.required])
      //dodatna oprema i dodaci za udobnost
    });
   }

  ngOnInit(): void {

  }

  addCar(){
    const auto: Auto = this.addForm.value;
    console.log(auto);
    this.autoService.dodajAuto(auto).subscribe(res => {
      this.snackBar.openSnackBar("Uspesno dodato", "", "green-snackbar");
    });
  }

}
