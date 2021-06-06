import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-advanced-search-auto',
  templateUrl: './advanced-search-auto.component.html',
  styleUrls: ['./advanced-search-auto.component.scss']
})
export class AdvancedSearchAutoComponent implements OnInit {

  searchForm: FormGroup;
  ekoCheckbox = new FormControl(false);

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
  ]

  constructor(private formBuilder:FormBuilder)
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
    console.log(this.searchForm.value);
  }

}
