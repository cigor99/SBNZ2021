import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

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

  constructor(private formBuilder:FormBuilder) {
    this.addForm = this.formBuilder.group({
      marka: ['', Validators.required],
      model: ['', Validators.required],
      godiste: ['', Validators.required],
      karoserija: new FormControl(),
      tipGoriva: new FormControl(),
      duzina: ['', Validators.required],
      sirina: ['', Validators.required],
      visina: ['', Validators.required],
      brojSedista: ['', Validators.required],
      zapreminaGepeka: ['', Validators.required],
      zapreminaRezervoara: ['', Validators.required],
      distanca: ['', Validators.required],
      ubrzanje: ['', Validators.required],
      maksimalnaBrzina: ['', Validators.required],
      cena: ['', Validators.required],
      //dodatna oprema i dodaci za udobnost
    });
   }

  ngOnInit(): void {
  }

  addCar(){

  }

}
