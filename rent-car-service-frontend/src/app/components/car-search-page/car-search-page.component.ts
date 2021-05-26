import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-car-search-page',
  templateUrl: './car-search-page.component.html',
  styleUrls: ['./car-search-page.component.sass'],
})
export class CarSearchPageComponent implements OnInit {
  svrhaLista: any[] = [
    { value: 'GRADSKA_VOZNJA', viewValue: 'Gradska Voznja' },
    { value: 'DUZE_PUTOVANJE', viewValue: 'Duze Putovanje' },
  ];
  dodatnaOprema: any[] = [
    { value: 'AUTOMATSKI_MENJAC', viewValue: 'Automatski Menjac' },
    { value: 'PARKING_SENZORI', viewValue: 'Parking Senzori' },
  ];
  dodaciZaUdobnost: any[] = [
    { value: 'GREJACI_SEDISTA', viewValue: 'Grejaci Sedista' },
    { value: 'MULTIMEDIJALNI_SISTEM', viewValue: 'Multimedijalni Sistem' },
  ];
  brojPutnika: any[] = [
    { value: '1', viewValue: '1' },
    { value: '4', viewValue: '4' },
  ];
  nivoBudzeta: any[] = [
    { value: 'NIZAK', viewValue: 'Nizak' },
    { value: 'SREDNJI', viewValue: 'Srednji' },
    { value: 'VISOK', viewValue: 'Visok' },
  ];

  ekoloskaVoznja = new FormControl(false, []);

  carSearchForm: FormGroup;

  constructor() {
    this.carSearchForm = new FormGroup({
      svrha: new FormControl('', [Validators.required]),
      dodatnaOprema: new FormControl('', [Validators.required]),
      dodaciZaUdobnost: new FormControl('', [Validators.required]),
      ekoloskaVoznja: this.ekoloskaVoznja,
      brojPutnika: new FormControl('', [Validators.required]),
      nivoBudzeta: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    console.log(this.carSearchForm);
  }
}
